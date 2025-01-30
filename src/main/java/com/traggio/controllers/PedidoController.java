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
import com.traggio.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findById(id));
	}
	@PostMapping
	public ResponseEntity<Pedido> createPedido(@RequestBody PedidoDto pedidoDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.createPedido(pedidoDto));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> updatePedido(@RequestBody PedidoDto pedidoDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.uptadePedido(id, pedidoDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Pedido> deletePedido(@PathVariable(name = "id")UUID id){
		pedidoService.deletePedido(id);
		return ResponseEntity.noContent().build();
	}
}
