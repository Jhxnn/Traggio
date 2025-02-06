package com.traggio.services;

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
import com.traggio.dtos.PedidoDto;
import com.traggio.models.Cliente;
import com.traggio.models.Pedido;
import com.traggio.models.enums.StatusPedido;
import com.traggio.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	TransporteService transporteService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	public Pedido findById(UUID id) {
		return pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public List<Pedido> findByCliente(UUID clienteId){
		Cliente cliente = clienteService.findById(clienteId);
		return pedidoRepository.findByCliente(cliente);
	}
	
	public Pedido uptadeStatus(StatusPedido status, UUID idPedido) {
		Pedido pedido = findById(idPedido);
		emailService.enviarEmailTexto(pedido.getCliente().getEmail(), "Status do pedido atualizado - TRAGGIO", "Olá " 
		+ pedido.getCliente().getNome() + 
		" O status do seu pedido foi atualizado de " + pedido.getStatus() + " para " + status);
		pedido.setStatus(status);
		return pedidoRepository.save(pedido);
	}
	
	public List<Pedido> findByStatus(StatusPedido status){
		return pedidoRepository.findByStatus(status);
	}
	
	public List<Double> taxasPagas(UUID clienteId) {
		var cliente = clienteService.findById(clienteId);
		return pedidoRepository.findTotalTaxaByClienteId(cliente);
	}
	
	public Pedido cancelPedido(UUID id) {
		Pedido pedido = findById(id);
		pedido.setStatus(StatusPedido.CANCELADO);
		return pedidoRepository.save(pedido);
	}
	public List<Pedido> updatedDesc(){
		return pedidoRepository.findAllByOrderByUpdatedAtDesc();
	}
	
	public List<Pedido> createdDesc(){
		return pedidoRepository.findAllByOrderByCreatedAtDesc();
	}
	
	public Pedido createPedido(PedidoDto pedidoDto) {
		var pedido = new Pedido();
		var cliente = clienteService.findById(pedidoDto.clienteId());
		BeanUtils.copyProperties(pedidoDto, pedido);
		pedido.setCliente(cliente);
		return pedidoRepository.save(pedido);
	}
	
	public Pedido uptadePedido(UUID id, PedidoDto pedidoDto) {
		var pedido =  findById(id);
		var cliente = clienteService.findById(pedidoDto.clienteId());
		BeanUtils.copyProperties(pedidoDto, pedido);
		pedido.setCliente(cliente);
		return pedidoRepository.save(pedido);
	}
	
	public void deletePedido(UUID id) {
		var pedido = findById(id);
		pedidoRepository.delete(pedido);
	}
	public byte[] orcamentoCompleto(UUID transporteId) {
		try(
		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
		        PdfDocument pdf = new PdfDocument(writer);
		        Document document = new Document(pdf);
				){
			
			var transporte = transporteService.findById(transporteId);
			
			document.add(new Paragraph("Ola " + transporte.getPedido().getCliente().getNome() +  ", aqui está o seu orçamento"));
			document.add(new Paragraph("Valor do transporte: " + transporte.getValorTransporte()));
			document.add(new Paragraph("Valor do veiculo: " + transporte.getVeiculo().getPrecoBase()));
			document.add(new Paragraph("Taxa de importação do veiculo: " + transporte.getVeiculo().getTaxaImportacao()));
			document.add(new Paragraph("Total de taxas: " + transporte.getPedido().getTotalTaxa()));
			document.add(new Paragraph("Valor final: " + transporte.getPedido().getValorFinal()));
			document.close();
			
			return byteArrayOutputStream.toByteArray();
			
			
		}
		catch(Exception e) {
			throw new RuntimeException("Erro ao gerar pdf", e);
		}
	}
}
