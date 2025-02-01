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

import com.traggio.dtos.DespachanteDto;
import com.traggio.models.Despachante;
import com.traggio.services.DespachanteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/despachante")
public class DespachanteController {

	@Autowired
	DespachanteService despachanteService;
	
	
	@Operation(description = "Lista todos os Despachantes")
	@GetMapping
	public ResponseEntity<List<Despachante>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(despachanteService.findAll());
	}
	
	@Operation(description = "Busca despachante pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Despachante> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(despachanteService.findById(id));
	}
	
	@Operation(description = "Cria um despachante")
	@PostMapping
	public ResponseEntity<Despachante> createDespachante(@RequestBody DespachanteDto despachanteDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(despachanteService.createDespachante(despachanteDto));
	}
	
	@Operation(description = "Atualiza Despachante")
	@PutMapping("/{id}")
	public ResponseEntity<Despachante> updateDespachante(@RequestBody DespachanteDto despachanteDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(despachanteService.uptadeDespachante(id, despachanteDto));
	}
	
	@Operation(description = "Deleta um despachante")
	@DeleteMapping("/{id}")
	public ResponseEntity<Despachante> deleteDespachante(@PathVariable(name = "id")UUID id){
		despachanteService.deleteDespachante(id);
		return ResponseEntity.noContent().build();
	}
}
