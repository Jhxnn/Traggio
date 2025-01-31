package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Pedido;
import java.util.List;
import com.traggio.models.Cliente;


public interface PedidoRepository extends JpaRepository<Pedido, UUID>  {

	
	List<Pedido> findByCliente(Cliente cliente);
}
