package aadd.concesionario.servicio.test;

import java.time.LocalDate;
import java.util.List;

import aadd.concesionario.modelo.Coche;
import aadd.concesionario.repositorio.EntidadNoEncontrada;
import aadd.concesionario.repositorio.FactoriaRepositorios;
import aadd.concesionario.repositorio.RepositorioConcesionarioAdHoc;
import aadd.concesionario.repositorio.RepositorioException;
import aadd.concesionario.servicio.CocheResumen;
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
		servicio.altaCoche("5678DEF", "Honda Civic", LocalDate.of(2021, 3, 10), "María López",
				"Avenida Siempre Viva 456", "600600601", "email2@prueba.com");
		servicio.altaCoche("9012GHI", "Ford Focus", LocalDate.of(2022, 7, 20), "Pedro García", "Plaza Mayor 789",
				"600600602", "email3@prueba.com");
		servicio.altaCoche("3456JKL", "Chevrolet Cruze", LocalDate.of(2021, 2, 5), "Laura Martínez",
				"Calle del Sol 321", "600600603", "email4@prueba.com");
		servicio.altaCoche("7890MNO", "Volkswagen Golf", LocalDate.of(2021, 4, 15), "Carlos Sánchez",
				"Avenida del Mar 654", "600600604", "email5@prueba.com");

		servicio.nuevoMantenimiento(id1, LocalDate.of(2023, 1, 10), 15000, true, false, true,
				"Cambio de aceite y filtros", 200.0f);
		servicio.nuevoMantenimiento(id1, LocalDate.of(2023, 3, 15), 18000, false, true, true,
				"Cambio de líquido de frenos y filtros", 300.0f);

		List<CocheResumen> fichas1 = servicio.getCochesConMantenimientoCercano(null);
		List<CocheResumen> fichas2 = servicio.getCochesConMantenimientoCercano(LocalDate.of(2023, 1, 1));

		System.out.println("Fichas de coches con mantenimiento cercano (sin fecha):");
		for (CocheResumen ficha : fichas1) {
			System.out.println(ficha.toString());
		}

		System.out.println("\nFichas de coches con mantenimiento cercano (a partir del 2023-01-01):");
		for (CocheResumen ficha : fichas2) {
			System.out.println(ficha.toString());
		}
	}
}
