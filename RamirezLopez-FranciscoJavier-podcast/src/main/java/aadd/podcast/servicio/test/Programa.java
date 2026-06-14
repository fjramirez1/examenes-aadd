package aadd.podcast.servicio.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aadd.podcast.modelo.Podcast;
import aadd.podcast.repositorio.EntidadNoEncontrada;
import aadd.podcast.repositorio.FactoriaRepositorios;
import aadd.podcast.repositorio.RepositorioException;
import aadd.podcast.repositorio.RepositorioPodcastAdHoc;
import aadd.podcast.servicio.FactoriaServicios;
import aadd.podcast.servicio.IServicioPodcast;
import aadd.podcast.servicio.PodcastResumen;

public class Programa {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada {

		IServicioPodcast servicioPodcast = FactoriaServicios.getServicio(IServicioPodcast.class);
		RepositorioPodcastAdHoc repositorio = FactoriaRepositorios.getRepositorio(Podcast.class);

		List<String> autores = new ArrayList<>();
		autores.add("Autor1");
		autores.add("Autor2");
		autores.add("Autor3");
		// Crear un nuevo podcast
		String id1 = servicioPodcast.nuevoPodcast("Nuevo1", "Podcast de prueba 1", autores, "Categoría");
		String id2 = servicioPodcast.nuevoPodcast("Nuevo2", "Podcast de prueba 2", autores, "Categoría");

		// Crear episodios para el podcast
		Date fecha = new Date();
		fecha.setTime(fecha.getTime() - 1000 * 60 * 60 * 24); // Restar un día a la fecha actual
		servicioPodcast.nuevoEpisodio(id1, 1, "Episodio 1", fecha, 30, "Descripción del episodio 1");
		servicioPodcast.nuevoEpisodio(id1, 2, "Episodio 2", new Date(), 45, "Descripción del episodio 2");
		servicioPodcast.nuevoEpisodio(id2, 1, "Episodio 1", fecha, 60, "Descripción del episodio 1");
		servicioPodcast.nuevoEpisodio(id2, 2, "Episodio 2", new Date(), 90, "Descripción del episodio 2");

		// Recuperar y mostrar los podcasts
		System.out.println("");
		List<PodcastResumen> podcastResumenes = servicioPodcast.recuperarPodcasts();
		podcastResumenes.stream().forEach(p -> System.out.println(p.toString()));

		// Buscar podcast por categoria
		System.out.println("");
		List<Podcast> podcastsCategoria = repositorio.buscarPodcastsPorCategoria("Categoría");
		System.out.println("Podcasts en la categoría 'Categoría':");
		podcastsCategoria.stream().forEach(p -> System.out.println(p.toString()));
	}

}
