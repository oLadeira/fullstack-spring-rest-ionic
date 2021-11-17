package com.lucasladeira.workshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasladeira.workshopspring.domain.Pedido;
import com.lucasladeira.workshopspring.repositories.PedidoRepository;
import com.lucasladeira.workshopspring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public Optional<Pedido> findById(Integer id){
		Optional<Pedido> opt = pedidoRepository.findById(id);
		
		if (opt.isEmpty()) {
			throw new ObjectNotFoundException("Pedido não encontrado!");
		}
		return opt;
	}
}
