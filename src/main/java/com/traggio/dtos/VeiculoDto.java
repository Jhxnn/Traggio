package com.traggio.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record VeiculoDto(
		String marca, 
		String modelo,
		LocalDate anoFabricacao,
		LocalDate anoModelo,
		String cor,
		int VIN,
		String paisOrigem,
		double precoBase,
		double taxaImportacao,
		UUID fornecedorId
		) {

}
