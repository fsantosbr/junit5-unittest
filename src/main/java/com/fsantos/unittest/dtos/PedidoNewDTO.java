package com.fsantos.unittest.dtos;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.models.PedidoModel;

import lombok.Data;

@Data
public class PedidoNewDTO {
	private Integer idPedido;
	
	@NotNull
	private Double totalPedido;
	
	private UsuarioDTOIn usuario;
	
	public PedidoModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, PedidoModel.class);
	}
}