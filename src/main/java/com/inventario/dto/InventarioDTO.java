package com.inventario.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "inventarios", itemRelation = "inventario")
public class InventarioDTO extends RepresentationModel<InventarioDTO> {

    private Integer idInventario;
    private Integer stockDisponible;
    private String nombreProducto;
    private String nombreSucursal;
    private String nombreUsuario;
}
