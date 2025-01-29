package com.traggio.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID pagamentoId;
	
	@JoinColumn(referencedColumnName = "id", name = "pedidoId")
	@ManyToOne
	private Pedido pedido;
	
	private LocalDate dataPagamento;
	
	private double valor;

	public UUID getPagamentoId() {
		return pagamentoId;
	}

	public void setPagamentoId(UUID pagamentoId) {
		this.pagamentoId = pagamentoId;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
//	private MeioPagamento meioPagamento;
	
//	private StatusPagamento status;
	
	
	
}
