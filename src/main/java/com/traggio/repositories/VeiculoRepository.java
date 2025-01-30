package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

}
