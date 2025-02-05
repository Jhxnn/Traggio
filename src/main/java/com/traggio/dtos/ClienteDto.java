package com.traggio.dtos;

import java.time.LocalDate;

import com.traggio.models.enums.Roles;

public record ClienteDto(
		String nome,
		String email,
		String senha,
		String cpf,
		LocalDate dataCadastro,
		Roles role
		) {

}
