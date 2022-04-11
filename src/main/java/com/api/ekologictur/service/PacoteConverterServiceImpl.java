package com.api.ekologictur.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.ekologictur.dto.PacoteDto;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.repository.PacoteRepository;

@Component
public class PacoteConverterServiceImpl implements PacoteConverterService{
	
	@Override
	@Bean
	public List<PacoteDto> pacoteToDTO(List<Pacote> entities) {
		List<PacoteDto> dtos = new ArrayList<>();
		
		for (Pacote dto : entities) {
			PacoteDto entity = pacoteToDTO(dto);
			dtos.add(entity);
		}
		return dtos;
	}

	@Override
	public Pacote dtoToPacote(PacoteDto dto) {
		Pacote entity = new Pacote();
		
		entity.setIdPacote(dto.getIdPacote());
		entity.setNomePacote(dto.getNomePacote());
		entity.setEpoca(dto.getEpoca());
		entity.setPeriodoEmDias(dto.getPeriodoEmDias());
		entity.setPreco(dto.getPreco());
				
		return entity;
	}

	@Override
	public PacoteDto pacoteToDTO(Pacote entity) {
		PacoteDto dto = new PacoteDto();
		
		dto.setIdPacote(entity.getIdPacote());
		dto.setNomePacote(entity.getNomePacote());
		dto.setEpoca(entity.getEpoca());
		dto.setPeriodoEmDias(entity.getPeriodoEmDias());
		dto.setPreco(entity.getPreco());
		
		return dto;
	}

}
