package aadd.podcast.modelo;

import java.util.Date;

public class Episodio {
	private int numEpisodio;
	private String titulo;
	private Date fechaPublicacion;
	private int duracionMinutos;
	private String descripcion;

	public Episodio() {
	}

	public Episodio(int numEpisodio, String titulo, Date fechaPublicacion, int duracionMinutos, String descripcion) {
		this.numEpisodio = numEpisodio;
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.duracionMinutos = duracionMinutos;
		this.descripcion = descripcion;
	}

	public int getNumEpisodio() {
		return numEpisodio;
	}

	public void setNumEpisodio(int numEpisodio) {
		this.numEpisodio = numEpisodio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Episodio [numEpisodio=" + numEpisodio + ", titulo=" + titulo + ", fechaPublicacion=" + fechaPublicacion
				+ ", duracionMinutos=" + duracionMinutos + ", descripcion=" + descripcion + "]";
	}

}
