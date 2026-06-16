package aadd.concesionario.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aadd.concesionario.modelo.Coche;
import aadd.concesionario.repositorio.RepositorioException;
import aadd.concesionario.servicio.CocheResumen;
import aadd.concesionario.servicio.IServicioConcesionario;

@Named
@ViewScoped
public class ListaCochesBean implements Serializable {

	private static final long serialVersionUID = 4405311125124704362L;

	@Inject
	private IServicioConcesionario servicioConcesionario;

	private List<CocheResumen> coches;

	private Coche cocheSeleccionado;

	private LocalDate fechaFiltro;

	@PostConstruct
	public void init() {
		cargarTodosLosCoches();
	}

	private void cargarTodosLosCoches() {
		try {
			coches = servicioConcesionario.getCoches();
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
	}

	public void filtrar() {
		if (fechaFiltro != null) {
			try {
				coches = servicioConcesionario.getCochesConMantenimientoCercano(fechaFiltro);
			} catch (RepositorioException e) {
				e.printStackTrace();
			}
		}
	}

	public String verDetalle() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cocheDetalleId",
				cocheSeleccionado.getId());

		return "verCoche?faces-redirect=true";
	}

	public List<CocheResumen> getCoches() {
		return coches;
	}

	public void setCoches(List<CocheResumen> coches) {
		this.coches = coches;
	}

	public Coche getCocheSeleccionado() {
		return cocheSeleccionado;
	}

	public LocalDate getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(LocalDate fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public void setCocheSeleccionado(Coche cocheSeleccionado) {
		this.cocheSeleccionado = cocheSeleccionado;
	}

}