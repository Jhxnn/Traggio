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

import com.traggio.dtos.FornecedorDto;
import com.traggio.models.Fornecedor;
import com.traggio.services.FornecedorService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	FornecedorService fornecedorService;
	
	
	@Operation(description = "Lista todos os fornecedores")
	@GetMapping
	public ResponseEntity<List<Fornecedor>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.findAll());
	}
	
	@Operation(description = "Busca fornecedor por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.findById(id));
	}
	
	@Operation(description = "Cria um fornecedor")
	@PostMapping
	public ResponseEntity<Fornecedor> createFornecedor(@RequestBody FornecedorDto fornecedorDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.createFornecedor(fornecedorDto));
	}
	
	@Operation(description = "Atualiza um fornecedor")
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> updateFornecedor(@RequestBody FornecedorDto fornecedorDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.uptadeFornecedor(id, fornecedorDto));
	}
	
	@Operation(description = "Deleta um fornecedor")
	@DeleteMapping("/{id}")
	public ResponseEntity<Fornecedor> deleteFornecedor(@PathVariable(name = "id")UUID id){
		fornecedorService.deleteFornecedor(id);
		return ResponseEntity.noContent().build();
	}
}
