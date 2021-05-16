package com.fsantos.unittest.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.fsantos.unittest.dtos.ProdutoDto;
import com.fsantos.unittest.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("produtos")
@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	//FindAll
	@GetMapping("")
	public ResponseEntity<List<ProdutoDto>> findAll() {
		return ResponseEntity.ok().body(this.service.findAll());
	}
	
	//FindOne
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> findOne(@PathVariable int id) throws Exception {
		return ResponseEntity.ok().body(this.service.findOne(id));
	}
	
	//Save
	@PostMapping("")
	public ResponseEntity<ProdutoDto> save(@RequestBody ProdutoDto novo) {
		return ResponseEntity.ok().body(this.service.save(novo));
	}
	
	//Update
	@PatchMapping("/{id}")
	public ResponseEntity<ProdutoDto> update(@PathVariable int id, @RequestBody ProdutoDto produtoDto) {
		return ResponseEntity.ok().body(this.service.update(id, produtoDto));
	}

	//Excluir registro do banco
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.service.delete(id);
	}
	
	@GetMapping("procurar-por-nome/{nomeProd}")
	public ResponseEntity<List<ProdutoDto>> finByName(@PathVariable(name = "nomeProd") String nomeProd){
		return ResponseEntity.ok().body(this.service.findByName(nomeProd));		
	}
	
}
