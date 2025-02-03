package com.traggio.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.traggio.models.enums.StatusPedido;

public record PedidoDto(
		UUID clienteId,
		LocalDate dataPedido,
		StatusPedido status, 
		String observacao	
		) {

}
