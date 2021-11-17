package com.lucasladeira.workshopspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasladeira.workshopspring.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
