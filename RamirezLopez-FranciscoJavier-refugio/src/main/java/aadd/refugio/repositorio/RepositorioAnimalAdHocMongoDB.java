package aadd.refugio.repositorio;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;

import aadd.refugio.modelo.Animal;

public class RepositorioAnimalAdHocMongoDB extends RepositorioAnimalMongoDB implements RepositorioAnimalAdHoc {

	public RepositorioAnimalAdHocMongoDB() throws IOException {
		super();
	}

	@Override
	public List<Animal> buscarAnimalesNoAdoptadosConAntiguedadMinima(int anyos) {
		if (anyos < 0) {
			throw new IllegalArgumentException("anyos: no debe ser negativo");
		}

		LocalDateTime fechaLimite = LocalDateTime.now().minusYears(anyos);

		Bson animalesAntiguos = Filters.lte("fechaEntrada", fechaLimite);

		Bson animalesSinAdoptar = Filters.eq("adopcion", null);

		Bson filtro = Filters.and(animalesAntiguos, animalesSinAdoptar);

		return getCollection().find(filtro).into(new ArrayList<Animal>());
	}
}