package com.api.ekologictur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ekologictur.dto.PacoteDto;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.repository.PacoteRepository;

@Service
public interface PacoteConverterService {
	
	public List<PacoteDto> pacoteToDTO(List<Pacote> entities);
	public Pacote dtoToPacote(PacoteDto dto);
	public PacoteDto pacoteToDTO(Pacote entity);

}
