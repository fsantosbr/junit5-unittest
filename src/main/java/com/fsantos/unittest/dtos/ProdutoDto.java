package com.fsantos.unittest.dtos;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.models.ProdutoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {
	
	private Integer idProd;
  
	@NotNull(message = "O campo 'nomeProd' não pode ser nulo!")
	private String nomeProd;
	
	@NotNull(message = "O campo 'precoUnitProd' não pode ser nulo!")
	private double precoUnitProd;
	
	@NotNull(message = "O campo 'qtdeEstoqueProd' não pode ser nulo!")
	private int qtdeEstoqueProd;
	
	public ProdutoModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ProdutoModel.class);
	}
}