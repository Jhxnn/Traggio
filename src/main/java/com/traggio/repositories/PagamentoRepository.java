package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

}
