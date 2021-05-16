package com.fsantos.unittest.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.dtos.ProdutoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_produto")
public class ProdutoModel {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="produto_seq_generator")
	@SequenceGenerator(name="produto_seq_generator", sequenceName="produto_seq", allocationSize = 1)
	@Column(name = "id_prod")
	private Integer idProd;
	
	@Column(name = "nome_prod")
	private String nomeProd;
	
	@Column(name = "preco_unit_prod")
	private double precoUnitProd;
	
	@Column(name = "qtde_estoque_prod")
	private int qtdeEstoqueProd;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private List<ItemPedidoModel> itemPedido = new ArrayList<>();

	public ProdutoDto toDTO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ProdutoDto.class);
	}
}
