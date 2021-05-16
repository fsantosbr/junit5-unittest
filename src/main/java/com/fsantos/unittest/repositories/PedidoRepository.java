package com.fsantos.unittest.repositories;

import com.fsantos.unittest.models.PedidoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

}
