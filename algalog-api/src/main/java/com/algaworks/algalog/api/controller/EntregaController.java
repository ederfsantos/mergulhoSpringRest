package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.service.EntregaService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaService entregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid  @RequestBody Entrega entrega) {
		
		return entregaService.solicitar(entrega);
	}
	@GetMapping
	public List<Entrega> listar(){
		return entregaService.listar();
		
	}
	@GetMapping("/{entregaId}")
	public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId){
		return entregaService.buscarPorId(entregaId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
