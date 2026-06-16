package aadd.concesionario.repositorio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.bson.Document;
import com.mongodb.client.MongoCollection;

import aadd.concesionario.modelo.Coche;

public class RepositorioConcesionarioAdHocMongoDB extends RepositorioConcesionarioMongoDB
		implements RepositorioConcesionarioAdHoc {

	private MongoCollection<Document> coleccionSinCodificar;

	public RepositorioConcesionarioAdHocMongoDB() throws IOException {
		super();
		coleccionSinCodificar = database.getCollection("coches");
	}

	@Override
	public List<Coche> getCochesConMantenimientoCercano(LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}