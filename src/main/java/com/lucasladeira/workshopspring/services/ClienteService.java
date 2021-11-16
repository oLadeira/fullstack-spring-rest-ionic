package com.lucasladeira.workshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasladeira.workshopspring.domain.Cliente;
import com.lucasladeira.workshopspring.repositories.ClienteRepository;
import com.lucasladeira.workshopspring.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<Cliente> findAll () {
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> findById (Integer id){
		Optional<Cliente> opt = clienteRepository.findById(id);		
		if (opt.isEmpty()) {
			throw new ObjectNotFoundException("Cliente não encontrado! ID: " + id);
		}		
		return opt;
	}
	
}
