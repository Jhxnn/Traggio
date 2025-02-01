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

import com.traggio.dtos.ClienteDto;
import com.traggio.models.Cliente;
import com.traggio.services.ClienteService;
import com.traggio.services.PedidoService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
	}
	
	@GetMapping("/taxas/{id}")
	public ResponseEntity<List<Double>> taxasPagas(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.taxasPagas(id));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDto clienteDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(clienteDto));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@RequestBody ClienteDto clienteDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.uptadeCliente(id, clienteDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable(name = "id")UUID id){
		clienteService.deleteCliente(id);
		return ResponseEntity.noContent().build();
	}
}
