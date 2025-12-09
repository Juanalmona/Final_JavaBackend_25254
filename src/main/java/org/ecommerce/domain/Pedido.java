package org.ecommerce.domain;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private boolean confirmado;
    private List<Item> items;


    public Pedido() {
        this.items = new ArrayList<>();
        this.confirmado = false;
    }


    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        this.items = new ArrayList<>();
        this.confirmado = false;
    }


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

    public void confirmar() {
        this.confirmado = true;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", confirmado=" + confirmado +
                ", items=" + items +
                '}';
    }
}