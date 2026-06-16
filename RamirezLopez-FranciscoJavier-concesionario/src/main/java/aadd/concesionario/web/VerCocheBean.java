package aadd.concesionario.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aadd.concesionario.modelo.Coche;
import aadd.concesionario.repositorio.EntidadNoEncontrada;
import aadd.concesionario.repositorio.RepositorioException;
import aadd.concesionario.servicio.IServicioConcesionario;

@Named
@ViewScoped
public class VerCocheBean implements Serializable {

	private static final long serialVersionUID = -8218893036371941455L;

	@Inject
	private IServicioConcesionario servicioConcesionario;

	private Coche coche;

	@PostConstruct
	public void init() {
		String id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("cocheDetalleId");
		try {
			servicioConcesionario.getCocheById(id);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}

	}

	public Coche getCoche() {
		return coche;
	}
}
