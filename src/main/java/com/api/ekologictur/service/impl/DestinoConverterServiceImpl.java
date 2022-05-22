package com.api.ekologictur.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.api.ekologictur.dto.DestinoDto;
import com.api.ekologictur.model.Destino;
import com.api.ekologictur.service.DestinoConverterService;

@Component
public class DestinoConverterServiceImpl implements DestinoConverterService{

	@Override
	@Bean
	public List<DestinoDto> destinoToDTOList(List<Destino> entities) {
		List<DestinoDto> dtos = new ArrayList<>();
		
		for (Destino dto : entities) {
			DestinoDto entity = destinoToDTO(dto);
			dtos.add(entity);
		}
		return dtos;
	}

	@Override
	public Destino dtoToDestino(DestinoDto dto) {
		Destino entity = new Destino();
		
		entity.setIdDestino(dto.getIdDestino());
		entity.setNomeDestino(dto.getNomeDestino());
		entity.setLocalidade(dto.getLocalidade());
		entity.setTipoDestino(dto.getTipoDestino());
		entity.setPacote(dto.getPacote());
				
		return entity;
	}

	@Override
	public DestinoDto destinoToDTO(Destino entity) {
		DestinoDto dto = new DestinoDto();
		
		dto.setIdDestino(entity.getIdDestino());
		dto.setNomeDestino(entity.getNomeDestino());
		dto.setLocalidade(entity.getLocalidade());
		dto.setTipoDestino(entity.getTipoDestino());
		dto.setPacote(entity.getPacote());
		
		return dto;
	}

}
