package com.lucasladeira.workshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasladeira.workshopspring.domain.Cliente;
import com.lucasladeira.workshopspring.dto.ClienteDTO;
import com.lucasladeira.workshopspring.repositories.ClienteRepository;
import com.lucasladeira.workshopspring.services.exceptions.DataIntegrityException;
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
	
	public Page<Cliente> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente update(Integer id, Cliente cliente){		
		Optional<Cliente> opt = clienteRepository.findById(id);
		
		if (opt.isEmpty()) {
			throw new ObjectNotFoundException("Cliente não encontrado!");
		}

		Cliente databaseCliente = opt.get();
		
		updateData(databaseCliente, cliente);
		
		return clienteRepository.save(databaseCliente);
	}
	
	public void delete(Integer id) {
		Optional<Cliente> opt = clienteRepository.findById(id);
		
		if (opt.isEmpty()) {
			throw new ObjectNotFoundException("Cliente não encontrado!");
		}
		
		try {
			clienteRepository.deleteById(id);
		}catch(DataIntegrityException e) {
			throw new DataIntegrityException("Não é possível excluir o cliente porque há entidades relacionadas");
		}
		
		
	}
	
	//auxiliares
	public Cliente fromDTO (ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}	
	
	private void updateData(Cliente databaseCliente, Cliente cliente) {
		databaseCliente.setNome(cliente.getNome());
		databaseCliente.setEmail(cliente.getEmail());;
	}
}
