package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.DocumentacaoDto;
import com.traggio.models.Documentacao;
import com.traggio.repositories.DocumentacaoRepository;

@Service
public class DocumentacaoService {

	@Autowired
	DocumentacaoRepository documentacaoRepository;
	
	
	
	public Documentacao findById(UUID id) {
		return documentacaoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Documentacao> findAll(){
		return documentacaoRepository.findAll();
	}
	
	public Documentacao createDocumentacao(DocumentacaoDto documentacaoDto) {
		var documentacao = new Documentacao();
		BeanUtils.copyProperties(documentacaoDto, documentacao);
		return documentacaoRepository.save(documentacao);
	}
	
	public Documentacao uptadeDocumentacao(UUID id, DocumentacaoDto documentacaoDto) {
		var documentacao =  findById(id);
		BeanUtils.copyProperties(documentacaoDto, documentacao);
		return documentacaoRepository.save(documentacao);
	}
	
	public void deleteDocumentacao(UUID id) {
		var documentacao = findById(id);
		documentacaoRepository.delete(documentacao);
	}
}
