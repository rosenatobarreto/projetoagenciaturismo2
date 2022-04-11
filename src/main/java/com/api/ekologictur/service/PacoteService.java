package com.api.ekologictur.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.repository.PacoteRepository;

@Service
public class PacoteService {
	
	@Autowired
	private PacoteRepository pacoteRepository;
	
	public Pacote save(Pacote pacote) {
		return pacoteRepository.save(pacote);
	}

	public void deleteById(Long id) {
		
		Pacote pacote = findById(id);
		
		if(pacote == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		
		pacoteRepository.deleteById(id);
	}

	public Pacote update(Pacote pacote) {
		return pacoteRepository.save(pacote);
	}
	
	public Pacote update(Long id) {
		Pacote pacoteSalvo = pacoteRepository.getById(id);
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}		
		return pacoteRepository.save(pacoteSalvo);
	}
	
	public Pacote findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		return pacoteRepository.findById(id).get();
	}

	public Iterable<Pacote> findAll() {
		return pacoteRepository.findAll();
	}
	
	public List<Pacote> find(Pacote filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return pacoteRepository.findAll(example);
		
	}
	
		
}
