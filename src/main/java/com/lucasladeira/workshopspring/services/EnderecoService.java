package com.lucasladeira.workshopspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasladeira.workshopspring.domain.Endereco;
import com.lucasladeira.workshopspring.repositories.EnderecoRepository;
import com.lucasladeira.workshopspring.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Optional<Endereco> findById (Integer id){
		Optional<Endereco> opt = enderecoRepository.findById(id);
		
		if (opt.isEmpty()) {
			throw new ObjectNotFoundException("Endereco de entrega não encontrado!");
		}
		
		return opt;
		
	}
	
}
