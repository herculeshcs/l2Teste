package com.example.l2teste.model.dto;

public class Produto {
    private String produto_id;
    private Dimensoes dimensoes;

    // Getters and Setters
    public String getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(String produto_id) {
        this.produto_id = produto_id;
    }

    public Dimensoes getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(Dimensoes dimensoes) {
        this.dimensoes = dimensoes;
    }
}