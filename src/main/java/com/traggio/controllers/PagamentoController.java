package com.traggio.controllers;

import java.time.LocalDate;
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

import com.traggio.dtos.PagamentoDto;
import com.traggio.models.Pagamento;
import com.traggio.models.enums.MeioPagamento;
import com.traggio.models.enums.StatusPagamento;
import com.traggio.services.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	PagamentoService pagamentoService;
	
	
	@Operation(description = "Lista todos os pagamentos")
	@GetMapping
	public ResponseEntity<List<Pagamento>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findAll());
	}
	
	
	@Operation(description = "Busca pagamento pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Pagamento> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findById(id));
	}
	
	@Operation(description = "Lista pelo meio de pagamento")
	@GetMapping("/meio/{meio}")
	public ResponseEntity<List<Pagamento>> findByMeio(@PathVariable(name = "meio")MeioPagamento meio){
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findByMeioPagamento(meio));
	}
	
	@Operation(description = "Lista pelo status")
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Pagamento>> findByStatus(@PathVariable(name = "status")StatusPagamento status){
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findByStatus(status));
	}
	
	
	@Operation(description = "Lista todos os pagamentos pela data inicial e data final")
	@GetMapping("/data/{dataInico}/{dataFim}")
	public ResponseEntity<List<Pagamento>> findBydata(@PathVariable(name = "dataInicio")LocalDate dataInicio,
			@PathVariable(name = "dataFim")LocalDate dataFim){
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findByData(dataInicio, dataFim));
	}
	
	@Operation(description = "Retorna receita total de uma data incial ate uma data final")
	@GetMapping("/receita/{dataInico}/{dataFim}")
	public ResponseEntity<Double> receitaPeriodo(@PathVariable(name = "dataInicio")LocalDate dataInicio,
			@PathVariable(name = "dataFim")LocalDate dataFim){
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.receitaDurantePeriodo(dataInicio, dataFim));
	}
	
	
	@Operation(description = "Cria um pagamento")
	@PostMapping
	public ResponseEntity<Pagamento> createPagamento(@RequestBody PagamentoDto pagamentoDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.createPagamento(pagamentoDto));
	}
	
	
	
	@Operation(description = "Atualiza um pagamento")
	@PutMapping("/{id}")
	public ResponseEntity<Pagamento> updatePagamento(@RequestBody PagamentoDto pagamentoDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.uptadePagamento(id, pagamentoDto));
	}
	
	@Operation(description = "Deleta um pagamento")
	@DeleteMapping("/{id}")
	public ResponseEntity<Pagamento> deletePagamentoe(@PathVariable(name = "id")UUID id){
		pagamentoService.deletePagamento(id);
		return ResponseEntity.noContent().build();
	}
}
