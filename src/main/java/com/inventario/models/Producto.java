package com.inventario.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;
    
    @Column(name = "nombre_producto")
    private String nombreProducto;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "precio")
    private Double precio;
} 