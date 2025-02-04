package com.traggio.dtos;

import java.time.LocalDate;

public record ClienteDto(
		String nome,
		String email,
		String senha,
		String cpf,
		LocalDate dataCadastro
		) {

}
