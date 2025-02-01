package com.traggio.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.traggio.models.Transporte;

public interface TransporteRepository extends JpaRepository<Transporte, UUID> {
	
	@Query("SELECT v.modelo, COUNT(t) AS totalUso " +
		       "FROM Transporte t " +
		       "JOIN t.veiculo v " +
		       "GROUP BY v.modelo " +
		       "ORDER BY totalUso DESC")
		List<Object[]> findMostUsedVehicleModels();



}
