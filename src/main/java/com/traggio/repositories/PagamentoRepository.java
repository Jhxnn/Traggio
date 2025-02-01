package com.traggio.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.traggio.models.Pagamento;
import com.traggio.models.enums.MeioPagamento;
import com.traggio.models.enums.StatusPagamento;



public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

	
	List<Pagamento> findByMeioPagamento(MeioPagamento meioPagamento);
	
	List<Pagamento> findByStatus(StatusPagamento status);
	
	@Query("SELECT p FROM Pagamento p WHERE p.dataPagamento BETWEEN :dataInicio AND :dataFim")
	List<Pagamento> findAllByDataPagamentoBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

}
