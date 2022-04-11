package com.api.ekologictur.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.ekologictur.dto.PacoteDto;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.repository.PacoteRepository;

@Service
@Component
public class PacoteConverterService {
	
	@Autowired
	private PacoteRepository pacoteRepository;
	
	//Mapeador de modelo
	@Autowired(required=true)
	private ModelMapper modelMapper;
	
	public List<PacoteDto> pacotesAll(){
		return pacoteRepository.findAll()
				.stream()
				.map(this::convertPacoteToDto)
				.collect(Collectors.toList());
		
	}
	
	//Usando o mapeador para a conversão
	@Bean
	public PacoteDto convertPacoteToDto(Pacote pacote) {
		//Isso permitirá que sejam mapeadas todas as proriedades definidas no objeto do pacote
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		PacoteDto pacoteDto = new PacoteDto();
		pacoteDto = modelMapper.map(pacote, PacoteDto.class);
		
		return pacoteDto;
	}
	
	@Bean
	public Pacote convertDtoToPacote(PacoteDto pacoteDto) {
		//Isso permitirá que sejam mapeadas todas as proriedades definidas no objeto do pacote
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Pacote pacote = new Pacote();
		pacote = modelMapper.map(pacoteDto, Pacote.class);
		
		return pacote;
	}

}
