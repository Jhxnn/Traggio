package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Despachante;

public interface DespachanteRepository extends JpaRepository<Despachante, UUID>  {

}
