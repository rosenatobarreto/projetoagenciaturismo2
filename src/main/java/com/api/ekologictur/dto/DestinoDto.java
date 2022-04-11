package com.api.ekologictur.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.api.ekologictur.model.Destino;
import com.api.ekologictur.model.Pacote;

@Component
public class DestinoDto {
	
private Long idDestino;
	
	private String nomeDestino;
	private String localidade;
	private String tipoDestino;
	private Pacote pacote;
	
	public DestinoDto(Destino destino) {
		super();
		this.idDestino = destino.getIdDestino();
		this.nomeDestino = destino.getNomeDestino();
		this.localidade = destino.getLocalidade();
		this.tipoDestino = destino.getTipoDestino();
		this.pacote = destino.getPacote();
	}
	
	public DestinoDto() {
	}

	@Bean
	public static List<DestinoDto> convert(List<Destino> destino){
		return destino.stream().map(DestinoDto::new).collect(Collectors.toList());
	  
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
	
	

}
