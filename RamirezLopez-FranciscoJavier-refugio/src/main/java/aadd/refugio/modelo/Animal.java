package aadd.refugio.modelo;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import aadd.refugio.repositorio.Identificable;

public class Animal implements Identificable {

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;
	private String nombre;
	private String especie;
	private int edadEntrada;
	private LocalDateTime fechaEntrada;
	private List<Revision> revision;
	private Adopcion adopcion;

	public Animal() {
	}

	public Animal(String nombre, String especie, int edadEntrada, LocalDateTime fechaEntrada) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.edadEntrada = edadEntrada;
		this.fechaEntrada = fechaEntrada;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getEdadEntrada() {
		return edadEntrada;
	}

	public void setEdadEntrada(int edadEntrada) {
		this.edadEntrada = edadEntrada;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public List<Revision> getRevisiones() {
		return revision;
	}

	public void setRevisiones(List<Revision> revision) {
		this.revision = revision;
	}

	public Adopcion getAdopcion() {
		return adopcion;
	}

	public void setAdopcion(Adopcion adopcion) {
		this.adopcion = adopcion;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", edadEntrada=" + edadEntrada
				+ ", fechaEntrada=" + fechaEntrada + ", revisiones=" + revision + ", adopcion=" + adopcion + "]";
	}

}
