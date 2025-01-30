package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.FornecedorDto;
import com.traggio.models.Fornecedor;
import com.traggio.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	FornecedorRepository fornecedorRepository;
	
	
	
	public Fornecedor findById(UUID id) {
		return fornecedorRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Fornecedor> findAll(){
		return fornecedorRepository.findAll();
	}
	
	public Fornecedor createFornecedor(FornecedorDto fornecedorDto) {
		var fornecedor = new Fornecedor();
		BeanUtils.copyProperties(fornecedorDto, fornecedor);
		return fornecedorRepository.save(fornecedor);
	}
	
	public Fornecedor uptadeFornecedor(UUID id, FornecedorDto fornecedorDto) {
		var fornecedor =  findById(id);
		BeanUtils.copyProperties(fornecedorDto, fornecedor);
		return fornecedorRepository.save(fornecedor);
	}
	
	public void deleteFornecedor(UUID id) {
		var fornecedor = findById(id);
		fornecedorRepository.delete(fornecedor);
	}
}
