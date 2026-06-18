package aadd.refugio.modelo;

import java.time.LocalDateTime;

public class Revision {

	private LocalDateTime fecha;
	private String observaciones;
	private float peso;

	public Revision() {
	}

	public Revision(LocalDateTime fecha, String observaciones, float peso) {
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.peso = peso;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Revision [fecha=" + fecha + ", observaciones=" + observaciones + ", peso=" + peso + "]";
	}
}
