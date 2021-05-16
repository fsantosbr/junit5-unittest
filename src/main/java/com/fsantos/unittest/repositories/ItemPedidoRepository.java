package com.fsantos.unittest.repositories;

import com.fsantos.unittest.models.ItemPedidoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Integer> {

	
}
