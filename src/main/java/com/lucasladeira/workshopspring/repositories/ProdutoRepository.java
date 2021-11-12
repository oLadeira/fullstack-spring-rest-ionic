package com.lucasladeira.workshopspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasladeira.workshopspring.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
