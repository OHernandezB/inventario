package com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventario.models.Usuario;
 
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
} 