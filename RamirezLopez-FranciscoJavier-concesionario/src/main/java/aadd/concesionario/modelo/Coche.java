package aadd.concesionario.modelo;

import java.time.LocalDate;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import aadd.concesionario.repositorio.Identificable;

public class Coche implements Identificable {
	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;
	private String matricula;
	private String modelo;
	private LocalDate fechaCompra;
	private LocalDate fechaUltimoMantenimiento;
	private Contacto contacto;
	private List<Mantenimiento> mantenimientos;

	public Coche() {
	}

	public Coche(String matricula, String modelo, LocalDate fechaCompra, Contacto contacto) {
		this.matricula = matricula;
		this.modelo = modelo;
		this.fechaCompra = fechaCompra;
		this.contacto = contacto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public LocalDate getFechaUltimoMantenimiento() {
		return fechaUltimoMantenimiento;
	}

	public void setFechaUltimoMantenimiento(LocalDate fechaUltimoMantenimiento) {
		this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public List<Mantenimiento> getMantenimientos() {
		return mantenimientos;
	}

	public void setMantenimientos(List<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", fechaCompra=" + fechaCompra
				+ ", fechaUltimoMantenimiento=" + fechaUltimoMantenimiento + ", contacto=" + contacto
				+ ", mantenimientos=" + mantenimientos + "]";
	}

}
