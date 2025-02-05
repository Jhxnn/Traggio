package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Cliente;
import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	
	Cliente findByEmail(String email);

}
