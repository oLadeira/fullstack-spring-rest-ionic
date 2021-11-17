package com.lucasladeira.workshopspring.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasladeira.workshopspring.domain.Categoria;
import com.lucasladeira.workshopspring.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> list = categoriaService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> findById(@PathVariable Integer id){
		Optional<Categoria> opt = categoriaService.findById(id);
		return ResponseEntity.ok().body(opt);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Categoria categoria){
		categoria = categoriaService.insert(categoria);
		
		//boas praticas, ao inserir um recurso retornar sua URI (endereco) onde foi inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId()).toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update (@RequestBody Categoria categoria, @PathVariable Integer id){
		categoria = categoriaService.update(id, categoria);
		return ResponseEntity.noContent().build();
	}
}
