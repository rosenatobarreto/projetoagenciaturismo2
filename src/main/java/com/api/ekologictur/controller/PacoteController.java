package com.api.ekologictur.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.api.ekologictur.dto.PacoteDto;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.service.PacoteConverterService;
import com.api.ekologictur.service.PacoteService;

@RestController
@RequestMapping("/api/pacote")
public class PacoteController {
	
	@Autowired
	private PacoteConverterService pacoteConverterService;

	@Autowired
	private PacoteService pacoteService;

	@PostMapping
	public ResponseEntity save(@RequestBody PacoteDto dto) {
		try {
			Pacote entity = pacoteConverterService.convertDtoToPacote(dto);
			entity = pacoteService.save(entity);
			dto = pacoteConverterService.convertPacoteToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody PacoteDto dto) {
		try {
			dto.setIdPacote(id);
			Pacote entity = pacoteConverterService.convertDtoToPacote(dto);
			entity = pacoteService.update(entity);
			dto = pacoteConverterService.convertPacoteToDto(entity);
			
			return ResponseEntity.ok(dto);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			pacoteService.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<PacoteDto> getPacoteById(@PathVariable Long id) {
        Pacote pacote = pacoteService.findById(id);
        if (pacote != null) {
        	PacoteDto pacoteDto = new PacoteDto();
            BeanUtils.copyProperties(pacote, pacoteDto);
            return ResponseEntity.ok(pacoteDto);
        }

        return ResponseEntity.notFound().build();
    }
	
	//  api/find?name=abc&periodo=verao&id=2
	@GetMapping
	public ResponseEntity find(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "periodo", required = false) int periodo,
			@RequestParam(value = "id", required = false) Long id
			) {
		try {
			Pacote filter = new Pacote();
			filter.setIdPacote(id);
			filter.setNomePacote(name);
			filter.setPeriodoEmDias(periodo);
			
			List<Pacote> entities = pacoteService.find(filter);
			List<PacoteDto> dtos = (List<PacoteDto>) pacoteConverterService.convertPacoteToDto((Pacote) entities);
			
			return ResponseEntity.ok(dtos);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GetMapping(value="/pacote/{id}")
//	public ResponseEntity<Pacote> getPacoteById(@PathVariable("id") Long id) {
//		Optional<Pacote> dadosPacote = pacoteService.findById(id);
//		if (dadosPacote.isPresent()) {
//			return new ResponseEntity<>(dadosPacote.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

}
