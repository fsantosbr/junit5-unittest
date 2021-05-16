package com.fsantos.unittest.dtos;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemPedidoQtdDTO {
	
	@NotNull
	private Integer qtdeItemPedido;
}
