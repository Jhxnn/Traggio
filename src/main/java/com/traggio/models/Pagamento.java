package com.traggio.models;

import java.time.LocalDate;
import java.util.UUID;

import com.traggio.models.enums.MeioPagamento;
import com.traggio.models.enums.StatusPagamento;

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
	
	private MeioPagamento meioPagamento;
	
	private StatusPagamento status;

	public UUID getPagamentoId() {
		return pagamentoId;
	}
	

	public MeioPagamento getMeioPagamento() {
		return meioPagamento;
	}


	public void setMeioPagamento(MeioPagamento meioPagamento) {
		this.meioPagamento = meioPagamento;
	}


	public StatusPagamento getStatus() {
		return status;
	}


	public void setStatus(StatusPagamento status) {
		this.status = status;
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
	
	
	
}
