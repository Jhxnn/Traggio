package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.traggio.dtos.TransporteDto;
import com.traggio.models.Pedido;
import com.traggio.models.Transporte;
import com.traggio.repositories.PedidoRepository;
import com.traggio.repositories.TransporteRepository;


@Service
public class TransporteService {

	@Autowired
	TransporteRepository transporteRepository;
	
	@Lazy
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	VeiculoService veiculoService;
	
	@Autowired
	DespachanteService despachanteService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	public Transporte findById(UUID id) {
		return transporteRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Transporte> findAll(){
		return transporteRepository.findAll();
	}
	
	public Transporte createTransporte(TransporteDto transporteDto) {
		var transporte = new Transporte();
		BeanUtils.copyProperties(transporteDto, transporte);
		
		var pedido  = pedidoService.findById(transporteDto.pedidoId());
		var veiculo = veiculoService.findById(transporteDto.veiculoId());
		var despachante = despachanteService.findById(transporteDto.despachanteId());
		transporte.setVeiculo(veiculo);
		transporte.setPedido(pedido);
		transporte.setDespachante(despachante);
		pedido.setTotalTaxa(transporte.getVeiculo().getTaxaImportacao());
		pedido.setValorFinal(transporte.getValorTransporte() + transporte.getVeiculo().getPrecoBase());
		
		
		pedidoRepository.save(pedido);
		return transporteRepository.save(transporte);
	}
	
	public byte[] relatorioAtraso(UUID id) {
	    var transporte = findById(id);
	    if (!transporte.getDataPrevisaoChegada().isBefore(transporte.getDataChegada())) {
	        throw new IllegalArgumentException("O transporte não atrasou");
	    }

	    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	         PdfWriter writer = new PdfWriter(byteArrayOutputStream);
	         PdfDocument pdf = new PdfDocument(writer);
	         Document document = new Document(pdf)) {

	        document.add(new Paragraph("O transporte do container " + transporte.getNumeroContainer() + " chegou atrasado!"));
	        document.add(new Paragraph("ID do transporte: " + transporte.getTransporteId()));
	        document.add(new Paragraph("Data de embarque: " + transporte.getDataEmbarque()));
	        document.add(new Paragraph("Data de previsão da chegada: " + transporte.getDataPrevisaoChegada()));
	        document.add(new Paragraph("Data de chegada: " + transporte.getDataChegada()));
	        
	        emailService.enviarEmailTexto(transporte.getPedido().getCliente().getEmail(),
	        		"Atraso do transporte - TRAGGIO",
	        		"Olá " + transporte.getPedido().getCliente().getNome() + ", este email esta sendo enviado para notificar que o transporte " +
	        		transporte.getTransporteId() + " atrasou.\n Data prevista: " + transporte.getDataPrevisaoChegada() + "\n Data de chegada: " +
	        				transporte.getDataChegada());
	        return byteArrayOutputStream.toByteArray();
	    } catch (Exception e) {
	        throw new RuntimeException("Erro ao gerar o relatório de atraso", e);
	    }
	}

	
	public List<Object[]> findMostUsedCars(){
		return transporteRepository.findMostUsedVehicleModels();
	}
	
	public Transporte findByPedido(Pedido pedido) {
		return transporteRepository.findByPedido(pedido);
	}
	
	
	
	public Transporte uptadeTransporte(UUID id, TransporteDto transporteDto) {
		var transporte =  findById(id);
		BeanUtils.copyProperties(transporteDto, transporte);
		var pedido  = pedidoService.findById(transporteDto.pedidoId());
		var veiculo = veiculoService.findById(transporteDto.veiculoId());
		var despachante = despachanteService.findById(transporteDto.despachanteId());
		transporte.setVeiculo(veiculo);
		transporte.setPedido(pedido);
		transporte.setDespachante(despachante);
		return transporteRepository.save(transporte);
	}
	
	public void deleteTransporte(UUID id) {
		var transporte = findById(id);
		transporteRepository.delete(transporte);
	}
}
