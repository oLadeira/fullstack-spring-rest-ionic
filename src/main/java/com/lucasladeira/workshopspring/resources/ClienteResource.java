package com.lucasladeira.workshopspring.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasladeira.workshopspring.domain.Cliente;
import com.lucasladeira.workshopspring.dto.ClienteDTO;
import com.lucasladeira.workshopspring.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDTO = list.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> findById(@PathVariable Integer id){
		Optional<Cliente> opt = clienteService.findById(id);
		return ResponseEntity.ok().body(opt);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(name = "page")Integer page, 
			@RequestParam(name = "linesPerPage")Integer linesPerPage, 
			@RequestParam(name = "orderBy")String orderBy, 
			@RequestParam(name = "direction")String direction){
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente = clienteService.update(id, cliente);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}

