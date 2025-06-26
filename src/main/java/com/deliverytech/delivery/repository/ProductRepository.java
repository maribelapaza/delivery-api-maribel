package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.ProdutoEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProdutoEntity, Long>{

    //Buscar produtos por restaurante, por categoria e disponibilidade


}
