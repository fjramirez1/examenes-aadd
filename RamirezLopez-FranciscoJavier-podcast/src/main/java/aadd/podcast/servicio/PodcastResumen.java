package aadd.podcast.servicio;

import java.util.Date;
import java.util.List;

public class PodcastResumen {

	private String id;
	private String titulo;
	private List<String> autores;
	private String categoria;
	private String descripcion;
	private Date fechaUltimoEpisodio;
	private int numEpisodios;

	public PodcastResumen() {
	}

	public PodcastResumen(String id, String titulo, List<String> autores, String categoria, String descripcion,
			Date fechaUltimoEpisodio, int numEpisodios) {
		this.id = id;
		this.titulo = titulo;
		this.autores = autores;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaUltimoEpisodio = fechaUltimoEpisodio;
		this.numEpisodios = numEpisodios;
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

	public List<String> getAutores() {
		return autores;
	}

	public void setAutores(List<String> autores) {
		this.autores = autores;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaUltimoEpisodio() {
		return fechaUltimoEpisodio;
	}

	public void setFechaUltimoEpisodio(Date fechaUltimoEpisodio) {
		this.fechaUltimoEpisodio = fechaUltimoEpisodio;
	}

	public int getNumEpisodios() {
		return numEpisodios;
	}

	public void setNumEpisodios(int numEpisodios) {
		this.numEpisodios = numEpisodios;
	}

	@Override
	public String toString() {
		return "PodcastResumen [id=" + id + ", titulo=" + titulo + ", autores=" + autores + ", categoria=" + categoria
				+ ", descripcion=" + descripcion + ", fechaUltimoEpisodio=" + fechaUltimoEpisodio + ", numEpisodios="
				+ numEpisodios + "]";
	}

}
