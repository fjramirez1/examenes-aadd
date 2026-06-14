package aadd.podcast.modelo;

import java.util.Date;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import aadd.podcast.repositorio.Identificable;

public class Podcast implements Identificable {

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;
	private String titulo;
	private String descripcion;
	private List<String> autores;
	private String categoria;
	private Date fechaUltimoEpisodio;
	private List<Episodio> episodios;

	public Podcast() {
	}

	public Podcast(String titulo, String descripcion, List<String> autores, String categoria) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.autores = autores;
		this.categoria = categoria;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Date getFechaUltimoEpisodio() {
		return fechaUltimoEpisodio;
	}

	public void setFechaUltimoEpisodio(Date fechaUltimoEpisodio) {
		this.fechaUltimoEpisodio = fechaUltimoEpisodio;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		this.episodios = episodios;
	}

	@Override
	public String toString() {
		return "Podcast [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", autores=" + autores
				+ ", categoria=" + categoria + ", fechaUltimoEpisodio=" + fechaUltimoEpisodio + ", episodios="
				+ episodios + "]";
	}

	public int getNumEpisodios() {
		if (episodios == null) {
			return 0;
		}
		return episodios.size();
	}

}
