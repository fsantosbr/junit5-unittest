package com.fsantos.unittest.controllers;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;

import com.fsantos.unittest.dtos.ItemPedidoDTO;
import com.fsantos.unittest.dtos.ItemPedidoNewDTO;
import com.fsantos.unittest.dtos.ItemPedidoQtdDTO;
import com.fsantos.unittest.services.ItemPedidoService;

@Controller
@RequestMapping("item-pedidos")
public class ItemPedidoController {
	
	
	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping("/{itemPedido}")
	public ResponseEntity< ItemPedidoDTO > findOne( @PathVariable("itemPedido") int itemPedido ) {	
		return ResponseEntity.ok().body(this.itemPedidoService.finOne(itemPedido));	
	}

	@GetMapping("")
	public ResponseEntity< List<ItemPedidoDTO> > findAll(){
		return ResponseEntity.ok().body(this.itemPedidoService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.itemPedidoService.delete(id);
	}
	
	@PostMapping("")
	public ResponseEntity<ItemPedidoDTO> save(@RequestBody @Valid ItemPedidoNewDTO newItemPedido){		
		return ResponseEntity.ok().body(this.itemPedidoService.save(newItemPedido));
	}
	
	@GetMapping("paginacao")
	public ResponseEntity< Page<ItemPedidoDTO> > paginacao(
			@RequestParam(name="pagina", defaultValue="0") int pagina,
			@RequestParam(name="registros", defaultValue = "10") int registros
			){
		Page<ItemPedidoDTO> pageDTO = this.itemPedidoService.paginacao(pagina, registros);
		return ResponseEntity.ok().body(pageDTO);
	}
	
	@PatchMapping("/{idItemPedido}")
	public ResponseEntity<ItemPedidoDTO> update (@Valid @RequestBody ItemPedidoQtdDTO newObj, @PathVariable int idItemPedido){
		return ResponseEntity.ok().body(this.itemPedidoService.update(idItemPedido, newObj));
	}

}
