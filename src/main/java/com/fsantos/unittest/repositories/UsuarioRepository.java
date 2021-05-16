package com.fsantos.unittest.repositories;

import java.util.List;

import com.fsantos.unittest.models.UsuarioModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
  
	List<UsuarioModel> findByNomeUsuarioContains(String nome);

	List<UsuarioModel> findByCpfUsuarioContains(String cpf);

	List<UsuarioModel> findByEmailUsuarioContains(String email);

}