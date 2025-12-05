package org.ecommerce.repository;

import org.ecommerce.domain.Item;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ItemArchivo {
    private static final String ARCHIVO_ITEMS = "item.json";
    private final ObjectMapper mapper;

    public ItemArchivo() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    private List<Item> loadItems(File archivo) throws IOException {
        if (archivo.exists()) {
            return mapper.readValue(archivo, new TypeReference<List<Item>>() {});
        }
        return new ArrayList<>();
    }


    public synchronized void create(Item item) throws IOException {
        File archivo = new File(ARCHIVO_ITEMS);
        List<Item> items = loadItems(archivo);
        items.add(item);
        mapper.writeValue(archivo, items);
    }


    public Optional<Item> readById(int idItem) throws IOException {
        File archivo = new File(ARCHIVO_ITEMS);
        return loadItems(archivo).stream()
                .filter(i -> i.getIdItem() == idItem)
                .findFirst();
    }


    public List<Item> readAll() throws IOException {
        return loadItems(new File(ARCHIVO_ITEMS));
    }


    public synchronized boolean update(Item item) throws IOException {
        File archivo = new File(ARCHIVO_ITEMS);
        List<Item> items = loadItems(archivo);

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getIdItem() == item.getIdItem()) {
                items.set(i, item);
                mapper.writeValue(archivo, items);
                return true;
            }
        }
        return false;
    }


    public synchronized boolean deleteById(int idItem) throws IOException {
        File archivo = new File(ARCHIVO_ITEMS);
        List<Item> items = loadItems(archivo);
        boolean eliminado = items.removeIf(i -> i.getIdItem() == idItem);
        if (eliminado) {
            mapper.writeValue(archivo, items);
        }
        return eliminado;
    }


    public int findLastId() throws IOException {
        File archivo = new File(ARCHIVO_ITEMS);
        return loadItems(archivo).stream()
                .mapToInt(Item::getIdItem)
                .max()
                .orElse(0);
    }
}