package com.api.ekologictur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ekologictur.model.Destino;
import com.api.ekologictur.model.Pacote;

public interface DestinoRepository extends JpaRepository<Destino, Long>{
	
}
