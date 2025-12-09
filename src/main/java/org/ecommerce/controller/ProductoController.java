package org.ecommerce.controller;

import org.ecommerce.domain.Producto;
import org.ecommerce.dto.ProductoDTO;
import org.ecommerce.services.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController() {
        this.productoService = new ProductoService();
    }


    @PostMapping
    public void crearProducto(@RequestBody ProductoDTO dto) {
        productoService.createProducto(
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock()
        );
    }


    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return productoService.readAllProductos()
                .stream()
                .map(p -> new ProductoDTO(
                        p.getIdProducto(),
                        p.getNombreProducto(),
                        p.getPrecioProducto(),
                        p.getStockProducto()
                ))
                .toList();
    }


    @GetMapping("/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable int id) {
        return productoService.readProducto(id)
                .map(p -> new ProductoDTO(
                        p.getIdProducto(),
                        p.getNombreProducto(),
                        p.getPrecioProducto(),
                        p.getStockProducto()
                ))
                .orElse(null);
    }


    @PutMapping("/{id}")
    public boolean actualizarProducto(@PathVariable int id, @RequestBody ProductoDTO dto) {
        return productoService.updateProducto(
                id,
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock()
        );
    }


    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        productoService.deleteProducto(id);
    }
}