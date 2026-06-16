package aadd.concesionario.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import aadd.concesionario.modelo.Coche;
import aadd.concesionario.modelo.Contacto;
import aadd.concesionario.modelo.Mantenimiento;
import aadd.concesionario.repositorio.EntidadNoEncontrada;
import aadd.concesionario.repositorio.FactoriaRepositorios;
import aadd.concesionario.repositorio.RepositorioConcesionarioAdHoc;
import aadd.concesionario.repositorio.RepositorioException;

public class ServicioConcesionario implements IServicioConcesionario {

	private RepositorioConcesionarioAdHoc repositorio = FactoriaRepositorios.getRepositorio(Coche.class);

	@Override
	public String altaCoche(String matricula, String modelo, LocalDate fechaCompra, String nombreContacto,
			String direccionContacto, String telefonoContacto, String emailContacto) throws RepositorioException {

		if (matricula == null || matricula.isEmpty()) {
			throw new IllegalArgumentException("matricula: no debe ser nulo ni vacio");
		}
		if (modelo == null || modelo.isEmpty()) {
			throw new IllegalArgumentException("modelo: no debe ser nulo ni vacio");
		}
		if (fechaCompra == null) {
			throw new IllegalArgumentException("fechaCompra: no debe ser nulo");
		}
		if (fechaCompra.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("fechaCompra: no debe ser posterior a la fecha actual");
		}
		if (nombreContacto == null || nombreContacto.isEmpty()) {
			throw new IllegalArgumentException("nombreContacto: no debe ser nulo ni vacio");
		}
		if (direccionContacto == null || direccionContacto.isEmpty()) {
			throw new IllegalArgumentException("direccionContacto: no debe ser nulo ni vacio");
		}
		if (telefonoContacto == null || telefonoContacto.isEmpty()) {
			throw new IllegalArgumentException("telefonoContacto: no debe ser nulo ni vacio");
		}
		if (emailContacto == null || emailContacto.isEmpty()) {
			throw new IllegalArgumentException("emailContacto: no debe ser nulo ni vacio");
		}

		Contacto contacto = new Contacto(nombreContacto, direccionContacto, telefonoContacto, emailContacto);
		Coche coche = new Coche(matricula, modelo, fechaCompra, contacto);

		String id = repositorio.add(coche);

		return id;
	}

	@Override
	public void nuevoMantenimiento(String idCoche, LocalDate fechaMantenimiento, int kilometraje, boolean cambioAceite,
			boolean cambioLiquidoFrenos, boolean cambioFiltros, String descripcion, float precio)
			throws RepositorioException, EntidadNoEncontrada {

		if (idCoche == null || idCoche.isEmpty()) {
			throw new IllegalArgumentException("idCoche: no debe ser nulo ni vacio");
		}
		if (fechaMantenimiento == null) {
			throw new IllegalArgumentException("fechaMantenimiento: no debe ser nulo");
		}
		if (fechaMantenimiento.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("fechaMantenimiento: no debe ser posterior a la fecha actual");
		}
		if (kilometraje < 0) {
			throw new IllegalArgumentException("kilometraje: no debe ser negativo");
		}
		if (descripcion == null || descripcion.isEmpty()) {
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		}
		if (precio < 0) {
			throw new IllegalArgumentException("precio: no debe ser negativo");
		}

		Coche coche = repositorio.getById(idCoche);
		Mantenimiento mantenimiento = new Mantenimiento(fechaMantenimiento, kilometraje, cambioAceite,
				cambioLiquidoFrenos, cambioFiltros, descripcion, precio);

		if (coche.getFechaUltimoMantenimiento() == null
				|| coche.getFechaUltimoMantenimiento().isBefore(fechaMantenimiento)) {
			coche.setFechaUltimoMantenimiento(fechaMantenimiento);
		}
		if (coche.getMantenimientos() == null) {
			coche.setMantenimientos(new ArrayList<Mantenimiento>());
		}
		coche.getMantenimientos().add(mantenimiento);

		repositorio.update(coche);
	}

	@Override
	public List<CocheResumen> getCochesConMantenimientoCercano(LocalDate fecha) throws RepositorioException {

		List<Coche> coches;
		List<CocheResumen> fichas = new ArrayList<>();

		if (fecha != null) {
			coches = repositorio.getCochesConMantenimientoCercano(fecha);
		} else {
			coches = repositorio.getAll();
		}

		for (Coche c : coches) {
			CocheResumen ficha = new CocheResumen(c.getId(), c.getMatricula(), c.getModelo(), c.getFechaCompra(),
					c.getContacto().getNombre(), c.getContacto().getEmail(), c.getFechaUltimoMantenimiento());
			fichas.add(ficha);
		}
		return fichas;
	}

	@Override
	public void exportarCocheJSON(String matricula, String ruta) throws RepositorioException {
		List<Coche> cochesByMatricula = repositorio.getCochesByMatricula(matricula);

	}
}
