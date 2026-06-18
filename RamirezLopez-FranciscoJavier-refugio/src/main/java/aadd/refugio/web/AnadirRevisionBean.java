package aadd.refugio.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aadd.refugio.modelo.Revision;
import aadd.refugio.repositorio.EntidadNoEncontrada;
import aadd.refugio.repositorio.RepositorioException;
import aadd.refugio.servicio.AnimalResumen;
import aadd.refugio.servicio.ServicioRefugio;

@Named
@ViewScoped
public class AnadirRevisionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServicioRefugio servicioRefugio;

	private AnimalResumen animalSeleccionado;

	private Revision nuevaRevision;

	@PostConstruct
	public void init() {
		nuevaRevision = new Revision();
		animalSeleccionado = (AnimalResumen) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("animal");
	}

	public String guardarRevision() {
		try {
			servicioRefugio.addRevision(animalSeleccionado.getId(), nuevaRevision.getFecha(), nuevaRevision.getPeso(),
					nuevaRevision.getObservaciones());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("animal");
			return "listaAnimales?faces-redirect=true";
		} catch (IllegalArgumentException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos no válidos", e.getMessage()));
			return null;
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la revisión."));
			return null;
		}
	}

	public String cancelar() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("animal");

		return "listaAnimales?faces-redirect=true";
	}

	public AnimalResumen getAnimalSeleccionado() {
		return animalSeleccionado;
	}

	public void setAnimalSeleccionado(AnimalResumen animalSeleccionado) {
		this.animalSeleccionado = animalSeleccionado;
	}

	public Revision getNuevaRevision() {
		return nuevaRevision;
	}

	public void setNuevaRevision(Revision nuevaRevision) {
		this.nuevaRevision = nuevaRevision;
	}

}