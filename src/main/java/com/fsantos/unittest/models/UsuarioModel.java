package com.fsantos.unittest.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_usuario")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "nome_usuario")
	private String nomeUsuario;

	@Column(name = "cpf_usuario")
	private String cpfUsuario;

	@Column(name = "email_usuario")
	private String emailUsuario;

	@Column(name = "telefone_usuario")
	private String telefoneUsuario;

	@Column(name = "senha_usuario")
	private String senhaUsuario;
	
	@OneToOne(mappedBy = "usuario")
	private EnderecoModel endereco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	List<PedidoModel> pedidos = new ArrayList<>();

	public UsuarioDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, UsuarioDTO.class);
	}

}