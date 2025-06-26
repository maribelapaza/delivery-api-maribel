package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.deliverytech.delivery.entity.ClienteEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClienteEntity, Long>{

}
