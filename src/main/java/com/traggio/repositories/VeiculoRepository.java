package com.traggio.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

	List<Veiculo> findByMarca(String marca);
	List<Veiculo> findByModelo(String modelo);
	List<Veiculo> findByPaisOrigem(String paisOrigem);
}
