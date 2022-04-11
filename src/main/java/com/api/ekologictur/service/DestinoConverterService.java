package com.api.ekologictur.service;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.api.ekologictur.dto.DestinoDto;
import com.api.ekologictur.model.Destino;

@Service
public interface DestinoConverterService {
	
	public List<DestinoDto> destinoToDTOList(List<Destino> entities);
	public Destino dtoToDestino(DestinoDto dto);
	public DestinoDto destinoToDTO(Destino entity);

}
