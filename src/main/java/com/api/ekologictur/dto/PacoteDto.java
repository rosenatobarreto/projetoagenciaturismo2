package com.api.ekologictur.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.ekologictur.model.Pacote;

@Component
public class PacoteDto {
	
	private Long idPacote;
	private String nomePacote;
	private String epoca;
	private double preco;
	private Integer periodoEmDias;
	
	public PacoteDto() {
		
	}


	public PacoteDto(Pacote pacote) {
		super();
		this.idPacote = pacote.getIdPacote();
		this.nomePacote = pacote.getNomePacote();
		this.epoca = pacote.getEpoca();
		this.preco = pacote.getPreco();
		this.periodoEmDias = pacote.getPeriodoEmDias();
	}
	
	public static List<PacoteDto> convert(List<Pacote> pacote){
		return pacote.stream().map(PacoteDto::new).collect(Collectors.toList());
	  
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


	public void setPeriodoEmDias(Integer periodoEmDias) {
		this.periodoEmDias = periodoEmDias;
	}


	@Override
	public int hashCode() {
		return Objects.hash(epoca, idPacote, nomePacote, periodoEmDias, preco);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacoteDto other = (PacoteDto) obj;
		return Objects.equals(epoca, other.epoca) && Objects.equals(idPacote, other.idPacote)
				&& Objects.equals(nomePacote, other.nomePacote) && Objects.equals(periodoEmDias, other.periodoEmDias)
				&& Double.doubleToLongBits(preco) == Double.doubleToLongBits(other.preco);
	}
	 
	 

}
