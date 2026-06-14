package aadd.podcast.servicio;

import java.util.Date;
import java.util.List;

import aadd.podcast.repositorio.EntidadNoEncontrada;
import aadd.podcast.repositorio.RepositorioException;

public interface IServicioPodcast {

	String nuevoPodcast(String titulo, String descripcion, List<String> nombresAutores, String categoria)
			throws RepositorioException;

	void nuevoEpisodio(String idPodcast, int numEpisodio, String titulo, Date fechaPublicacion, int duracionMinutos,
			String descripcion) throws RepositorioException, EntidadNoEncontrada;

	List<PodcastResumen> recuperarPodcasts() throws RepositorioException;

}
