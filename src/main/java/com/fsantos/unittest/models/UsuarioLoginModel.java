package com.fsantos.unittest.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fsantos.unittest.dtos.UsuarioLoginDTO;
import com.fsantos.unittest.models.enums.Perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tab_usuario")
public class UsuarioLoginModel {
	@Id
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "usuario_seq" )
	@SequenceGenerator ( sequenceName = "usuario_seq", name = "usuario_seq", allocationSize = 1 )
	@Column(name = "id_usuario")
	private int id;
	
	@Column(name = "nome_usuario")
	private String nome;
	@Column(name = "senha_usuario")
	private String senha;
	@Column(name = "email_usuario")
	private String email;
		
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tab_perfil", joinColumns = @JoinColumn (name = "id_usuario") )
	@Column(name = "id_perfil")
	private Set<Integer> perfis = new HashSet<>();
	
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		if (perfis == null) {
			perfis = new HashSet<>();
		}
		
		perfis.add( perfil.getCodigo() );
	}
	
	public UsuarioLoginDTO toDto() {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(this, UsuarioLoginDTO.class);
	}
}