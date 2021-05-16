package com.fsantos.unittest.dtos;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.models.UsuarioModel;

import lombok.Data;

@Data
public class UsuarioDTOIn {
	private int idUsuario;
	
	@NotBlank(message = "Nome deve ser preenchido")
	private String nomeUsuario;

	@NotBlank(message = "CPF deve ser preenchido")
	//@Max(value = 11, message = "CPF deve conter 11 caracteres no máximo")
	//@Min(value = 11, message = "CPF deve conter 11 caracteres no mínimo")
	private String cpfUsuario;

	@NotBlank(message = "Email deve ser preenchido")
	private String emailUsuario;

	@NotBlank(message = "Telefone deve ser preenchido")
	private String telefoneUsuario;

	@NotBlank(message = "Senha deve ser preenchido")
	private String senhaUsuario;

	public UsuarioModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, UsuarioModel.class);
	}
}