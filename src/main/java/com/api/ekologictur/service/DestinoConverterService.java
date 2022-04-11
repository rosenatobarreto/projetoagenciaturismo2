package com.api.ekologictur.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.ekologictur.dto.DestinoDto;
import com.api.ekologictur.dto.PacoteDto;
import com.api.ekologictur.model.Destino;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.repository.DestinoRepository;

@Service
@Component
public class DestinoConverterService {

	@Autowired
	private DestinoRepository destinoRepository;
	
	//Mapeador de modelo
	@Autowired(required=true)
	private ModelMapper modelMapper;
	
	public List<DestinoDto> destinosAll(){
		return destinoRepository.findAll()
				.stream()
				.map(this::convertDestinoToDto)
				.collect(Collectors.toList());
		
	}
	
	//Usando o mapeador para a conversão
	@Bean
	public DestinoDto convertDestinoToDto(Destino destino) {
		//Isso permitirá que sejam mapeadas todas as proriedades definidas no objeto do destino
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		DestinoDto destinoDto = new DestinoDto();
		destinoDto = modelMapper.map(destino, DestinoDto.class);
		
		return destinoDto;
	}
	
	@Bean
	public Destino convertDtoToDestino(DestinoDto destinoDto) {
		//Isso permitirá que sejam mapeadas todas as proriedades definidas no objeto do destino
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Destino destino = new Destino();
		destino = modelMapper.map(destinoDto, Destino.class);
		
		return destino;
	}
}
