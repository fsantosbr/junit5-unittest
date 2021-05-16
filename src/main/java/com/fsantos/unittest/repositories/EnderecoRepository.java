package com.fsantos.unittest.repositories;

import com.fsantos.unittest.models.EnderecoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {

}
