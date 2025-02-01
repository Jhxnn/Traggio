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

import com.traggio.dtos.VeiculoDto;
import com.traggio.models.Veiculo;
import com.traggio.services.VeiculoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

	@Autowired
	VeiculoService veiculoService;
	
	@Operation(description = "Lista todos os veiculos")
	@GetMapping
	public ResponseEntity<List<Veiculo>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.findAll());
	}
	
	@Operation(description = "Busca veiculo pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.findById(id));
	}
	
	@Operation(description = "Lista veiculos pela marca")
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Veiculo>> findByMarca(@PathVariable(name = "marca")String marca){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.findByMarca(marca));
	}
	
	@Operation(description = "Lista veiculos pelo modelo")
	@GetMapping("/modelo/{modelo}")
	public ResponseEntity<List<Veiculo>> findByModelo(@PathVariable(name = "modelo")String modelo){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.findByModelo(modelo));
	}
	
	@Operation(description = "Lista veiculos pelo pais de origem")
	@GetMapping("/pais/{paisOrigem}")
	public ResponseEntity<List<Veiculo>> findByPaisOrigem(@PathVariable(name = "paisOrigem")String paisOrigem){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.findByPaisOrigem(paisOrigem));
	}
	
	@Operation(description = "Lista veiculos pelo fornecedor")
	@GetMapping("/fornecedor/{fornecedorId}")
	public ResponseEntity<List<Veiculo>> findByFornecedor(@PathVariable(name = "fornecedorId")UUID fornecedorId){
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.findByFornecedor(fornecedorId));
	}
	
	@Operation(description = "Cria um veiculo")
	@PostMapping
	public ResponseEntity<Veiculo> createVeiculo(@RequestBody VeiculoDto veiculoDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.createVeiculo(veiculoDto));
	}
	
	@Operation(description = "Atualiza um veiculo")
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> updateVeiculo(@RequestBody VeiculoDto veiculoDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.uptadeVeiculo(id, veiculoDto));
	}
	
	@Operation(description = "Deleta um veiculo")
	@DeleteMapping("/{id}")
	public ResponseEntity<Veiculo> deleteVeiculo(@PathVariable(name = "id")UUID id){
		veiculoService.deleteVeiculo(id);
		return ResponseEntity.noContent().build();
	}
}
