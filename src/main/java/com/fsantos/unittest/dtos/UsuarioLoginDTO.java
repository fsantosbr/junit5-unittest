package com.fsantos.unittest.dtos;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.models.UsuarioLoginModel;

import lombok.Data;

@Data
public class UsuarioLoginDTO {
	private int id;
	private String nome;	
	private String senha;
	private String email;		
	private Set<Integer> perfis = new HashSet<>();
	
	public UsuarioLoginModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(this, UsuarioLoginModel.class);		
	}
}