package com.example.l2teste.model.dto;

import java.util.List;

public class Pedido {
    private int pedido_id;
    private List<Produto> produtos;

    // Getters and Setters
    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}