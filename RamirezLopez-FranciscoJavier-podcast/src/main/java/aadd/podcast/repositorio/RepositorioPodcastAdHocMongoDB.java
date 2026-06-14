package aadd.podcast.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Filters;

import aadd.podcast.modelo.Podcast;
import aadd.podcast.servicio.PodcastDuracionMedia;

public class RepositorioPodcastAdHocMongoDB extends RepositorioPodcastMongoDB implements RepositorioPodcastAdHoc {

	public RepositorioPodcastAdHocMongoDB() throws IOException {
		super();
	}

	@Override
	public List<Podcast> buscarPodcastsPorCategoria(String categoria) throws RepositorioException {
		if (categoria == null || categoria.isEmpty()) {
			throw new IllegalArgumentException("categoria: no debe ser nula ni vacia");
		}

		try {
			Bson filtroCategoria = Filters.eq("categoria", categoria);

			return getCollection().find(filtroCategoria).into(new LinkedList<Podcast>());

		} catch (Exception e) {
			throw new RepositorioException("Error recuperando podcasts por categoria", e);
		}
	}

	@Override
	public List<PodcastDuracionMedia> recuperarPodcastsConDuracionMedia() throws RepositorioException {
		try {
			// Etapa 1 – proyectar sólo los campos necesarios
			Document project1 = new Document("$project", new Document("titulo", 1).append("episodios", 1));

			// Etapa 2 – desplegar el array de episodios
			Document unwind = new Document("$unwind",
					new Document("path", "$episodios").append("preserveNullAndEmptyArrays", false));

			// Etapa 3 – agrupar por podcast calculando la media de duracionMinutos
			Document group = new Document("$group",
					new Document("_id", "$_id").append("titulo", new Document("$first", "$titulo"))
							.append("duracionMedia", new Document("$avg", "$episodios.duracionMinutos")));

			// Etapa 4 – proyectar el resultado final
			Document project2 = new Document("$project",
					new Document("_id", 1).append("titulo", 1).append("duracionMedia", 1));

			List<Document> pipeline = Arrays.asList(project1, unwind, group, project2);

			// La colección sin tipar se obtiene desde la base de datos heredada
			// de RepositorioPodcastMongoDB → no se codifica el nombre aquí.
			AggregateIterable<Document> resultados = database
					.getCollection(getCollection().getNamespace().getCollectionName()).aggregate(pipeline);

			List<PodcastDuracionMedia> lista = new ArrayList<>();
			for (Document doc : resultados) {
				String id = doc.getObjectId("_id").toString();
				String titulo = doc.getString("titulo");
				double media = doc.getDouble("duracionMedia");
				lista.add(new PodcastDuracionMedia(id, titulo, media));
			}
			return lista;

		} catch (Exception e) {
			throw new RepositorioException("Error ejecutando el agregado de duración media: ", e);
		}
	}

}