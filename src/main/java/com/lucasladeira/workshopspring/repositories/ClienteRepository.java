package com.lucasladeira.workshopspring.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasladeira.workshopspring.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional()
	Cliente findByEmail (String email);
	
}
