package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

}
