package com.deliverytech.delivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.deliverytech.delivery.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

    Optional<ClienteEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    List<ClienteEntity> findByAtivoTrue();

    List<ClienteEntity> findByNomeContainingIgnoreCase(String nome);

    Optional<ClienteEntity> findByTelefone(String telefone);

    @Query(value = "SELECT * FROM clientes WHERE endereco LIKE %:cidade% AND ativo = true", nativeQuery = true)
    List<ClienteEntity> findByCidade(@Param("cidade") String cidade);

    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.ativo = true")
    Long countClientesAtivos();

}
