package com.example.l2teste.model.dto;

public class Dimensoes {
    private int altura;
    private int largura;
    private int comprimento;

    public Dimensoes()
    {

    }
    public Dimensoes(int [] dimensoes) {
        altura = dimensoes[0];
        largura = dimensoes[1];
        comprimento = dimensoes[2];
    }
    // Getters and Setters
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getComprimento() {
        return comprimento;
    }

    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }
}