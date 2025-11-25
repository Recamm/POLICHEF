package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}