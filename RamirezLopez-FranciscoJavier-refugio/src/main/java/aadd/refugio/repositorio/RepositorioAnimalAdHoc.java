package aadd.refugio.repositorio;

import java.util.List;

import aadd.refugio.modelo.Animal;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Animal).
 */
public interface RepositorioAnimalAdHoc extends RepositorioString<Animal> {

	List<Animal> buscarAnimalesNoAdoptadosConAntiguedadMinima(int anyos);
}