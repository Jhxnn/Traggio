package com.traggio.dtos;

import java.time.LocalDate;

public record VeiculoDto(
		String marca, 
		String modelo,
		LocalDate anoFabricacao,
		LocalDate anoModelo,
		String cor,
		int VIN,
		String paisOrigem,
		double precoBase,
		double taxaImportacao
		) {

}
