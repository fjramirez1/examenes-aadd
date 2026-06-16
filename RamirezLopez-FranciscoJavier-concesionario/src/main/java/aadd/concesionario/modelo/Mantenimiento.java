package aadd.concesionario.modelo;

import java.time.LocalDate;

public class Mantenimiento {
	private LocalDate fecha;
	private int kilometros;
	private boolean cambioAceite;
	private boolean cambioLiquidoFrenos;
	private boolean cambioFiltros;
	private String descripcion;
	private float precio;

	public Mantenimiento() {
	}

	public Mantenimiento(LocalDate fecha, int kilometros, boolean cambioAceite, boolean cambioLiquidoFrenos,
			boolean cambioFiltros, String descripcion, float precio) {
		this.fecha = fecha;
		this.kilometros = kilometros;
		this.cambioAceite = cambioAceite;
		this.cambioLiquidoFrenos = cambioLiquidoFrenos;
		this.cambioFiltros = cambioFiltros;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getKilometros() {
		return kilometros;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public boolean isCambioAceite() {
		return cambioAceite;
	}

	public void setCambioAceite(boolean cambioAceite) {
		this.cambioAceite = cambioAceite;
	}

	public boolean isCambioLiquidoFrenos() {
		return cambioLiquidoFrenos;
	}

	public void setCambioLiquidoFrenos(boolean cambioLiquidoFrenos) {
		this.cambioLiquidoFrenos = cambioLiquidoFrenos;
	}

	public boolean isCambioFiltros() {
		return cambioFiltros;
	}

	public void setCambioFiltros(boolean cambioFiltros) {
		this.cambioFiltros = cambioFiltros;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Mantenimiento [fecha=" + fecha + ", kilometros=" + kilometros + ", cambioAceite=" + cambioAceite
				+ ", cambioLiquidoFrenos=" + cambioLiquidoFrenos + ", cambioFiltros=" + cambioFiltros + ", descripcion="
				+ descripcion + ", precio=" + precio + "]";
	}

}
