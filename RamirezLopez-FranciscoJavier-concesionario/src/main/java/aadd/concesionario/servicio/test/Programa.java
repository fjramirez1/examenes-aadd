package aadd.concesionario.servicio.test;

import java.time.LocalDate;

import aadd.concesionario.modelo.Coche;
import aadd.concesionario.repositorio.EntidadNoEncontrada;
import aadd.concesionario.repositorio.FactoriaRepositorios;
import aadd.concesionario.repositorio.RepositorioConcesionarioAdHoc;
import aadd.concesionario.repositorio.RepositorioException;
import aadd.concesionario.servicio.FactoriaServicios;
import aadd.concesionario.servicio.IServicioConcesionario;

public class Programa {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada {

		IServicioConcesionario servicio = FactoriaServicios.getServicio(IServicioConcesionario.class);
		RepositorioConcesionarioAdHoc repositorio = FactoriaRepositorios.getRepositorio(Coche.class);

		// Limpiar la base de datos antes de ejecutar el programa
		for (Coche coche : repositorio.getAll()) {
			repositorio.delete(coche);
		}

		String id1 = servicio.altaCoche("1234ABC", "Toyota Corolla", LocalDate.of(2020, 5, 15), "Juan Pérez",
				"Calle Falsa 123", "600600600", "email1@prueba.com");

		String id2 = servicio.altaCoche("5678DEF", "Honda Civic", LocalDate.of(2021, 3, 10), "María López",
				"Avenida Siempre Viva 456", "600600601", "email2@prueba.com");

		servicio.nuevoMantenimiento(id1, LocalDate.of(2023, 1, 10), 15000, true, false, true,
				"Cambio de aceite y filtros", 200.0f);
		servicio.nuevoMantenimiento(id2, LocalDate.of(2023, 2, 20), 20000, true, true, false,
				"Cambio de aceite y líquido de frenos", 250.0f);
		servicio.nuevoMantenimiento(id1, LocalDate.of(2023, 3, 15), 18000, false, true, true,
				"Cambio de líquido de frenos y filtros", 300.0f);

	}

}
