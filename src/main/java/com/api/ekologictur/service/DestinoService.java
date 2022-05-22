package com.api.ekologictur.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import com.api.ekologictur.model.Destino;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.repository.DestinoRepository;

@Service
public class DestinoService {
	
	@Autowired
	private DestinoRepository destinoRepository;
	
	public Destino save(Destino destino) {
		return destinoRepository.save(destino);
	}

	public void deleteById(Long id) {
		
		Destino destino = findById(id);
		
		if(destino == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		
		destinoRepository.deleteById(id);
	}

	public Destino update(Destino destino) {
		return destinoRepository.save(destino);
	}
	
	public Destino update(Long id) {
		Destino destinoSalvo = destinoRepository.getById(id);
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}		
		return destinoRepository.save(destinoSalvo);
	}
	
	public Destino findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		return destinoRepository.findById(id).get();
	}

	public List<Destino> findAll() {
		return destinoRepository.findAll();
	}
	
	public List<Destino> find(Destino filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return destinoRepository.findAll(example);
		
	}
		
}
