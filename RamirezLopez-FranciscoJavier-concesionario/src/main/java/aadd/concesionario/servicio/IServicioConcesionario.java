package aadd.concesionario.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import aadd.concesionario.repositorio.EntidadNoEncontrada;
import aadd.concesionario.repositorio.RepositorioException;

public interface IServicioConcesionario {

	String altaCoche(String matricula, String modelo, LocalDate fechaCompra, String nombreContacto,
			String direccionContacto, String telefonoContacto, String emailContacto) throws RepositorioException;

	void nuevoMantenimiento(String idCoche, LocalDate fechaMantenimiento, int kilometraje, boolean cambioAceite,
			boolean cambioLiquidoFrenos, boolean cambioFiltros, String descripcion, float precio)
			throws RepositorioException, EntidadNoEncontrada;

	List<CocheResumen> getCochesConMantenimientoCercano(Optional<LocalDate> fecha) throws RepositorioException;

}
