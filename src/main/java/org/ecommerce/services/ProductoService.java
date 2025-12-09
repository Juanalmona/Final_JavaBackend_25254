package org.ecommerce.services;

import org.ecommerce.domain.Producto;
import org.ecommerce.repository.ProductoArchivo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductoService {

    private final ProductoArchivo productoArchivo;


    public ProductoService() {
        this.productoArchivo = new ProductoArchivo();
    }


    public void createProducto(String nombre, float precio, int stock) {
        try {
            int ultimaId = productoArchivo.findLastId();
            int nuevaId = ultimaId + 1;
            Producto nuevoProducto = new Producto(nuevaId, nombre, precio, stock);
            productoArchivo.create(nuevoProducto);
        } catch (IOException e) {
            throw new RuntimeException("Error al crear producto", e);
        }
    }


    public Optional<Producto> readProducto(int idProducto) {
        try {
            return productoArchivo.readById(idProducto);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer producto con ID: " + idProducto, e);
        }
    }


    public List<Producto> readAllProductos() {
        try {
            return productoArchivo.readAll();
        } catch (IOException e) {
            throw new RuntimeException("Error al listar productos", e);
        }
    }


    public boolean updateProducto(int idProducto, String nuevoNombre, float nuevoPrecio, int nuevoStock) {
        try {
            Optional<Producto> optProducto = productoArchivo.readById(idProducto);
            if (optProducto.isEmpty()) {
                return false;
            }
            Producto producto = optProducto.get();
            producto.setNombreProducto(nuevoNombre);
            producto.setPrecioProducto(nuevoPrecio);
            producto.setStockProducto(nuevoStock);
            return productoArchivo.update(producto);
        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar producto con ID: " + idProducto, e);
        }
    }


    public void deleteProducto(int idProducto) {
        try {
            productoArchivo.deleteById(idProducto);
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar producto con ID: " + idProducto, e);
        }
    }
}