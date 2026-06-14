package aadd.podcast.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import aadd.podcast.modelo.Podcast;
import aadd.podcast.servicio.PodcastDuracionMedia;

public class RepositorioPodcastAdHocMongoDB extends RepositorioPodcastMongoDB implements RepositorioPodcastAdHoc {

	private MongoCollection<Document> coleccionSinCodificar;

	public RepositorioPodcastAdHocMongoDB() throws IOException {
		super();
		coleccionSinCodificar = database.getCollection("podcasts");
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
			Document unwind = new Document("$unwind", "$episodios");

			Bson group = Aggregates.group("$_id", Accumulators.first("titulo", "$titulo"),
					Accumulators.avg("duracionMedia", "$episodios.duracionMinutos"));

			AggregateIterable<Document> resultados = coleccionSinCodificar.aggregate(Arrays.asList(unwind, group));

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