package com.traggio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
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
	public byte[] pdfPagamento(LocalDate dataInicio, LocalDate dataFim) {
		
		try(
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
		){
		List<Pagamento> pagamentos = findByData(dataInicio, dataFim);
		
		for(Pagamento pagamento: pagamentos) {
			
			document.add(new Paragraph("Id do pedido: " + pagamento.getPedido().getPedidoId()));
			document.add(new Paragraph("Data do pagamento: " + pagamento.getDataPagamento()));
			document.add(new Paragraph("Valor: " + pagamento.getValor()));
			document.add(new Paragraph("Meio de pagamento: " + pagamento.getMeioPagamento()));
			document.add(new Paragraph("Status: " + pagamento.getStatus()));
			document.add(new Paragraph("_____________________________________________"));
			
		}
		document.add(new Paragraph("Valor Total: "  + receitaDurantePeriodo(dataInicio, dataFim)));
        document.close();
        return byteArrayOutputStream.toByteArray();
		}
		catch(Exception e) {
			throw new RuntimeException("Erro ao gerar relatorio: ", e);
		}
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
