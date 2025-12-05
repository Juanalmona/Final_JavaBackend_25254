package org.ecommerce.repository;

import org.ecommerce.domain.Pedido;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PedidoArchivo {
    private static final String ARCHIVO_PEDIDOS = "pedido.json";
    private final ObjectMapper mapper;

    public PedidoArchivo() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    private List<Pedido> loadPedidos(File archivo) throws IOException {
        if (archivo.exists()) {
            return mapper.readValue(archivo, new TypeReference<List<Pedido>>() {});
        }
        return new ArrayList<>();
    }


    public synchronized void create(Pedido pedido) throws IOException {
        File archivo = new File(ARCHIVO_PEDIDOS);
        List<Pedido> pedidos = loadPedidos(archivo);
        pedidos.add(pedido);
        mapper.writeValue(archivo, pedidos);
    }


    public Optional<Pedido> readById(int idPedido) throws IOException {
        File archivo = new File(ARCHIVO_PEDIDOS);
        return loadPedidos(archivo).stream()
                .filter(p -> p.getIdPedido() == idPedido)
                .findFirst();
    }


    public List<Pedido> readAll() throws IOException {
        return loadPedidos(new File(ARCHIVO_PEDIDOS));
    }


    public synchronized boolean update(Pedido pedido) throws IOException {
        File archivo = new File(ARCHIVO_PEDIDOS);
        List<Pedido> pedidos = loadPedidos(archivo);

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getIdPedido() == pedido.getIdPedido()) {
                pedidos.set(i, pedido);
                mapper.writeValue(archivo, pedidos);
                return true;
            }
        }
        return false;
    }


    public synchronized void deleteById(int idPedido) throws IOException {
        File archivo = new File(ARCHIVO_PEDIDOS);
        List<Pedido> pedidos = loadPedidos(archivo);
        pedidos.removeIf(p -> p.getIdPedido() == idPedido);
        mapper.writeValue(archivo, pedidos);
    }


    public int findLastId() throws IOException {
        File archivo = new File(ARCHIVO_PEDIDOS);
        return loadPedidos(archivo).stream()
                .mapToInt(Pedido::getIdPedido)
                .max()
                .orElse(0);
    }
}