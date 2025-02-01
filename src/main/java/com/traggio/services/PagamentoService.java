package com.traggio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.PagamentoDto;
import com.traggio.models.Pagamento;
import com.traggio.models.enums.MeioPagamento;
import com.traggio.models.enums.StatusPagamento;
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
	
	public List<Pagamento> findByMeioPagamento(MeioPagamento meio){
		return pagamentoRepository.findByMeioPagamento(meio);
	}
	
	public List<Pagamento> findByStatus(StatusPagamento status){
		return pagamentoRepository.findByStatus(status);
	}
	
	public List<Pagamento> findByData(LocalDate dataInicio, LocalDate dataFim){
		return pagamentoRepository.findAllByDataPagamentoBetween(dataInicio, dataFim);
	}
	
	public Double receitaDurantePeriodo(LocalDate dataInicio, LocalDate dataFim){
		List<Pagamento> pagamentos = findByData(dataInicio, dataFim);
		double total = 0;
		for(Pagamento pagamento: pagamentos) {
			total += pagamento.getValor();
		}
		return total;
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
