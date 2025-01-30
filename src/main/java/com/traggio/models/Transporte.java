package com.traggio.models;

import java.time.LocalDate;

import com.traggio.models.enums.StatusTransporte;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transportes")
public class Transporte {

   @OneToOne
   @JoinColumn(referencedColumnName = "id", name = "veiculo_id")
   private Veiculo veiculo;
   
   @ManyToOne
   @JoinColumn(referencedColumnName = "id", name = "despachante_id")
   private Despachante despachante;
   
   @OneToOne
   @JoinColumn(referencedColumnName = "id", name="pedido_id")
   private Pedido pedido;
   
   private int numeroContainer;
   
   private LocalDate dataEmbarque;
   
   private LocalDate dataPrevisaoChegada;
   
   private LocalDate dataChegada;

   private StatusTransporte status;
   
   
	public StatusTransporte getStatus() {
		return status;
	}
	
	public void setStatus(StatusTransporte status) {
		this.status = status;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Despachante getDespachante() {
		return despachante;
	}
	
	public void setDespachante(Despachante despachante) {
		this.despachante = despachante;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public int getNumeroContainer() {
		return numeroContainer;
	}
	
	public void setNumeroContainer(int numeroContainer) {
		this.numeroContainer = numeroContainer;
	}
	
	public LocalDate getDataEmbarque() {
		return dataEmbarque;
	}
	
	public void setDataEmbarque(LocalDate dataEmbarque) {
		this.dataEmbarque = dataEmbarque;
	}
	
	public LocalDate getDataPrevisaoChegada() {
		return dataPrevisaoChegada;
	}
	
	public void setDataPrevisaoChegada(LocalDate dataPrevisaoChegada) {
		this.dataPrevisaoChegada = dataPrevisaoChegada;
	}
	
	public LocalDate getDataChegada() {
		return dataChegada;
	}
	
	public void setDataChegada(LocalDate dataChegada) {
		this.dataChegada = dataChegada;
	}
	   
	   
	   
   
}
