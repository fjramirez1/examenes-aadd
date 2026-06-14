package aadd.podcast.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import aadd.podcast.modelo.Episodio;
import aadd.podcast.modelo.Podcast;
import aadd.podcast.repositorio.EntidadNoEncontrada;
import aadd.podcast.repositorio.FactoriaRepositorios;
import aadd.podcast.repositorio.Repositorio;
import aadd.podcast.repositorio.RepositorioException;

@ApplicationScoped
public class ServicioPodcast implements IServicioPodcast {

	private Repositorio<Podcast, String> repositorio = FactoriaRepositorios.getRepositorio(Podcast.class);

	@Override
	public String nuevoPodcast(String titulo, String descripcion, List<String> nombresAutores, String categoria)
			throws RepositorioException {

		if (titulo == null || titulo.isEmpty())
			throw new IllegalArgumentException("titulo: no debe ser nulo ni vacio");

		if (descripcion == null || descripcion.isEmpty())
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");

		if (nombresAutores == null)
			throw new IllegalArgumentException("nombresAutores: no debe ser una colección nula");

		if (nombresAutores.size() < 1)
			throw new IllegalArgumentException("nombresAutores: debe tener al menos un autor");

		if (categoria == null || categoria.isEmpty())
			throw new IllegalArgumentException("categoria: no debe ser nulo ni vacio");

		Podcast podcast = new Podcast(titulo, descripcion, nombresAutores, categoria);

		String id = repositorio.add(podcast);

		return id;
	}

	@Override
	public void nuevoEpisodio(String idPodcast, int numEpisodio, String titulo, Date fechaPublicacion,
			int duracionMinutos, String descripcion) throws RepositorioException, EntidadNoEncontrada {

		if (idPodcast == null || idPodcast.isEmpty())
			throw new IllegalArgumentException("idPodcast: no debe ser nulo ni vacio");

		if (numEpisodio < 1)
			throw new IllegalArgumentException("numEpisodio: no debe ser menor que 1");

		if (titulo == null || titulo.isEmpty())
			throw new IllegalArgumentException("titulo: no debe ser nulo ni vacio");

		if (fechaPublicacion == null)
			throw new IllegalArgumentException("fechaPublicacion: no debe ser nula");

		if (fechaPublicacion.after(new Date()))
			throw new IllegalArgumentException("fechaPublicacion: no debe estar en el futuro");

		if (duracionMinutos < 1)
			throw new IllegalArgumentException("duracionMinutos: no debe ser menor que 1");

		if (descripcion == null || descripcion.isEmpty())
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");

		Podcast podcast = repositorio.getById(idPodcast);

		Episodio episodio = new Episodio(numEpisodio, titulo, fechaPublicacion, duracionMinutos, descripcion);

		List<Episodio> episodios;

		if (podcast.getEpisodios() == null) {
			episodios = new ArrayList<>();
			podcast.setFechaUltimoEpisodio(fechaPublicacion);
		} else {
			episodios = podcast.getEpisodios();
			if (podcast.getFechaUltimoEpisodio().before(fechaPublicacion)) {
				podcast.setFechaUltimoEpisodio(fechaPublicacion);
			}
		}

		episodios.add(episodio);
		podcast.setEpisodios(episodios);

		repositorio.update(podcast);
	}

	@Override
	public List<PodcastResumen> recuperarPodcasts() throws RepositorioException {
		List<PodcastResumen> resumenes = new ArrayList<>();
		List<Podcast> podcasts = repositorio.getAll();
		PodcastResumen resumen;

		for (Podcast p : podcasts) {
			resumen = new PodcastResumen();
			resumen.setAutores(p.getAutores());
			resumen.setCategoria(p.getCategoria());
			resumen.setDescripcion(p.getDescripcion());
			resumen.setFechaUltimoEpisodio(p.getFechaUltimoEpisodio());
			resumen.setId(p.getId());
			resumen.setNumEpisodios(p.getNumEpisodios());
			resumen.setTitulo(p.getTitulo());
			resumenes.add(resumen);
		}

		return resumenes;
	}

}
