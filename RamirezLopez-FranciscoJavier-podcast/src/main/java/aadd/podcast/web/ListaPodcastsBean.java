package aadd.podcast.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aadd.podcast.repositorio.RepositorioException;
import aadd.podcast.servicio.PodcastResumen;
import aadd.podcast.servicio.IServicioPodcast;

@Named
@ViewScoped
public class ListaPodcastsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IServicioPodcast servicioPodcast;

	private PodcastResumen podcastSeleccionado;

	private List<PodcastResumen> podcasts;

	@PostConstruct
	public void init() {
		try {
			cargarTodosLosPodcasts();
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
	}

	private void cargarTodosLosPodcasts() throws RepositorioException {
		podcasts = servicioPodcast.recuperarPodcasts();
	}

	public String anadirEpisodio() {
		if (podcastSeleccionado != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("podcastId",
					podcastSeleccionado.getId());
			return "anadirEpisodio?faces-redirect=true";
		}
		return null;
	}

	public PodcastResumen getPodcastSeleccionado() {
		return podcastSeleccionado;
	}

	public void setPodcastSeleccionado(PodcastResumen podcastSeleccionado) {
		this.podcastSeleccionado = podcastSeleccionado;
	}

	public List<PodcastResumen> getPodcasts() {
		return podcasts;
	}

	public void setPodcasts(List<PodcastResumen> podcasts) {
		this.podcasts = podcasts;
	}
}