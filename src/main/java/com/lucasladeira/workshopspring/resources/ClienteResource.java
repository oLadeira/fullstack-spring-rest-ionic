package com.lucasladeira.workshopspring.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasladeira.workshopspring.domain.Cliente;
import com.lucasladeira.workshopspring.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> list = clienteService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> findById(@PathVariable Integer id){
		Optional<Cliente> opt = clienteService.findById(id);
		return ResponseEntity.ok().body(opt);
	}
}
