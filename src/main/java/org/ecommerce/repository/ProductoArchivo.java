package org.ecommerce.repository;

import org.ecommerce.domain.Producto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductoArchivo {

    private static final String ARCHIVO_PRODUCTOS = "producto.json";
    private final ObjectMapper mapper;


    public ProductoArchivo() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    public void create(Producto producto) throws IOException {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        List<Producto> productos = new ArrayList<>();
        if (archivo.exists()) {
            productos = mapper.readValue(archivo, new TypeReference<List<Producto>>() {});
        }
        productos.add(producto);
        mapper.writeValue(archivo, productos);
    }


    public Optional<Producto> readById(int idProducto) throws IOException {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (archivo.exists()) {
            List<Producto> productos = mapper.readValue(archivo, new TypeReference<List<Producto>>() {});
            return productos.stream()
                    .filter(p -> p.getIdProducto() == idProducto)
                    .findFirst();
        }
        return Optional.empty();
    }


    public List<Producto> readAll() throws IOException {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (archivo.exists()) {
            return mapper.readValue(archivo, new TypeReference<List<Producto>>() {});
        }
        return new ArrayList<>();
    }


    public boolean update(Producto producto) throws IOException {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (archivo.exists()) {
            List<Producto> productos = mapper.readValue(archivo, new TypeReference<List<Producto>>() {});
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getIdProducto() == producto.getIdProducto()) {
                    productos.set(i, producto);
                    mapper.writeValue(archivo, productos);
                    return true;
                }
            }
        }
        return false;
    }


    public void deleteById(int idProducto) throws IOException {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (archivo.exists()) {
            List<Producto> productos = mapper.readValue(archivo, new TypeReference<List<Producto>>() {});
            productos.removeIf(p -> p.getIdProducto() == idProducto);
            mapper.writeValue(archivo, productos);
        }
    }


    public int findLastId() throws IOException {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        int maxId = 0;
        if (archivo.exists()) {
            List<Producto> productos = mapper.readValue(archivo, new TypeReference<List<Producto>>() {});
            for (Producto producto : productos) {
                if (producto.getIdProducto() > maxId) {
                    maxId = producto.getIdProducto();
                }
            }
        }
        return maxId;
    }
}