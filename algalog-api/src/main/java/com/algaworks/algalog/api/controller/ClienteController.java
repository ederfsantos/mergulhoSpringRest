package com.algaworks.algalog.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	// @GetMapping("/clientes")
	// public String listar() {
	// return "Teste";
	// }
    private List<Cliente> clientes = new ArrayList<Cliente>();
	
@GetMapping
	public List<Cliente> listar() {
		// Cliente cliente1 = new Cliente();
		var cliente1 = new Cliente();// no java 11 pode se usar essa anotação var
		cliente1.setId(1L);
		cliente1.setNome("João");
		cliente1.setTelefone("18 2222v-2222");
		cliente1.setEmail("joao@email.com");

		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Jose");
		cliente2.setTelefone("18 2225-2262");
		cliente2.setEmail("jose@email.com");
		this.clientes.add(cliente1);
		this.clientes.add(cliente2);
		return Arrays.asList(cliente1, cliente2);
	}

	@GetMapping(value = "/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		//var cliente1 = new Cliente();// no java 11 pode se usar essa anotação var
	//	cliente1.setId(1L);
		//cliente1.setNome("João");
		//cliente1.setTelefone("18 2222v-2222");
		//cliente1.setEmail("joao@email.com");
		//return cliente1;
		for(Cliente c:this.clientes) {
			if(c.getId().equals(id)) {
				return c;
			}
			
		}
		return null;

	}

}
