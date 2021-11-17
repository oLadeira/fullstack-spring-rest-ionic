package com.lucasladeira.workshopspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasladeira.workshopspring.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
