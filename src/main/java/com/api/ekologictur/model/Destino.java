package com.api.ekologictur.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Destino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDestino;
	
	private String nomeDestino;
	
	private String localidade;//pais, estado, provincia
	
	private String tipoDestino;//nacional ou internacional
	
	@ManyToOne
	@JoinColumn(name = "pacote_id")
	private Pacote pacote;
	
	public Destino() {
		
	}
	
	public Destino(Long idDestino, String nomeDestino, String localidade, String tipoDestino, Pacote pacote) {
		super();
		this.idDestino = idDestino;
		this.nomeDestino = nomeDestino;
		this.localidade = localidade;
		this.tipoDestino = tipoDestino;
		this.pacote = pacote;
	}

	public Long getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}

	public String getNomeDestino() {
		return nomeDestino;
	}

	public void setNomeDestino(String nomeDestino) {
		this.nomeDestino = nomeDestino;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getTipoDestino() {
		return tipoDestino;
	}

	public void setTipoDestino(String tipoDestino) {
		this.tipoDestino = tipoDestino;
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDestino, localidade, nomeDestino, pacote, tipoDestino);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destino other = (Destino) obj;
		return Objects.equals(idDestino, other.idDestino) && Objects.equals(localidade, other.localidade)
				&& Objects.equals(nomeDestino, other.nomeDestino) && Objects.equals(pacote, other.pacote)
				&& Objects.equals(tipoDestino, other.tipoDestino);
	}

	@Override
	public String toString() {
		return "Destino [idDestino=" + idDestino + ", nomeDestino=" + nomeDestino + ", localidade=" + localidade
				+ ", tipoDestino=" + tipoDestino + ", pacote=" + pacote + "]";
	}
	
	

}
