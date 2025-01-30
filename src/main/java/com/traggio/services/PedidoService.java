package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.PedidoDto;
import com.traggio.models.Pedido;
import com.traggio.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	
	
	public Pedido findById(UUID id) {
		return pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public Pedido createPedido(PedidoDto pedidoDto) {
		var pedido = new Pedido();
		BeanUtils.copyProperties(pedidoDto, pedido);
		return pedidoRepository.save(pedido);
	}
	
	public Pedido uptadePedido(UUID id, PedidoDto pedidoDto) {
		var pedido =  findById(id);
		BeanUtils.copyProperties(pedidoDto, pedido);
		return pedidoRepository.save(pedido);
	}
	
	public void deletePedido(UUID id) {
		var pedido = findById(id);
		pedidoRepository.delete(pedido);
	}
}
