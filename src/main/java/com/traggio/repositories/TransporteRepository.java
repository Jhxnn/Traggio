package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Transporte;

public interface TransporteRepository extends JpaRepository<Transporte, UUID> {

}
