package com.fsantos.unittest.controllers;

import java.util.List;

import javax.validation.Valid;

import com.fsantos.unittest.dtos.PedidoDTO;
import com.fsantos.unittest.dtos.PedidoDTOIn;
import com.fsantos.unittest.dtos.PedidoNewDTO;
import com.fsantos.unittest.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("pedidos")
@RestController
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("")
	public ResponseEntity < List<PedidoDTO> > findAll(){
		return ResponseEntity.ok().body(this.pedidoService.findAll());
	}
	
	@PostMapping("")
	private ResponseEntity<PedidoDTO> save(@RequestBody @Valid PedidoNewDTO newPedido){
		return ResponseEntity.ok().body(this.pedidoService.save(newPedido));
	}

	@PatchMapping("/{idPedido}")
	public ResponseEntity<PedidoDTO> update(@Valid @RequestBody PedidoDTOIn newObj, @PathVariable int idPedido) {
		return ResponseEntity.ok().body(this.pedidoService.update(idPedido, newObj));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		this.pedidoService.delete(id);
	}
	
	@GetMapping("/{idPedido}")
	public ResponseEntity< PedidoDTO > findOne( @PathVariable("idPedido") int idPedido  ) {	
		return ResponseEntity.ok().body( this.pedidoService.finOne(idPedido) );						
	}
	
	@GetMapping("paginacao")
	public ResponseEntity<Page<PedidoDTO>> paginacao (
			@RequestParam(name="pagina", defaultValue = "0") int pagina,
			@RequestParam(name="registros", defaultValue = "10") int registros
			){
		Page<PedidoDTO> pageDTO = this.pedidoService.paginacao(pagina, registros);
		return ResponseEntity.ok().body(pageDTO);
	}
	

}
