package com.traggio.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/transporte")
public class TransporteController {

	
	@Autowired
	TransporteService transporteService;
	
	@Operation(description = "Lista todos os transportes")
	@GetMapping
	public ResponseEntity<List<Transporte>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(transporteService.findAll());
	}
	
	@Operation(description = "Busca transporte pelo Id")
	@GetMapping("/{id}")
	public ResponseEntity<Transporte> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(transporteService.findById(id));
	}
	
	@Operation(description = "Gera relatorio de atraso")
	@GetMapping("/atraso/{id}")
	public ResponseEntity<byte[]> relatorioAtraso(@PathVariable(name = "id")UUID id){
		HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf");
	    headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
	    byte[] pdfBytes = transporteService.relatorioAtraso(id);
	    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}
	
	@Operation(description = "Busca os modelos de veiculos mais transportados")
	@GetMapping("/veiculos/mais-transportados")
	public ResponseEntity<List<Object[]>> maisTransportados(){
		return ResponseEntity.status(HttpStatus.OK).body(transporteService.findMostUsedCars());
	}
	
	@Operation(description = "Cria um transporte e salva valor total e total de taxa no Pedido")
	@PostMapping
	public ResponseEntity<Transporte> createTransporte(@RequestBody TransporteDto clienteDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(transporteService.createTransporte(clienteDto));
	}
	@Operation(description = "Atualiza um transpote")
	@PutMapping("/{id}")
	public ResponseEntity<Transporte> updateTransporte(@RequestBody TransporteDto transporteDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(transporteService.uptadeTransporte(id, transporteDto));
	}
	@Operation(description = "Deleta um transporte")
	@DeleteMapping("/{id}")
	public ResponseEntity<Transporte> deleteTransporte(@PathVariable(name = "id")UUID id){
		transporteService.deleteTransporte(id);
		return ResponseEntity.noContent().build();
	}
}
