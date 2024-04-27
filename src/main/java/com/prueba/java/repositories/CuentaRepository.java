package com.prueba.java.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prueba.java.models.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long>{

}
