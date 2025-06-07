package com.inventario.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_inventario")
    private Integer id_Inventario;
    private Integer stockDisponible;

    @OneToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Integer idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Double precioProducto;
    private String categoriaProducto;
    private String activo;



}
