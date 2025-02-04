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
import com.traggio.dtos.DocumentacaoDto;
import com.traggio.models.Documentacao;
import com.traggio.repositories.DocumentacaoRepository;

@Service
public class DocumentacaoService {

	@Autowired
	DocumentacaoRepository documentacaoRepository;
	
	@Autowired
	PedidoService pedidoService;
	
	
	
	
	public Documentacao findById(UUID id) {
		return documentacaoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Documentacao> findAll(){
		return documentacaoRepository.findAll();
	}
	
	public Documentacao createDocumentacao(DocumentacaoDto documentacaoDto) {
		var documentacao = new Documentacao();
		var pedido = pedidoService.findById(documentacaoDto.pedidoId());
		BeanUtils.copyProperties(documentacaoDto, documentacao);
		documentacao.setPedido(pedido);
		return documentacaoRepository.save(documentacao);

	}
	public byte[] gerarPdfDocumento(UUID idDocument) {
		var documento = findById(idDocument);
		try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
		        PdfDocument pdf = new PdfDocument(writer);
		        Document document = new Document(pdf);
				){
			document.add(new Paragraph("ID do documento: " + documento.getDocumentacaoId()));
			document.add(new Paragraph("Status: " + documento.getStatus()));
			document.add(new Paragraph("Tipo do docmento: " + documento.getTipo()));
			document.add(new Paragraph("Data envio: " + documento.getDataEnvio()));
			document.add(new Paragraph("Data de recebimento: " + documento.getDataRecebimento()));
			document.close();
			return byteArrayOutputStream.toByteArray();
		}
		
		catch(Exception e) {
			throw new RuntimeException("Erro ao gerar pdf",e);
		}
		
	}
	
	public Documentacao uptadeDocumentacao(UUID id, DocumentacaoDto documentacaoDto) {
		var documentacao =  findById(id);
		var pedido = pedidoService.findById(documentacaoDto.pedidoId());
		BeanUtils.copyProperties(documentacaoDto, documentacao);
		documentacao.setPedido(pedido);
		return documentacaoRepository.save(documentacao);
	}
	
	public void deleteDocumentacao(UUID id) {
		var documentacao = findById(id);
		documentacaoRepository.delete(documentacao);
	}
}
