package com.inventario.service;

import com.inventario.models.Inventario;
import com.inventario.dto.InventarioDTO;
import com.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventaerioRepository;

    private InventarioDTO toDTO(Inventario inventario){
        return new InventarioDTO(
                inventario.getId_Inventario(),
                inventario.getStockDisponible(),
                inventario.getId_producto(),
                inventario.getId_sucursal(),
                inventario.getId_usuario()
        );
    }

    private Inventario toEntity(InventarioDTO dto){
        Inventario inventario = new Inventario();
        inventario.setId_Inventario(dto.getIdInventario());
        inventario.setStockDisponible(dto.getStockDisponible());
        inventario.setId_producto(dto.getIdProducto());
        inventario.setId_sucursal(dto.getIdSucursal());
        inventario.setId_usuario(dto.getIdUsuario());
        return inventario;
    }

    public InventarioDTO crear(InventarioDTO dto) {
        Inventario inventario = toEntity(dto);
        return toDTO(inventaerioRepository.save(inventario));
    }

    public List<InventarioDTO>lista(){
        return inventaerioRepository.findAll().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
    }

    public InventarioDTO obInventarioDTO(Integer id){
        Inventario inventario = inventaerioRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                      return toDTO(inventario);
    }

    public InventarioDTO actualizar(Integer id, InventarioDTO dto){
        Inventario existente =  inventaerioRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existente.setId_Inventario(dto.getIdInventario());
        existente.setStockDisponible(dto.getStockDisponible());
        existente.setId_producto(dto.getIdProducto());
        existente.setId_sucursal(dto.getIdSucursal());
        existente.setId_usuario(dto.getIdUsuario());
        return toDTO(inventaerioRepository.save(existente));

    }

    public void eliminar (Integer id){
        inventaerioRepository.deleteById(id);
    }
}
