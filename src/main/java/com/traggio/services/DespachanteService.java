package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.DespachanteDto;
import com.traggio.models.Despachante;
import com.traggio.repositories.DespachanteRepository;

@Service
public class DespachanteService {

	
	@Autowired
	DespachanteRepository despachanteRepository;
	
	
	
	public Despachante findById(UUID id) {
		return despachanteRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Despachante> findAll(){
		return despachanteRepository.findAll();
	}
	
	public Despachante createDespachante(DespachanteDto despachanteDto) {
		var despachante = new Despachante();
		BeanUtils.copyProperties(despachanteDto, despachante);
		return despachanteRepository.save(despachante);
	}
	
	public Despachante uptadeDespachante(UUID id, DespachanteDto despachanteDto) {
		var despachante =  findById(id);
		BeanUtils.copyProperties(despachanteDto, despachante);
		return despachanteRepository.save(despachante);
	}
	
	public void deleteDespachante(UUID id) {
		var despachante = findById(id);
		despachanteRepository.delete(despachante);
	}
}
