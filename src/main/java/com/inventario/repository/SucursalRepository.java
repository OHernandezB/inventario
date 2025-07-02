package com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventario.models.Sucursal;
 
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
} 