package com.traggio.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.traggio.models.enums.StatusDocumento;
import com.traggio.models.enums.TipoDocumento;

public record DocumentacaoDto(
		UUID pedidoId,
		LocalDate dataEnvio,
		LocalDate dataRecebimento, 
		TipoDocumento tipo, 
		StatusDocumento status
		
		
		) {

}
