package com.fsantos.unittest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fsantos.unittest.dtos.ItemPedidoDTO;
import com.fsantos.unittest.dtos.ItemPedidoNewDTO;
import com.fsantos.unittest.dtos.ItemPedidoQtdDTO;
import com.fsantos.unittest.models.ItemPedidoModel;
import com.fsantos.unittest.models.PedidoModel;
import com.fsantos.unittest.models.ProdutoModel;
import com.fsantos.unittest.repositories.ItemPedidoRepository;
import com.fsantos.unittest.repositories.PedidoRepository;
import com.fsantos.unittest.repositories.ProdutoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public ItemPedidoDTO save(ItemPedidoNewDTO newItemPedido) {
		Integer idProd = newItemPedido.getProduto().getIdProd();
		Integer idPed = newItemPedido.getPedido().getIdPedido();
		
		Optional<ProdutoModel> objProd = this.produtoRepository.findById(idProd);
		Optional<PedidoModel> objPed = this.pedidoRepository.findById(idPed);
		
		if(objProd.isPresent() && objPed.isPresent()) {
			double preco = newItemPedido.getProduto().getPrecoUnitProd();
			
			ItemPedidoModel model = newItemPedido.toEntity();
			
			model.setPrecoItemPedido(preco);
			model.setProduto(objProd.get());
			model.setPedido(objPed.get());
			
			return this.itemPedidoRepository.save(model).toDto();
		} else {
			return null;
		}
	}
  
	public List<ItemPedidoDTO> findAll(){
		List<ItemPedidoModel> list = this.itemPedidoRepository.findAll();
		return list.stream().map(x -> x.toDto()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public void delete(int id) {
		this.itemPedidoRepository.deleteById(id);
	}
	
	public Page<ItemPedidoDTO> paginacao(int pagina, int registros){
		PageRequest pageRequest = PageRequest.of(pagina, registros);
		Page<ItemPedidoModel> pageModel = this.itemPedidoRepository.findAll(pageRequest);
		
		Page<ItemPedidoDTO> pageDTO = pageModel.map(
				new Function<ItemPedidoModel, ItemPedidoDTO>(){
					public ItemPedidoDTO apply(ItemPedidoModel model) {
						return model.toDto();
					}
				}
				);		
		return pageDTO;
	}

	public ItemPedidoDTO finOne(int itemPedido) {
		return this.itemPedidoRepository.findById(itemPedido).get().toDto();
	}
	
	public ItemPedidoDTO update(int id, ItemPedidoQtdDTO newObj) {
		Optional<ItemPedidoModel> optObj = this.itemPedidoRepository.findById(id);
		
		if (optObj.isPresent()) {
			ItemPedidoModel obj = optObj.get();
			
			obj.setQtdeItemPedido(newObj.getQtdeItemPedido());
			
			return this.itemPedidoRepository.save(obj).toDto();
		}else {
			throw new RuntimeException("Pedido não encontrado");
		}

	}
}