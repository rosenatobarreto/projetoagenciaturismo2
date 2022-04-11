package com.api.ekologictur.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.ekologictur.dto.PacoteDto;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.service.PacoteConverterService;
import com.api.ekologictur.service.PacoteService;

@RestController
@RequestMapping("/api/pacote")
public class PacoteController {
	
	@Autowired(required=true)
	private PacoteConverterService pacoteConverterService;

	@Autowired(required=true)
	private PacoteService pacoteService;

	@PostMapping
	public ResponseEntity save(@RequestBody PacoteDto dto) {
		try {
			Pacote entity = pacoteConverterService.dtoToPacote(dto);
			entity = pacoteService.save(entity);
			dto = pacoteConverterService.pacoteToDTO(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody PacoteDto dto) {
		try {
			dto.setIdPacote(id);
			Pacote entity = pacoteConverterService.dtoToPacote(dto);
			entity = pacoteService.update(entity);
			dto = pacoteConverterService.pacoteToDTO(entity);
			
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
			@RequestParam(value = "nomePacote", required = false) String nomePacote,
			@RequestParam(value = "periodoEmDias", required = false) Integer periodoEmDias,
			@RequestParam(value = "idPacote", required = false) Long idPacote
			) {
		try {
			Pacote filter = new Pacote();
			filter.setIdPacote(idPacote);
			filter.setNomePacote(nomePacote);
			filter.setPeriodoEmDias(periodoEmDias);
			
			List<Pacote> entities = pacoteService.find(filter);
			List<PacoteDto> dtos = pacoteConverterService.pacoteToDTO(entities);
			
			return ResponseEntity.ok(dtos);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
}
