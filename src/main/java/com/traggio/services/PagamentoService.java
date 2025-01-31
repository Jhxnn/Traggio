package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.PagamentoDto;
import com.traggio.models.Pagamento;
import com.traggio.repositories.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	
	
	public Pagamento findById(UUID id) {
		return pagamentoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Pagamento> findAll(){
		return pagamentoRepository.findAll();
	}
	
	public Pagamento createPagamento(PagamentoDto pagamentoDto) {
		var pagamento = new Pagamento();
		BeanUtils.copyProperties(pagamentoDto, pagamento);
		return pagamentoRepository.save(pagamento);
	}
	
	public Pagamento uptadePagamento(UUID id, PagamentoDto pagamentoDto) {
		var pagamento =  findById(id);
		BeanUtils.copyProperties(pagamentoDto, pagamento);
		return pagamentoRepository.save(pagamento);
	}
	
	public void deletePagamento(UUID id) {
		var pagamento = findById(id);
		pagamentoRepository.delete(pagamento);
	}
}
