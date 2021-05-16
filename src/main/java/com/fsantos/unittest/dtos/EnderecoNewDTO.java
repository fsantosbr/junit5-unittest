package com.fsantos.unittest.dtos;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.models.EnderecoModel;

import lombok.Data;

@Data
public class EnderecoNewDTO {
	
	private int id;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String cep;
	
	private String cidade;
	
	private String estado;
	
	private UsuarioDTOIn usuario;
	
	public EnderecoModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();	
		return modelMapper.map(this,  EnderecoModel.class);
	}
}