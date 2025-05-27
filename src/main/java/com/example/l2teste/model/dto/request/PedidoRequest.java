package com.example.l2teste.model.dto.request;

import com.example.l2teste.model.dto.Pedido;

import java.util.List;

public class PedidoRequest {
    private List<Pedido> pedidos;

    // Getters and Setters
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}