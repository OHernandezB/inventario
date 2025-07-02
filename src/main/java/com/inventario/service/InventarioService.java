package com.inventario.service;

import com.inventario.models.Inventario;
import com.inventario.models.Producto;
import com.inventario.models.Sucursal;
import com.inventario.models.Usuario;
import com.inventario.dto.InventarioDTO;
import com.inventario.repository.InventarioRepository;
import com.inventario.repository.ProductoRepository;
import com.inventario.repository.SucursalRepository;
import com.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private SucursalRepository sucursalRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    private InventarioDTO toDTO(Inventario inventario){
        return new InventarioDTO(
                inventario.getIdInventario(),
                inventario.getStockDisponible(),
                inventario.getProducto() != null ? inventario.getProducto().getNombreProducto() : null,
                inventario.getSucursal() != null ? inventario.getSucursal().getNombreSucursal() : null,
                inventario.getUsuario() != null ? inventario.getUsuario().getNombreUsuario() : null
        );
    }

    private Inventario toEntity(InventarioDTO dto){
        Inventario inventario = new Inventario();
        inventario.setIdInventario(dto.getIdInventario());
        inventario.setStockDisponible(dto.getStockDisponible());
        
        // Buscar las entidades relacionadas por nombre con validación null
        if (dto.getNombreProducto() != null && !dto.getNombreProducto().trim().isEmpty()) {
            Producto producto = productoRepository.findAll().stream()
                    .filter(p -> p.getNombreProducto() != null && 
                               p.getNombreProducto().equals(dto.getNombreProducto()))
                    .findFirst()
                    .orElse(null);
            inventario.setProducto(producto);
        }
        
        if (dto.getNombreSucursal() != null && !dto.getNombreSucursal().trim().isEmpty()) {
            Sucursal sucursal = sucursalRepository.findAll().stream()
                    .filter(s -> s.getNombreSucursal() != null && 
                               s.getNombreSucursal().equals(dto.getNombreSucursal()))
                    .findFirst()
                    .orElse(null);
            inventario.setSucursal(sucursal);
        }
        
        if (dto.getNombreUsuario() != null && !dto.getNombreUsuario().trim().isEmpty()) {
            Usuario usuario = usuarioRepository.findAll().stream()
                    .filter(u -> u.getNombreUsuario() != null && 
                               u.getNombreUsuario().equals(dto.getNombreUsuario()))
                    .findFirst()
                    .orElse(null);
            inventario.setUsuario(usuario);
        }
        
        return inventario;
    }

    public InventarioDTO crear(InventarioDTO dto) {
        // Validar que los datos requeridos existan
        if (dto.getNombreProducto() != null && !dto.getNombreProducto().trim().isEmpty()) {
            boolean productoExiste = productoRepository.findAll().stream()
                    .anyMatch(p -> p.getNombreProducto() != null && 
                                 p.getNombreProducto().equals(dto.getNombreProducto()));
            if (!productoExiste) {
                throw new RuntimeException("El producto '" + dto.getNombreProducto() + "' no existe en la base de datos");
            }
        }
        
        if (dto.getNombreSucursal() != null && !dto.getNombreSucursal().trim().isEmpty()) {
            boolean sucursalExiste = sucursalRepository.findAll().stream()
                    .anyMatch(s -> s.getNombreSucursal() != null && 
                                 s.getNombreSucursal().equals(dto.getNombreSucursal()));
            if (!sucursalExiste) {
                throw new RuntimeException("La sucursal '" + dto.getNombreSucursal() + "' no existe en la base de datos");
            }
        }
        
        if (dto.getNombreUsuario() != null && !dto.getNombreUsuario().trim().isEmpty()) {
            boolean usuarioExiste = usuarioRepository.findAll().stream()
                    .anyMatch(u -> u.getNombreUsuario() != null && 
                                 u.getNombreUsuario().equals(dto.getNombreUsuario()));
            if (!usuarioExiste) {
                throw new RuntimeException("El usuario '" + dto.getNombreUsuario() + "' no existe en la base de datos");
            }
        }
        
        Inventario inventario = toEntity(dto);
        return toDTO(inventarioRepository.save(inventario));
    }

    public List<InventarioDTO> lista(){
        return inventarioRepository.findAll().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
    }

    public InventarioDTO obtenerInventarioDTO(Integer id){
        Inventario inventario = inventarioRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        return toDTO(inventario);
    }

    public InventarioDTO actualizar(Integer id, InventarioDTO dto){
        Inventario existente = inventarioRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        
        existente.setStockDisponible(dto.getStockDisponible());
        
        // Actualizar relaciones con validación
        if (dto.getNombreProducto() != null && !dto.getNombreProducto().trim().isEmpty()) {
            Producto producto = productoRepository.findAll().stream()
                    .filter(p -> p.getNombreProducto() != null && 
                               p.getNombreProducto().equals(dto.getNombreProducto()))
                    .findFirst()
                    .orElse(null);
            existente.setProducto(producto);
        }
        
        if (dto.getNombreSucursal() != null && !dto.getNombreSucursal().trim().isEmpty()) {
            Sucursal sucursal = sucursalRepository.findAll().stream()
                    .filter(s -> s.getNombreSucursal() != null && 
                               s.getNombreSucursal().equals(dto.getNombreSucursal()))
                    .findFirst()
                    .orElse(null);
            existente.setSucursal(sucursal);
        }
        
        if (dto.getNombreUsuario() != null && !dto.getNombreUsuario().trim().isEmpty()) {
            Usuario usuario = usuarioRepository.findAll().stream()
                    .filter(u -> u.getNombreUsuario() != null && 
                               u.getNombreUsuario().equals(dto.getNombreUsuario()))
                    .findFirst()
                    .orElse(null);
            existente.setUsuario(usuario);
        }
        
        return toDTO(inventarioRepository.save(existente));
    }

    public void eliminar(Integer id){
        inventarioRepository.deleteById(id);
    }
}
