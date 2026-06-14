package aadd.podcast.servicio;

/**
 * DTO que representa el resumen de un podcast con la duración media (en
 * minutos) de todos sus episodios.
 */
public class PodcastDuracionMedia {

	private String id;
	private String titulo;
	private double duracionMediaMinutos;

	public PodcastDuracionMedia() {
	}

	public PodcastDuracionMedia(String id, String titulo, double duracionMediaMinutos) {
		this.id = id;
		this.titulo = titulo;
		this.duracionMediaMinutos = duracionMediaMinutos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getDuracionMediaMinutos() {
		return duracionMediaMinutos;
	}

	public void setDuracionMediaMinutos(double duracionMediaMinutos) {
		this.duracionMediaMinutos = duracionMediaMinutos;
	}

	@Override
	public String toString() {
		return "PodcastDuracionMedia [id=" + id + ", titulo=" + titulo + ", duracionMediaMinutos="
				+ duracionMediaMinutos + "]";
	}
}