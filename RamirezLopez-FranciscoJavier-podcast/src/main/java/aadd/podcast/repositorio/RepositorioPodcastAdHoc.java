package aadd.podcast.repositorio;

import java.util.List;

import aadd.podcast.modelo.Podcast;
import aadd.podcast.servicio.PodcastDuracionMedia;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Podcast).
 */
public interface RepositorioPodcastAdHoc extends RepositorioString<Podcast> {

	List<Podcast> buscarPodcastsPorCategoria(String categoria) throws RepositorioException;

	List<PodcastDuracionMedia> recuperarPodcastsConDuracionMedia() throws RepositorioException;

}