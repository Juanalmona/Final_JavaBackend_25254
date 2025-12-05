package org.ecommerce.domain;

public class Item {
    private int idItem;
    private int cantidadProducto;
    private Producto producto;
    private Pedido pedido; // relación inversa opcional


    public Item() {
    }


    public Item(int idItem, int cantidadProducto, Producto producto) {
        this.idItem = idItem;
        this.cantidadProducto = cantidadProducto;
        this.producto = producto;
    }


    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", cantidadProducto=" + cantidadProducto +
                ", producto=" + (producto != null ? producto.getNombreProducto() : "null") +
                '}';
    }
}