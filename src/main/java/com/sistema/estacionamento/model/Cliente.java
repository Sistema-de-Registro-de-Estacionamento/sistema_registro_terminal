package com.sistema.estacionamento.model;

public class Cliente {

    private String nome;
    private String placa;
    private String cpf;

    public Cliente(String nome, String placa, String cpf){
        this.nome = nome;
        this.placa = placa;
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }

    public String getPlaca(){
        return placa;
    }

    public String getCpf(){
        return cpf;
    }

    public String toString(){
        return "{ Nome: " + nome + " | CPF: " + cpf + " | Placa: " + placa + " }";
    }

}
