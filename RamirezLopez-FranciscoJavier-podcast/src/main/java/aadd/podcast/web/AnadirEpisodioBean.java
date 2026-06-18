package aadd.podcast.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aadd.podcast.modelo.Episodio;
import aadd.podcast.repositorio.EntidadNoEncontrada;
import aadd.podcast.repositorio.RepositorioException;
import aadd.podcast.servicio.IServicioPodcast;

@Named
@ViewScoped
public class AnadirEpisodioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IServicioPodcast servicioPodcast;

	private String podcastId;

	private Episodio nuevoEpisodio;

	@PostConstruct
	public void init() {
		nuevoEpisodio = new Episodio();
		podcastId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("podcastId");

	}

	public String guardarEpisodio() {
		try {
			servicioPodcast.nuevoEpisodio(this.podcastId, nuevoEpisodio.getNumEpisodio(), nuevoEpisodio.getTitulo(),
					nuevoEpisodio.getFechaPublicacion(), nuevoEpisodio.getDuracionMinutos(),
					nuevoEpisodio.getDescripcion());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("podcastId");
			return "listaPodcasts?faces-redirect=true";
		} catch (IllegalArgumentException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos no válidos", e.getMessage()));
			return null;
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el episodio."));
			return null;
		}
	}

	public String cancelar() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("podcastId");

		return "listaPodcasts?faces-redirect=true";
	}

	public String getPodcastId() {
		return podcastId;
	}

	public void setPodcastId(String podcastId) {
		this.podcastId = podcastId;
	}

	public Episodio getNuevoEpisodio() {
		return nuevoEpisodio;
	}

	public void setNuevoEpisodio(Episodio nuevoEpisodio) {
		this.nuevoEpisodio = nuevoEpisodio;
	}

}