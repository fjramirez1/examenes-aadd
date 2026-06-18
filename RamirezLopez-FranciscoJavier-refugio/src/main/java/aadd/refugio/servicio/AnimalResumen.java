package aadd.refugio.servicio;

import java.time.LocalDateTime;

public class AnimalResumen {
	private String id;
	private String nombre;
	private String especie;
	private LocalDateTime fechaEntrada;
	private boolean adopcion;

	public AnimalResumen() {
	}

	public AnimalResumen(String id, String nombre, String especie, LocalDateTime fechaEntrada, boolean adopcion) {
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.fechaEntrada = fechaEntrada;
		this.adopcion = adopcion;
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

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public boolean isAdopcion() {
		return adopcion;
	}

	public void setAdopcion(boolean adopcion) {
		this.adopcion = adopcion;
	}

	@Override
	public String toString() {
		return "AnimalResumen [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", fechaEntrada="
				+ fechaEntrada + ", adopcion=" + adopcion + "]";
	}

}
