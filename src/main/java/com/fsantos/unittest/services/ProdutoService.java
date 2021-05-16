package com.fsantos.unittest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fsantos.unittest.dtos.ProdutoDto;
import com.fsantos.unittest.models.ProdutoModel;
import com.fsantos.unittest.repositories.ProdutoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {	
	
	private ProdutoRepository repository;
	
	public List<ProdutoDto> findAll(){
		List<ProdutoModel> list  = this.repository.findAll();
		return list.stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList :: new));
	}
	
	public ProdutoDto findOne(int idProduto) throws Exception {
		return this.repository.findById(idProduto)
					.orElseThrow(() -> new Exception("Matrícula não localizada")).toDTO();
	}
	
	public ProdutoDto save(ProdutoDto novo) {
		//return this.repository.save(novo.toEntity()).toDTO();
		ProdutoModel prod = novo.toEntity();
		//prod.toDTO();
		return this.repository.save(prod).toDTO();
	}
	
	public ProdutoModel saveEntity(ProdutoModel novo) {
		return this.repository.save(novo);
	}

	public ProdutoDto update(int id, ProdutoDto produtoDto) {						
		Optional<ProdutoModel> op = this.repository.findById(id);
		if(op.isPresent()){
			ProdutoModel obj = op.get();
			obj.setNomeProd(produtoDto.getNomeProd());
			obj.setPrecoUnitProd(produtoDto.getPrecoUnitProd());
			obj.setQtdeEstoqueProd(produtoDto.getQtdeEstoqueProd());
			
			return this.repository.save(obj).toDTO();
		} else {
			throw new RuntimeException("Produto não encontrado!");
		}
	}
	
	public void delete(int id) {	
		this.repository.deleteById(id);
	}
	
	public List<ProdutoDto> findByName(String nomeProd){
		List<ProdutoModel> list  = this.repository.findByNomeProdContains(nomeProd);
		return list.stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList :: new));		
	}
}
