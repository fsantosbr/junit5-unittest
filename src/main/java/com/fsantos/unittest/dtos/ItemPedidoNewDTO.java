package com.fsantos.unittest.dtos;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.models.ItemPedidoModel;

import lombok.Data;

@Data
public class ItemPedidoNewDTO {
	
	private Integer idItemPedido;

	@NotNull
	private Integer qtdeItemPedido;
	
	@NotNull
	private ProdutoInDto produto;
	
	@NotNull
	private PedidoDTOInItemPedido pedido;
	
	public ItemPedidoModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ItemPedidoModel.class);
	}
}
