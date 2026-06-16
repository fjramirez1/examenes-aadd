package aadd.concesionario.servicio;

import java.time.LocalDate;
import java.util.Optional;

public class CocheResumen {
	private String idCoche;
	private String matricula;
	private String modelo;
	private LocalDate fechaCompra;
	private String nombreContacto;
	private String emailContacto;
	private Optional<LocalDate> fechaUltimoMantenimiento;

	public CocheResumen() {
	}

	public CocheResumen(String idCoche, String matricula, String modelo, LocalDate fechaCompra, String nombreContacto,
			String emailContacto, Optional<LocalDate> fechaUltimoMantenimiento) {
		this.idCoche = idCoche;
		this.matricula = matricula;
		this.modelo = modelo;
		this.fechaCompra = fechaCompra;
		this.nombreContacto = nombreContacto;
		this.emailContacto = emailContacto;
		this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
	}

	public String getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(String idCoche) {
		this.idCoche = idCoche;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public Optional<LocalDate> getFechaUltimoMantenimiento() {
		return fechaUltimoMantenimiento;
	}

	public void setFechaUltimoMantenimiento(Optional<LocalDate> fechaUltimoMantenimiento) {
		this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
	}

	@Override
	public String toString() {
		return "CocheResumen [idCoche=" + idCoche + ", matricula=" + matricula + ", modelo=" + modelo + ", fechaCompra="
				+ fechaCompra + ", nombreContacto=" + nombreContacto + ", emailContacto=" + emailContacto
				+ ", fechaUltimoMantenimiento=" + fechaUltimoMantenimiento + "]";
	}

}
