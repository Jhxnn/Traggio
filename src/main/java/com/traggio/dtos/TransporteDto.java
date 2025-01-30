package com.traggio.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.traggio.models.enums.StatusTransporte;

public record TransporteDto(
		UUID veiculoId,
		UUID despachanteId,
		UUID pedidoId,
		int numeroContainer,
		LocalDate dataEmbarque,
		LocalDate dataPrevisaoChegada,
		LocalDate dataChegada,
		StatusTransporte status
		) {

}
