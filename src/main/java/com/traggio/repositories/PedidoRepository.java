package com.traggio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traggio.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>  {

}
