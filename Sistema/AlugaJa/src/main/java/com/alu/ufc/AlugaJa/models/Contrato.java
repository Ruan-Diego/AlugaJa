package com.alu.ufc.AlugaJa.models;

public class Contrato {
    String id_usuario;
    String id_Imovel;
    int validade;
    float valor_imovel;

    public Contrato() {
    }

    public Contrato(String id_usuario, String id_Imovel, int validade, float valor_imovel) {
        this.id_usuario = id_usuario;
        this.id_Imovel = id_Imovel;
        this.validade = validade;
        this.valor_imovel = valor_imovel;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_imovel() {
        return id_Imovel;
    }

    public void setId_imovel(String id_Imovel) {
        this.id_Imovel = id_Imovel;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public float getValor_imovel() {
        return valor_imovel;
    }

    public void setValor_imovel(float valor_imovel) {
        this.valor_imovel = valor_imovel;
    }
}