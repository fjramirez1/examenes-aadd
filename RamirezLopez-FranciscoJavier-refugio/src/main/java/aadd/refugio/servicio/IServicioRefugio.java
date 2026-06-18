package aadd.refugio.servicio;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import aadd.refugio.repositorio.EntidadNoEncontrada;
import aadd.refugio.repositorio.RepositorioException;

public interface IServicioRefugio {

	String nuevoAnimal(String nombre, String especie, int edad, LocalDateTime fechaEntrada) throws RepositorioException;

	void addRevision(String id, LocalDateTime fechaRevision, float peso, String observaciones)
			throws RepositorioException, EntidadNoEncontrada;

	void addAdopcion(String id, String nombreAdoptante, String telefono, LocalDateTime fechaAdopcion)
			throws RepositorioException, EntidadNoEncontrada;

	List<AnimalResumen> recuperarAnimales(Integer anyos) throws RepositorioException;

	void exportarAnimalJSON(String id, String rutaFichero)
			throws RepositorioException, EntidadNoEncontrada, IOException;

	List<AnimalResumen> getAnimales() throws RepositorioException;

}
