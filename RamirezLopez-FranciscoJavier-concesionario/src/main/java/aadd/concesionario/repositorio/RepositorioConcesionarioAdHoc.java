package aadd.concesionario.repositorio;

import java.time.LocalDate;
import java.util.List;

import aadd.concesionario.modelo.Coche;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Coche).
 */
public interface RepositorioConcesionarioAdHoc extends RepositorioString<Coche> {

	List<Coche> getCochesConMantenimientoCercano(LocalDate fecha) throws RepositorioException;

	List<Coche> getCochesByMatricula(String matricula) throws RepositorioException;

}