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

import com.traggio.dtos.DocumentacaoDto;
import com.traggio.models.Documentacao;
import com.traggio.services.DocumentacaoService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/documentacao")
public class DocumentacaoController {
	
	@Autowired
	DocumentacaoService documentacaoService;
	
	
	@Operation(description = "Lista todos os documentos")
	@GetMapping
	public ResponseEntity<List<Documentacao>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(documentacaoService.findAll());
	}
	
	@Operation(description = "Busca documento pelo Id")
	@GetMapping("/{id}")
	public ResponseEntity<Documentacao> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(documentacaoService.findById(id));
	}
	
	@Operation(description = "Cria um documento")
	@PostMapping
	public ResponseEntity<Documentacao> createDocumentacao(@RequestBody DocumentacaoDto documentacaoDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(documentacaoService.createDocumentacao(documentacaoDto));
	}
	
	@Operation(description = "Atualiza um documento")
	@PutMapping("/{id}")
	public ResponseEntity<Documentacao> updateDocumentacao(@RequestBody DocumentacaoDto documentacaoDto, @PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(documentacaoService.uptadeDocumentacao(id, documentacaoDto));
	}
	
	@Operation(description = "Deleta um documento")
	@DeleteMapping("/{id}")
	public ResponseEntity<Documentacao> deleteDocumentacao(@PathVariable(name = "id")UUID id){
		documentacaoService.deleteDocumentacao(id);
		return ResponseEntity.noContent().build();
	}
}
