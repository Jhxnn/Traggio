package com.traggio.dtos;

import com.traggio.models.enums.Roles;

public record RegisterDto(String email, String password, Roles role) {

}
