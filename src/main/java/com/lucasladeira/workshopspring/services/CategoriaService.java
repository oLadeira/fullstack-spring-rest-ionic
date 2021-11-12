package com.lucasladeira.workshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasladeira.workshopspring.domain.Categoria;
import com.lucasladeira.workshopspring.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll(){
		List<Categoria> list = categoriaRepository.findAll();
		return list;
	}
	
	public Optional<Categoria> findById(Integer id){		
		Optional<Categoria> opt = categoriaRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new RuntimeException();
		}
		
		return opt;
	}
}
