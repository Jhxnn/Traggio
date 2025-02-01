package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.TransporteDto;
import com.traggio.models.Transporte;
import com.traggio.repositories.TransporteRepository;


@Service
public class TransporteService {

	@Autowired
	TransporteRepository transporteRepository;
	
	
	
	public Transporte findById(UUID id) {
		return transporteRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Transporte> findAll(){
		return transporteRepository.findAll();
	}
	
	public Transporte createTransporte(TransporteDto transporteDto) {
		var transporte = new Transporte();
		BeanUtils.copyProperties(transporteDto, transporte);
		return transporteRepository.save(transporte);
	}
	
	public String relatorioAtraso(UUID id) {
		var transporte = findById(id);
		if(transporte.getDataPrevisaoChegada().isBefore(transporte.getDataChegada())){
			return "O transporte atrasou (gerar relatorio em pdf";
		}
		return "O Transporte nao atrasou (gerar relatorio em pdf";
	}
	
	public List<Object[]> findMostUsedCars(){
		return transporteRepository.findMostUsedVehicleModels();
	}
	
	
	
	public Transporte uptadeTransporte(UUID id, TransporteDto transporteDto) {
		var transporte =  findById(id);
		BeanUtils.copyProperties(transporteDto, transporte);
		return transporteRepository.save(transporte);
	}
	
	public void deleteTransporte(UUID id) {
		var transporte = findById(id);
		transporteRepository.delete(transporte);
	}
}
