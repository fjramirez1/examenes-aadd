package aadd.refugio.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aadd.refugio.repositorio.RepositorioException;
import aadd.refugio.servicio.AnimalResumen;
import aadd.refugio.servicio.IServicioRefugio;

@Named
@ViewScoped
public class ListaAnimalesBean implements Serializable {

	private static final long serialVersionUID = 4405311125124704362L;

	@Inject
	private IServicioRefugio servicioRefugio;

	@Inject
	private FacesContext facesContext;

	private List<AnimalResumen> animales;

	private AnimalResumen animalSeleccionado;

	private Integer anyosFiltro;

	@PostConstruct
	public void init() {
		cargarTodosLosCoches();
	}

	private void cargarTodosLosCoches() {
		try {
			animales = servicioRefugio.getAnimales();
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
	}

	public void filtrar() {
		try {
			animales = servicioRefugio.recuperarAnimales(anyosFiltro);
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
	}

	public String anadirRevision() {
		if (animalSeleccionado != null) {
			if (animalSeleccionado.isAdopcion()) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Animal en adopción",
						"No se puede añadir una revisión a un animal en adopción"));
				return null;
			}
			facesContext.getExternalContext().getSessionMap().put("animal", animalSeleccionado);
			return "anadirRevision?faces-redirect=true";
		}
		return null;
	}

	public List<AnimalResumen> getAnimales() {
		return animales;
	}

	public void setAnimales(List<AnimalResumen> animales) {
		this.animales = animales;
	}

	public AnimalResumen getAnimalSeleccionado() {
		return animalSeleccionado;
	}

	public void setAnimalSeleccionado(AnimalResumen animalSeleccionado) {
		this.animalSeleccionado = animalSeleccionado;
	}

	public Integer getAnyosFiltro() {
		return anyosFiltro;
	}

	public void setAnyosFiltro(Integer anyosFiltro) {
		this.anyosFiltro = anyosFiltro;
	}

}