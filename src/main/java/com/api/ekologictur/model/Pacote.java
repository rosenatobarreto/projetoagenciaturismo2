package com.api.ekologictur.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pacote implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPacote;
	private String nomePacote;
	private String epoca;
	private double preco;
	private int periodoEmDias;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pacote")
	private List<Destino> destinos = new ArrayList<>();
	
	public Pacote() {
		
	}

	public Pacote(String nomePacote, String epoca, double preco, int periodoEmDias) {
		super();
		
		this.nomePacote = nomePacote;
		this.epoca = epoca;
		this.preco = preco;
		this.periodoEmDias = periodoEmDias;
		
	}

	public Long getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(Long idPacote) {
		this.idPacote = idPacote;
	}

	public String getNomePacote() {
		return nomePacote;
	}

	public void setNomePacote(String nomePacote) {
		this.nomePacote = nomePacote;
	}

	public String getEpoca() {
		return epoca;
	}

	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getPeriodoEmDias() {
		return periodoEmDias;
	}

	public void setPeriodoEmDias(int periodoEmDias) {
		this.periodoEmDias = periodoEmDias;
	}

	public List<Destino> getDestinos() {
		return destinos;
	}

	public void setDestinos(List<Destino> destinos) {
		this.destinos = destinos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destinos, epoca, idPacote, nomePacote, periodoEmDias, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pacote other = (Pacote) obj;
		return Objects.equals(destinos, other.destinos) && Objects.equals(epoca, other.epoca)
				&& Objects.equals(idPacote, other.idPacote) && Objects.equals(nomePacote, other.nomePacote)
				&& periodoEmDias == other.periodoEmDias
				&& Double.doubleToLongBits(preco) == Double.doubleToLongBits(other.preco);
	}

	@Override
	public String toString() {
		return "Pacote [idPacote=" + idPacote + ", nomePacote=" + nomePacote + ", epoca=" + epoca + ", preco=" + preco
				+ ", periodoEmDias=" + periodoEmDias + ", destinos=" + destinos + "]";
	}
	
}
