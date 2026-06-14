package aadd.podcast.repositorio;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;

import aadd.podcast.modelo.Podcast;

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

}