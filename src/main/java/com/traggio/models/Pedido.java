package com.traggio.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.traggio.models.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID pedidoId;
	
	
	@ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "cliente_id")
    private Cliente cliente;
	
	private LocalDate dataPedido;
	
	private StatusPedido status;
	
	private double totalTaxa;
	
	private double valorFinal;
	
	private String observacao;
	
	private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

	
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

	public UUID getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(UUID pedidoId) {
		this.pedidoId = pedidoId;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public double getTotalTaxa() {
		return totalTaxa;
	}

	public void setTotalTaxa(double totalTaxa) {
		this.totalTaxa = totalTaxa;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
}
