package com.lucasladeira.workshopspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasladeira.workshopspring.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
