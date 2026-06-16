package aadd.concesionario.servicio;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

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
	public void exportarCocheJSON(String matricula, String ruta) throws RepositorioException, IOException {

		List<Coche> cochesByMatricula = repositorio.getCochesByMatricula(matricula);

		if (cochesByMatricula.isEmpty()) {
			throw new RepositorioException("No existe ningún coche con la matrícula: " + matricula);
		}

		Coche coche = cochesByMatricula.get(0);

		JsonObject cocheJSON = crearCocheJSON(coche);

		// Almacenamiento en disco
		HashMap<String, Boolean> config = new HashMap<String, Boolean>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);

		JsonGenerator generador = factoriaGeneradores.createGenerator(new FileWriter(ruta));
		generador.write(cocheJSON);
		generador.close();
	}

	private static JsonObject crearMantenimientoJSON(Mantenimiento m) {
		return Json.createObjectBuilder().add("fecha", m.getFecha().toString()).add("kilometros", m.getKilometros())
				.add("cambioAceite", m.isCambioAceite()).add("cambioLiquidoFrenos", m.isCambioLiquidoFrenos())
				.add("cambioFiltros", m.isCambioFiltros()).add("descripcion", m.getDescripcion())
				.add("precio", m.getPrecio()).build();
	}

	private static JsonArray crearMantenimientosJSON(List<Mantenimiento> mantenimientos) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		if (mantenimientos != null) {
			for (Mantenimiento m : mantenimientos) {
				builder.add(crearMantenimientoJSON(m));
			}
		}
		return builder.build();
	}

	private static JsonObject crearCocheJSON(Coche coche) {
		JsonObjectBuilder builder = Json.createObjectBuilder().add("id", coche.getId())
				.add("matricula", coche.getMatricula()).add("modelo", coche.getModelo())
				.add("fechaCompra", coche.getFechaCompra().toString());

		if (coche.getFechaUltimoMantenimiento() != null) {
			builder.add("fechaUltimoMantenimiento", coche.getFechaUltimoMantenimiento().toString());
		} else {
			builder.add("fechaUltimoMantenimiento", JsonValue.NULL);
		}

		builder.add("mantenimientos", crearMantenimientosJSON(coche.getMantenimientos()));

		return builder.build();
	}
}
