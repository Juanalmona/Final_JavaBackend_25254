package org.ecommerce.dto;

import java.util.List;

public class PedidoDTO {
    private int idPedido;
    private boolean confirmado;
    private float total;
    private List<ItemDTO> items;

    public PedidoDTO() {}

    public PedidoDTO(int idPedido, boolean confirmado, float total, List<ItemDTO> items) {
        this.idPedido = idPedido;
        this.confirmado = confirmado;
        this.total = total;
        this.items = items;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public boolean isConfirmado() { return confirmado; }
    public void setConfirmado(boolean confirmado) { this.confirmado = confirmado; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }

    public List<ItemDTO> getItems() { return items; }
    public void setItems(List<ItemDTO> items) { this.items = items; }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "idPedido=" + idPedido +
                ", confirmado=" + confirmado +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}