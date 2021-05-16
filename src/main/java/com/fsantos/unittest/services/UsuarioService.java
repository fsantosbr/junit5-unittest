package com.fsantos.unittest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fsantos.unittest.dtos.UsuarioDTO;
import com.fsantos.unittest.dtos.UsuarioDTOIn;
import com.fsantos.unittest.models.UsuarioModel;
import com.fsantos.unittest.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioDTO> findAll() {
		List<UsuarioModel> list = this.usuarioRepository.findAll();
		return list.stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList::new));
	}

	public UsuarioDTO findOne(int idUsuario) {
		return this.usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!")).toDTO();
	}

	public UsuarioDTO update(int idUsuario, UsuarioDTOIn updateUsuario) {
		Optional<UsuarioModel> usuarioOptional = this.usuarioRepository.findById(idUsuario);
		if (usuarioOptional.isPresent()) {
			UsuarioModel usuarioExistente = usuarioOptional.get();
			usuarioExistente.setNomeUsuario(updateUsuario.getNomeUsuario());
			usuarioExistente.setCpfUsuario(updateUsuario.getCpfUsuario());
			usuarioExistente.setEmailUsuario(updateUsuario.getEmailUsuario());
			usuarioExistente.setTelefoneUsuario(updateUsuario.getTelefoneUsuario());
			usuarioExistente.setSenhaUsuario(updateUsuario.getSenhaUsuario());

			return this.usuarioRepository.save(usuarioExistente).toDTO();
		} else {
			throw new RuntimeException("Usuario(a) nao encontrado(a)");
		}

	}

	public UsuarioDTO save(UsuarioDTOIn novoUsuario) {
		return this.usuarioRepository.save(novoUsuario.toEntity()).toDTO();
	}

	public void delete(int id) {
		this.usuarioRepository.deleteById(id);
	}

	// deletar multiplos
	public void deleteMany(int[] ids) {
		for (int i : ids) {
			this.usuarioRepository.deleteById(i);
		}
	}

	// Paginacao alunos
	public Page<UsuarioDTO> paginacao(int pagina, int registros) {
		PageRequest pageRequest = PageRequest.of(pagina, registros);

		Page<UsuarioModel> pageModels = this.usuarioRepository.findAll(pageRequest);

		Page<UsuarioDTO> pageDTO = pageModels.map(new Function<UsuarioModel, UsuarioDTO>() {
			public UsuarioDTO apply(UsuarioModel models) {
				return models.toDTO();
			}
		});
		return pageDTO;
	}

	public List<UsuarioDTO> findByNome(String nome) {
		List<UsuarioModel> usuarios = this.usuarioRepository.findByNomeUsuarioContains(nome);
		return usuarios.stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList::new));
	}

	public List<UsuarioDTO> findByCpf(String cpf) {
		List<UsuarioModel> usuarios = this.usuarioRepository.findByCpfUsuarioContains(cpf);
		return usuarios.stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList::new));
	}

	public List<UsuarioDTO> findByEmail(String email) {
		List<UsuarioModel> usuarios = this.usuarioRepository.findByEmailUsuarioContains(email);
		return usuarios.stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList::new));
	}

}
