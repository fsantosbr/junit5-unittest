package com.fsantos.unittest.repositories;

import com.fsantos.unittest.models.ProdutoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer>{
	
	List<ProdutoModel> findByNomeProdContains(String nomeProd);

}