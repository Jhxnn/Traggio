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

	        return byteArrayOutputStream.toByteArray();
	    } catch (Exception e) {
	        throw new RuntimeException("Erro ao gerar o relatório de atraso", e);
	    }
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
