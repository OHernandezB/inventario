package com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.models.Inventario;



@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {



}
