package com.fsantos.unittest.dtos;

import lombok.Data;

@Data
public class EnderecoDTO {
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String cep;
	
	private String cidade;
	
	private String estado;
}