package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.VeiculoDto;
import com.traggio.models.Veiculo;
import com.traggio.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired
	FornecedorService fornecedorService;
	
	public Veiculo findById(UUID id) {
		return veiculoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Veiculo> findAll(){
		return veiculoRepository.findAll();
	}
	
	public Veiculo createVeiculo(VeiculoDto veiculoDto) {
		var veiculo = new Veiculo();
		var fornecedor = fornecedorService.findById(veiculoDto.fornecedorId());
		BeanUtils.copyProperties(veiculoDto, veiculo);
		veiculo.setFornecedor(fornecedor);
		return veiculoRepository.save(veiculo);
	}
	
	public List<Veiculo> findByPaisOrigem(String paisOrigem){
		return veiculoRepository.findByPaisOrigem(paisOrigem);
	}
	
	public List<Veiculo> findByModelo(String modelo){
		return veiculoRepository.findByModelo(modelo);	
	}
	
	public List<Veiculo> findByFornecedor(UUID fornecedorId){
		var fornecedor = fornecedorService.findById(fornecedorId);
		return veiculoRepository.findByFornecedor(fornecedor);
	}
	
	public List<Veiculo> findByMarca(String marca){
		return veiculoRepository.findByMarca(marca);
	}
	
	public Veiculo uptadeVeiculo(UUID id, VeiculoDto veiculoDto) {
		var veiculo =  findById(id);
		var fornecedor = fornecedorService.findById(veiculoDto.fornecedorId());
		BeanUtils.copyProperties(veiculoDto, veiculo);
		veiculo.setFornecedor(fornecedor);
		return veiculoRepository.save(veiculo);
	}
	
	public void deleteVeiculo(UUID id) {
		var veiculo = findById(id);
		veiculoRepository.delete(veiculo);
	}
}
