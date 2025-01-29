package com.traggio.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "veiculos")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID veiculoId;
	
	private String marca;
	
	private String Modelo;
	
	private LocalDate anoFabricacao;
	
	private LocalDate anoModelo;
	
	private String cor;
	
	private int VIN;
	
	private String paisOrigem;
	
	private double precoBase;
	
	private double taxaImportacao;

	public UUID getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(UUID veiculoId) {
		this.veiculoId = veiculoId;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public LocalDate getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(LocalDate anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public LocalDate getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(LocalDate anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getVIN() {
		return VIN;
	}

	public void setVIN(int vIN) {
		VIN = vIN;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public double getPrecoBase() {
		return precoBase;
	}

	public void setPrecoBase(double precoBase) {
		this.precoBase = precoBase;
	}

	public double getTaxaImportacao() {
		return taxaImportacao;
	}

	public void setTaxaImportacao(double taxaImportacao) {
		this.taxaImportacao = taxaImportacao;
	}
	
	
}
