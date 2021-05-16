package com.fsantos.unittest.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PedidoDTO {	
	
	private LocalDateTime dataPedido;

	private Double totalPedido;
}