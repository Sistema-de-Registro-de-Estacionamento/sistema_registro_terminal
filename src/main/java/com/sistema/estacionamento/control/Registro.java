package com.sistema.estacionamento.control;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import com.sistema.estacionamento.model.Cliente;

public class Registro {

    private Cliente cliente;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Registro(Cliente cliente){
        this.cliente = cliente;
        this.entrada = LocalDateTime.now();
    }

    public void registrarSaida(){
        this.saida = LocalDateTime.now();
    }

    public long calcularSegundos(){

        if(saida == null) return 0;

        Duration duracao = Duration.between(entrada, saida);

        return duracao.getSeconds();

    }

    public Cliente getCliente(){
        return cliente;
    }

    public String getHorarioEntrada(){
        return entrada.format(formato);
    }

    public String getHorarioSaida(){

        if(saida == null){
            return "Ainda no estacionamento";
        }

        return saida.format(formato);

    }

    public String toString(){
        return "{ " + cliente + " | Entrada: " + getHorarioEntrada() + " }";
    }

}