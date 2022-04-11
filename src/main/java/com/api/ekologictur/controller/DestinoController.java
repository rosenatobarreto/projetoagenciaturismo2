package com.api.ekologictur.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.ekologictur.dto.DestinoDto;
import com.api.ekologictur.dto.PacoteDto;
import com.api.ekologictur.model.Destino;
import com.api.ekologictur.model.Pacote;
import com.api.ekologictur.service.DestinoConverterService;
import com.api.ekologictur.service.DestinoService;
import com.api.ekologictur.service.PacoteConverterService;
import com.api.ekologictur.service.PacoteService;

@RestController
@RequestMapping("/api/destino")
public class DestinoController {

	@Autowired
	private DestinoConverterService destinoConverterService;

	@Autowired
	private DestinoService destinoService;

	@PostMapping
	public ResponseEntity save(@RequestBody DestinoDto dto) {
		try {
			Destino entity = destinoConverterService.convertDtoToDestino(dto);
			entity = destinoService.save(entity);
			dto = destinoConverterService.convertDestinoToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DestinoDto dto) {
		try {
			dto.setIdDestino(id);
			Destino entity = destinoConverterService.convertDtoToDestino(dto);
			entity = destinoService.update(entity);
			dto = destinoConverterService.convertDestinoToDto(entity);
			
			return ResponseEntity.ok(dto);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			destinoService.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<DestinoDto> getDestinoById(@PathVariable Long id) {
        Destino destino = destinoService.findById(id);
        if (destino != null) {
        	DestinoDto destinoDto = new DestinoDto();
            BeanUtils.copyProperties(destino, destinoDto);
            return ResponseEntity.ok(destinoDto);
        }

        return ResponseEntity.notFound().build();
    }
	
//	@GetMapping("/{name}")
//    public ResponseEntity<DestinoDto> getDestino(@PathVariable String name) {
//        Destino destino = destinoService.findByName(name);
//        if (destino != null) {
//        	DestinoDto destinoDto = new DestinoDto();
//            BeanUtils.copyProperties(destino, destinoDto);
//            return ResponseEntity.ok(destinoDto);
//        }
//
//        return ResponseEntity.notFound().build();
//    }
	
	//  api/find?name=abc&tipo=nacional&id=2
	@GetMapping
	public ResponseEntity find(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "tipo", required = false) String tipo,
			@RequestParam(value = "id", required = false) Long id
			) {
		try {
			Destino filter = new Destino();
			filter.setIdDestino(id);
			filter.setNomeDestino(name);
			filter.setTipoDestino(tipo);
			
			List<Destino> entities = destinoService.find(filter);
			List<DestinoDto> dtos = (List<DestinoDto>) destinoConverterService.convertDestinoToDto((Destino) entities);
			
			return ResponseEntity.ok(dtos);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
