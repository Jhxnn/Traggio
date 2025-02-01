package com.traggio.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.traggio.models.Cliente;
import com.traggio.models.Pedido;
import com.traggio.models.enums.StatusPedido;


public interface PedidoRepository extends JpaRepository<Pedido, UUID>  {

	
	List<Pedido> findByCliente(Cliente cliente);
	
	List<Pedido> findAllByOrderByUpdatedAtDesc();
	
	List<Pedido> findAllByOrderByCreatedAtDesc();
	
	
	@Query("SELECT p.totalTaxa FROM Pedido p WHERE p.cliente = :cliente")
    List<Double> findTotalTaxaByClienteId(@Param("cliente") Cliente cliente);
	
	
	List<Pedido> findByStatus(StatusPedido status);
}
