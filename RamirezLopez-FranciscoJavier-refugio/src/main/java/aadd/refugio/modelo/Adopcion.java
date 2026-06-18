package aadd.refugio.modelo;

import java.time.LocalDateTime;

public class Adopcion {

	private LocalDateTime fechaAdopcion;
	private String nombreAdoptante;
	private String telefono;

	public Adopcion() {
	}

	public Adopcion(LocalDateTime fechaAdopcion, String nombreAdoptante, String telefono) {
		this.fechaAdopcion = fechaAdopcion;
		this.nombreAdoptante = nombreAdoptante;
		this.telefono = telefono;
	}

	public LocalDateTime getFechaAdopcion() {
		return fechaAdopcion;
	}

	public void setFechaAdopcion(LocalDateTime fechaAdopcion) {
		this.fechaAdopcion = fechaAdopcion;
	}

	public String getNombreAdoptante() {
		return nombreAdoptante;
	}

	public void setNombreAdoptante(String nombreAdoptante) {
		this.nombreAdoptante = nombreAdoptante;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Adopcion [fechaAdopcion=" + fechaAdopcion + ", nombreAdoptante=" + nombreAdoptante + ", telefono="
				+ telefono + "]";
	}

}
