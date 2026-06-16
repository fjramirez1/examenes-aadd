package aadd.concesionario.repositorio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;

import aadd.concesionario.modelo.Coche;

public class RepositorioConcesionarioAdHocMongoDB extends RepositorioConcesionarioMongoDB
		implements RepositorioConcesionarioAdHoc {

	public RepositorioConcesionarioAdHocMongoDB() throws IOException {
		super();
	}

	@Override
	public List<Coche> getCochesConMantenimientoCercano(LocalDate fecha) throws RepositorioException {

		try {
			LocalDate fechaLimite = fecha.minusMonths(11);

			// El coche se compró hace 11 meses o más respecto a la fecha indicada
			Bson comprasAntiguas = Filters.lte("fechaCompra", fechaLimite);

			// No ha tenido mantenimiento en los últimos 11 meses (o nunca lo ha tenido)
			Bson sinMantenimientoReciente = Filters.or(Filters.eq("fechaUltimoMantenimiento", null),
					Filters.lt("fechaUltimoMantenimiento", fechaLimite));

			Bson filtro = Filters.and(comprasAntiguas, sinMantenimientoReciente);

			return getCollection().find(filtro).into(new ArrayList<Coche>());

		} catch (Exception e) {
			throw new RepositorioException("Error recuperando coches con mantenimiento cercano: ", e);
		}
	}

	@Override
	public List<Coche> getCochesByMatricula(String matricula) throws RepositorioException {

		try {
			Bson filtro = Filters.eq("matricula", matricula);
			return getCollection().find(filtro).into(new ArrayList<Coche>());

		} catch (Exception e) {
			throw new RepositorioException("Error recuperando coches por matrícula: ", e);
		}
	}

}