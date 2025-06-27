package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.PedidoEntity;

@Repository
public interface PedidoRepository  extends JpaRepository<PedidoEntity, Long>{
//Buscar pedidos por cliente, filtrar por status/data, relatórios


}
