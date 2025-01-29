package com.traggio.models;

import java.time.LocalDate;
import java.util.UUID;

import com.traggio.models.enums.StatusDocumento;
import com.traggio.models.enums.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documentacoes")
public class Documentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID documentacaoId;
	
	
	@JoinColumn(referencedColumnName = "id", name = "pedidoId")
	@ManyToOne
	private Pedido pedido;
	
	private LocalDate dataEnvio;
	
	private LocalDate dataRecebimento;
	
	private TipoDocumento tipo;
	
	private StatusDocumento status;

	public UUID getDocumentacaoId() {
		return documentacaoId;
	}
	

	public TipoDocumento getTipo() {
		return tipo;
	}


	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
	}


	public StatusDocumento getStatus() {
		return status;
	}


	public void setStatus(StatusDocumento status) {
		this.status = status;
	}


	public void setDocumentacaoId(UUID documentacaoId) {
		this.documentacaoId = documentacaoId;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	

	
	
}
