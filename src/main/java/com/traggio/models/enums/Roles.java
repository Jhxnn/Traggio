package com.traggio.models.enums;

public enum Roles {
	ADMIN("admin"),
	BASIC("basic"),
	FORNECEDOR("fornecedor");
	
	private String role;
	
	
	Roles(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
