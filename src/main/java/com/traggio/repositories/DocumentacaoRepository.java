package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Documentacao;

public interface DocumentacaoRepository extends JpaRepository<Documentacao, UUID> {

}
