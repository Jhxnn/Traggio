package com.traggio.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traggio.dtos.PedidoDto;
import com.traggio.models.Pedido;
import com.traggio.models.enums.StatusPedido;
import com.traggio.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	
	@Operation(description = "Lista todos os pedidos")
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
	}
	
	@Operation(description = "Busca pedido por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findById(id));
	}
	
	@Operation(description = "Lista pedidos de um Cliente")
	@GetMapping("/cliente/{id}")
	public ResponseEntity<List<Pedido>> findByCliente(@PathVariable(name = "id")UUID clienteId){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findByCliente(clienteId));
	}
	
	@Operation(description = "Lista os ultimos pedidos atualizados")
	@GetMapping("/updated")
	public ResponseEntity<List<Pedido>> updatedDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.updatedDesc());
	}
	@Operation(description = "Lista os ultimos pedidos criados")
	@GetMapping("/created")
	public ResponseEntity<List<Pedido>> createdDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.createdDesc());
	}
	
	@Operation(description = "Lista os pedidos pelo Status")
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Pedido>> findByStatus(@PathVariable(name = "status")StatusPedido status){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findByStatus(status));
	}
	
	@Operation(description = "Cria um pedido")
	@PostMapping
	public ResponseEntity<Pedido> createPedido(@RequestBody PedidoDto pedidoDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.createPedido(pedidoDto));
	}
	
	@Operation(description = "Atualiza o status")
	@PutMapping("/{status}/{id}")
	public ResponseEntity<Pedido> uptadeStatus(@PathVariable(name = "id")UUID id,
			@PathVariable(name = "status")StatusPedido status){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.uptadeStatus(status, id));
	}
	
	@Operation(description = "Atualiza um pedido")
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> updatePedido(@RequestBody PedidoDto pedidoDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.uptadePedido(id, pedidoDto));
	}
	
	@Operation(description = "Atualiza o status do Pedido para cancelado")
	@PutMapping("/cancelar/{id}")
	public ResponseEntity<Pedido> cancelPedido(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.cancelPedido(id));
	}
	@Operation(description = "Deleta um pedido")
	@DeleteMapping("/{id}")
	public ResponseEntity<Pedido> deletePedido(@PathVariable(name = "id")UUID id){
		pedidoService.deletePedido(id);
		return ResponseEntity.noContent().build();
	}
}
