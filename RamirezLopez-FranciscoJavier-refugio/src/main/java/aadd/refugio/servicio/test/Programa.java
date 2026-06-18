package aadd.refugio.servicio.test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import aadd.refugio.modelo.Animal;
import aadd.refugio.modelo.Revision;
import aadd.refugio.repositorio.EntidadNoEncontrada;
import aadd.refugio.repositorio.FactoriaRepositorios;
import aadd.refugio.repositorio.RepositorioAnimalAdHoc;
import aadd.refugio.repositorio.RepositorioException;
import aadd.refugio.servicio.AnimalResumen;
import aadd.refugio.servicio.FactoriaServicios;
import aadd.refugio.servicio.IServicioRefugio;

public class Programa {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada, IOException {

		IServicioRefugio servicioRefugio = FactoriaServicios.getServicio(IServicioRefugio.class);
		RepositorioAnimalAdHoc repositorio = FactoriaRepositorios.getRepositorio(Animal.class);

		// Eliminar todos los podcasts existentes
		List<Animal> animalesExistentes = repositorio.getAll();
		for (Animal animal : animalesExistentes) {
			repositorio.delete(animal);
		}

		String id1 = servicioRefugio.nuevoAnimal("Animal1", "Perro", 4,
				LocalDateTime.now().minusYears(1).minusMonths(1));
		String id2 = servicioRefugio.nuevoAnimal("Animal2", "Perro", 6,
				LocalDateTime.now().minusYears(2).minusMonths(1));
		String id3 = servicioRefugio.nuevoAnimal("Animal3", "Perro", 8,
				LocalDateTime.now().minusYears(3).minusMonths(1));
		servicioRefugio.nuevoAnimal("Animal4", "Perro", 8, LocalDateTime.now().minusYears(3).minusMonths(1));

		servicioRefugio.addRevision(id1, LocalDateTime.now().minusMonths(8), 15, "Todo correcto");
		servicioRefugio.addRevision(id1, LocalDateTime.now().minusMonths(4), 15, "Todo correcto");
		servicioRefugio.addRevision(id1, LocalDateTime.now().minusMonths(2), 15, "Todo correcto");

		System.out.println("\nRevisiones de Animal1:");
		for (Revision r : repositorio.getById(id1).getRevisiones()) {
			System.out.println(r.toString());
		}

		servicioRefugio.addAdopcion(id1, "Javi", "123456789", LocalDateTime.now().minusMonths(1));
		servicioRefugio.addAdopcion(id2, "Javi", "123456789", LocalDateTime.now().minusMonths(2));
		servicioRefugio.addAdopcion(id3, "Javi", "123456789", LocalDateTime.now().minusMonths(3));

		List<AnimalResumen> resumenTodos = servicioRefugio.recuperarAnimales(null);
		List<AnimalResumen> resumenConAntiguedadMinimaSinAdoptar = servicioRefugio.recuperarAnimales(2);

		System.out.println("\nResumen de todos los animales:");
		for (AnimalResumen animalResumen : resumenTodos) {
			System.out.println(animalResumen.toString());
		}

		System.out.println("\nResumen de los animales sin adoptar que llevan más de 2 años en el refugio:");
		for (AnimalResumen animalResumen : resumenConAntiguedadMinimaSinAdoptar) {
			System.out.println(animalResumen.toString());
		}
		
		servicioRefugio.exportarAnimalJSON(id1, "animal1.json");
	}

}
