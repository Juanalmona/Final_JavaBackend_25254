package org.ecommerce.controller;

import org.ecommerce.dto.PedidoDTO;
import org.ecommerce.dto.ItemDTO;
import org.ecommerce.services.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController() {
        this.pedidoService = new PedidoService();
    }


    @PostMapping
    public void crearPedido() {
        pedidoService.createPedido();
    }


    @GetMapping
    public List<PedidoDTO> listarPedidos() {
        return pedidoService.readAllPedidos()
                .stream()
                .map(p -> new PedidoDTO(
                        p.getIdPedido(),
                        p.isConfirmado(),
                        pedidoService.calculateTotalPedido(p.getIdPedido()),
                        p.getItems().stream()
                                .map(i -> new ItemDTO(
                                        i.getIdItem(),
                                        i.getCantidadProducto(),
                                        i.getProducto() != null ? i.getProducto().getIdProducto() : 0
                                ))
                                .collect(Collectors.toList())
                ))
                .toList();
    }


    @GetMapping("/{id}")
    public PedidoDTO obtenerPedidoPorId(@PathVariable int id) {
        return pedidoService.readPedido(id)
                .map(p -> new PedidoDTO(
                        p.getIdPedido(),
                        p.isConfirmado(),
                        pedidoService.calculateTotalPedido(p.getIdPedido()),
                        p.getItems().stream()
                                .map(i -> new ItemDTO(
                                        i.getIdItem(),
                                        i.getCantidadProducto(),
                                        i.getProducto() != null ? i.getProducto().getIdProducto() : 0
                                ))
                                .collect(Collectors.toList())
                ))
                .orElse(null);
    }


    @PutMapping("/{idPedido}/items")
    public void agregarItem(@PathVariable int idPedido,
                            @RequestParam int idProducto,
                            @RequestParam int cantidad) {
        pedidoService.includeItem(idPedido, idProducto, cantidad);
    }


    @PutMapping("/{id}/confirmar")
    public void confirmarPedido(@PathVariable int id) {
        pedidoService.confirmPedido(id);
    }


    @GetMapping("/{id}/total")
    public float totalPedido(@PathVariable int id) {
        return pedidoService.calculateTotalPedido(id);
    }
}