package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Transactional //esta anotação funciona como se fosse um roolback ou tudo ou nada, desfaz a operação
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream().anyMatch(clienteExistente->!clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este email.");
		}
		
		return clienteRepository.save(cliente);
	}
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
	public Cliente buscar(Long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(()-> new NegocioException("Cliente não encontrado"));
		return cliente;
	}

}
