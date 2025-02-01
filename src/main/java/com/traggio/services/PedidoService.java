package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.PedidoDto;
import com.traggio.models.Cliente;
import com.traggio.models.Pedido;
import com.traggio.models.enums.StatusPedido;
import com.traggio.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	
	
	public Pedido findById(UUID id) {
		return pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public List<Pedido> findByCliente(UUID clienteId){
		Cliente cliente = clienteService.findById(clienteId);
		return pedidoRepository.findByCliente(cliente);
	}
	
	public Pedido uptadeStatus(StatusPedido status, UUID idPedido) {
		Pedido pedido = findById(idPedido);
		pedido.setStatus(status);
		return pedidoRepository.save(pedido);
	}
	
	public List<Double> taxasPagas(UUID clienteId) {
		var cliente = clienteService.findById(clienteId);
		return pedidoRepository.findTotalTaxaByClienteId(cliente);
	}
	
	public Pedido cancelPedido(UUID id) {
		Pedido pedido = findById(id);
		pedido.setStatus(StatusPedido.CANCELADO);
		return pedidoRepository.save(pedido);
	}
	public List<Pedido> updatedDesc(){
		return pedidoRepository.findAllByOrderByUpdatedAtDesc();
	}
	
	public List<Pedido> createdDesc(){
		return pedidoRepository.findAllByOrderByCreatedAtDesc();
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
