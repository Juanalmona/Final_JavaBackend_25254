package org.ecommerce.dto;

public class ItemDTO {
    private int idItem;
    private int cantidad;
    private int idProducto;

    public ItemDTO() {}

    public ItemDTO(int idItem, int cantidad, int idProducto) {
        this.idItem = idItem;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
    }

    public int getIdItem() { return idItem; }
    public void setIdItem(int idItem) { this.idItem = idItem; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "idItem=" + idItem +
                ", cantidad=" + cantidad +
                ", idProducto=" + idProducto +
                '}';
    }
}