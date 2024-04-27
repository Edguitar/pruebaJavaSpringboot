package com.prueba.java.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.java.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Long>{
	Optional<Cliente> findByIdentificacion(@Param("identificacion") String identificacion);
}
