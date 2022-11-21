package com.algaworks.algalog.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

	private EntregaRepository entregaRepository;
	// private ClienteRepository clienteRepository;
	private ClienteService clienteService;

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());

		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());

		return entregaRepository.save(entrega);
	}

	/**
	 * Metodo retorna uma lista de entregas
	 * 
	 * @return
	 */
	@Transactional
	public List<Entrega> listar() {
		return entregaRepository.findAll();
	}
	
	@Transactional
	public Optional<Entrega> buscarPorId(Long entregaId) {
		return entregaRepository.findById(entregaId);
	}
	
	

}
