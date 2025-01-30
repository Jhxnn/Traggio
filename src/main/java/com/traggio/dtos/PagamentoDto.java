package com.traggio.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.traggio.models.enums.MeioPagamento;
import com.traggio.models.enums.StatusPagamento;

public record PagamentoDto(
		UUID pedidoId,
		LocalDate dataPagamento,
		double valor,
		MeioPagamento meioPagamento,
		StatusPagamento status 
		
		) {

}
