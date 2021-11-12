package com.lucasladeira.workshopspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasladeira.workshopspring.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
