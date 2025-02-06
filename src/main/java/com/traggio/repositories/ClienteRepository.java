package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.traggio.models.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	
	UserDetails findByEmail(String email);

}
