package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")

public class ClienteController {
	// @PersistenceContext
	// private EntityManager manager;
	// a injeção de dependecia aqui esta vindo pelo construtor anotação @AllArgs....
	// acima
	private ClienteRepository clienteRepository;

	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {

		// return manager.createQuery("FROM Cliente",Cliente.class).getResultList();
		return clienteRepository.findAll();
		// return clienteRepository.findByNomeContaining("R");
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		// Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		// return cliente.orElse(null);// se nao tiver nada, retorna null
		// if(cliente.isPresent()) {
		// return ResponseEntity.ok(cliente.get());
		// }
		// return ResponseEntity.notFound().build();
//	}

//ou assim abaixo

		return clienteRepository.findById(clienteId)
				// .map(cliente->ResponseEntity.ok(cliente)) //ou assim pra enxugar mais
				.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		// return clienteRepository.save(cliente);
		return clienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);// para poder atualizar o objeto
		// cliente = clienteRepository.save(cliente);
		cliente = clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);

	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		// clienteRepository.deleteById(clienteId);
		clienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}

}
