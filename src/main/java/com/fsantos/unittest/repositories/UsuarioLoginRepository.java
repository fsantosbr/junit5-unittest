package com.fsantos.unittest.repositories;

import com.fsantos.unittest.models.UsuarioLoginModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioLoginRepository extends JpaRepository<UsuarioLoginModel, Integer>{
	
	public UsuarioLoginModel findByEmail(String email);
}