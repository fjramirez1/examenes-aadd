package aadd.refugio.servicio;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;

import aadd.refugio.modelo.Adopcion;
import aadd.refugio.modelo.Animal;
import aadd.refugio.modelo.Revision;
import aadd.refugio.repositorio.EntidadNoEncontrada;
import aadd.refugio.repositorio.FactoriaRepositorios;
import aadd.refugio.repositorio.RepositorioAnimalAdHoc;
import aadd.refugio.repositorio.RepositorioException;

@ApplicationScoped
public class ServicioRefugio implements IServicioRefugio {

	private RepositorioAnimalAdHoc repositorio = FactoriaRepositorios.getRepositorio(Animal.class);

	@Override
	public String nuevoAnimal(String nombre, String especie, int edad, LocalDateTime fechaEntrada)
			throws RepositorioException {

		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		if (especie == null || especie.isEmpty())
			throw new IllegalArgumentException("especie: no debe ser nulo ni vacio");
		if (edad < 0)
			throw new IllegalArgumentException("edad: no debe ser negativa");
		if (fechaEntrada == null)
			throw new IllegalArgumentException("fechaEntrada: no debe ser nulo");
		if (fechaEntrada.isAfter(LocalDateTime.now()))
			throw new IllegalArgumentException("fechaEntrada: no debe ser posterior a la fecha actual");

		Animal animal = new Animal(nombre, especie, edad, fechaEntrada);
		return repositorio.add(animal);
	}

	@Override
	public void addRevision(String id, LocalDateTime fechaRevision, float peso, String observaciones)
			throws RepositorioException, EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		if (fechaRevision == null)
			throw new IllegalArgumentException("fechaRevision: no debe ser nulo");
		if (fechaRevision.isAfter(LocalDateTime.now()))
			throw new IllegalArgumentException("fechaRevision: no debe ser posterior a la fecha actual");
		if (peso < 0)
			throw new IllegalArgumentException("peso: no debe ser negativo");
		if (observaciones == null || observaciones.isEmpty())
			throw new IllegalArgumentException("observaciones: no debe ser nulo ni vacio");

		Animal animal = repositorio.getById(id);
		Revision revision = new Revision(fechaRevision, observaciones, peso);
		if (animal.getRevisiones() == null)
			animal.setRevisiones(new ArrayList<Revision>());
		animal.getRevisiones().add(revision);

		repositorio.update(animal);
	}

	@Override
	public void addAdopcion(String id, String nombreAdoptante, String telefono, LocalDateTime fechaAdopcion)
			throws RepositorioException, EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		if (nombreAdoptante == null || nombreAdoptante.isEmpty())
			throw new IllegalArgumentException("nombreAdoptante: no debe ser nulo ni vacio");
		if (telefono == null || telefono.isEmpty())
			throw new IllegalArgumentException("telefono: no debe ser nulo ni vacio");
		if (fechaAdopcion == null)
			throw new IllegalArgumentException("fechaAdopcion: no debe ser nulo");
		if (fechaAdopcion.isAfter(LocalDateTime.now()))
			throw new IllegalArgumentException("fechaAdopcion: no debe ser posterior a la fecha actual");

		Animal animal = repositorio.getById(id);
		Adopcion adopcion = new Adopcion(fechaAdopcion, nombreAdoptante, telefono);
		animal.setAdopcion(adopcion);

		repositorio.update(animal);
	}

	@Override
	public List<AnimalResumen> recuperarAnimales(Integer anyos) throws RepositorioException {
		List<Animal> animales;
		if (anyos == null) {
			animales = repositorio.getAll();
		} else {
			animales = repositorio.buscarAnimalesNoAdoptadosConAntiguedadMinima(anyos);
		}

		return convertirAResumen(animales);
	}

	@Override
	public void exportarAnimalJSON(String id, String rutaFichero)
			throws RepositorioException, EntidadNoEncontrada, IOException {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacío");
		if (rutaFichero == null || rutaFichero.isEmpty())
			throw new IllegalArgumentException("rutaFichero: no debe ser nula ni vacía");

		Animal animal = repositorio.getById(id);

		Path ruta = Path.of(rutaFichero);

		if (ruta.getParent() != null) {
			Files.createDirectories(ruta.getParent());
		}

		JsonbConfig config = new JsonbConfig().withFormatting(true).withNullValues(true)
				.withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);
		Jsonb jsonb = JsonbBuilder.create(config);
		Writer writer = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8);

		jsonb.toJson(animal, writer);
	}

	@Override
	public List<AnimalResumen> getAnimales() throws RepositorioException {
		List<Animal> animales = repositorio.getAll();
		return convertirAResumen(animales);
	}

	private List<AnimalResumen> convertirAResumen(List<Animal> animales) {
		List<AnimalResumen> animalesResumen = new ArrayList<>();
		AnimalResumen animalResumen;
		for (Animal animal : animales) {
			animalResumen = new AnimalResumen();
			animalResumen.setAdopcion(animal.getAdopcion() == null ? false : true);
			animalResumen.setEspecie(animal.getEspecie());
			animalResumen.setFechaEntrada(animal.getFechaEntrada());
			animalResumen.setId(animal.getId());
			animalResumen.setNombre(animal.getNombre());
			animalesResumen.add(animalResumen);
		}
		return animalesResumen;
	}
}
