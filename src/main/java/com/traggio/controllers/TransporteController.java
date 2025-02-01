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

import com.traggio.dtos.TransporteDto;
import com.traggio.models.Transporte;
import com.traggio.services.TransporteService;

@RestController
@RequestMapping("/transporte")
public class TransporteController {

	
	@Autowired
	TransporteService transporteService;
	
	@GetMapping
	public ResponseEntity<List<Transporte>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(transporteService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Transporte> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(transporteService.findById(id));
	}
	@GetMapping("/atraso/{id}")
	public ResponseEntity<String> relatorioAtraso(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(transporteService.relatorioAtraso(id));
	}
	@GetMapping("/veiculos/mais-transportados")
	public ResponseEntity<List<Object[]>> maisTransportados(){
		return ResponseEntity.status(HttpStatus.OK).body(transporteService.findMostUsedCars());
	}
	@PostMapping
	public ResponseEntity<Transporte> createTransporte(@RequestBody TransporteDto clienteDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(transporteService.createTransporte(clienteDto));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Transporte> updateTransporte(@RequestBody TransporteDto transporteDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(transporteService.uptadeTransporte(id, transporteDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Transporte> deleteTransporte(@PathVariable(name = "id")UUID id){
		transporteService.deleteTransporte(id);
		return ResponseEntity.noContent().build();
	}
}
