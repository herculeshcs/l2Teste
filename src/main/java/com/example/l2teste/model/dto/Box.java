package com.example.l2teste.model.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Box {
    private int altura;
    private int largura;
    private int comprimento;
    private String id;
    private String observacao;
    private final List<String> produtoIds = new ArrayList<>();

    public Box(int altura, int largura, int comprimento, String id ) {
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.id = id;
    }
    public Box (Box box) {
        this.altura = box.altura;
        this.largura = box.largura;
        this.comprimento = box.comprimento;
        this.id = new String(box.id);
    }
    public boolean canAdd(Produto produto) {
        return produto.getDimensoes().getAltura() <= altura &&
                produto.getDimensoes().getLargura() <= largura &&
                produto.getDimensoes().getComprimento() <= comprimento;
    }

    public void addProduto(Produto produto) {
        produtoIds.add(produto.getProduto_id());
    }


    public List<String> getProdutoIds() { return new ArrayList<>(produtoIds); }
}