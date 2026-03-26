package com.sistema.estacionamento.view;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import com.sistema.estacionamento.model.Cliente;
import com.sistema.estacionamento.control.Registro;

public class Ui_interface {

    public static void gerarDadosTeste(Map<String, Registro> estacionamento, int quantidade) {

        String[] nomes = {
            "Carlos", "Ana", "Marcos", "Joao", "Pedro",
            "Lucas", "Fernanda", "Julia", "Rafael", "Bruno"
        };

        for (int i = 0; i < quantidade; i++) {

            String nome = nomes[(int)(Math.random() * nomes.length)];

            String placa =
                "" + (char)(65 + Math.random()*26) +
                (char)(65 + Math.random()*26) +
                (char)(65 + Math.random()*26) +
                "-" +
                (int)(1000 + Math.random()*9000);

            String cpf = "" + (long)(10000000000L + Math.random()*90000000000L);

            Cliente cliente = new Cliente(nome, placa, cpf);
            Registro registro = new Registro(cliente);

            estacionamento.put(placa, registro);
        }

        System.out.println(quantidade + " veiculos de teste inseridos.");
    }

    public static void buscarPorCpf(Map<String, Registro> estacionamento, String cpf){

        boolean encontrado = false;

        for(Registro r : estacionamento.values()){

            if(r.getCliente().getCpf().equals(cpf)){
                System.out.println("Veiculo encontrado:");
                System.out.println(r);
                encontrado = true;
            }

        }

        if(!encontrado){
            System.out.println("Nenhum veiculo encontrado com esse CPF.");
        }

    }

    public static void main() {

        Scanner scanner = new Scanner(System.in);
        Map<String, Registro> estacionamento = new HashMap<>();

        double precoPorSegundo = 0.05;
        boolean cont = true;

        while (cont) {

            System.out.println("\n====== Sistema de Estacionamento ======");
            System.out.println("1 - Registrar entrada de veiculo");
            System.out.println("2 - Registrar saida de veiculo");
            System.out.println("3 - Listar veiculos");
            System.out.println("4 - Buscar por CPF");
            System.out.println("5 - Gerar dados de teste");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");

            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {

                case 1:

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Placa: ");
                    String placa = scanner.nextLine().toUpperCase();

                    if (estacionamento.containsKey(placa)) {
                        System.out.println("Esse veiculo ja esta no estacionamento.");
                        break;
                    }

                    Cliente cliente = new Cliente(nome, placa, cpf);
                    Registro registro = new Registro(cliente);

                    estacionamento.put(placa, registro);

                    System.out.println("Entrada registrada!");
                    System.out.println("Horario: " + registro.getHorarioEntrada());

                    break;

                case 2:

                    System.out.print("Placa: ");
                    String placaSaida = scanner.nextLine().toUpperCase();

                    if (!estacionamento.containsKey(placaSaida)) {
                        System.out.println("Veiculo nao encontrado.");
                        break;
                    }

                    Registro reg = estacionamento.get(placaSaida);
                    reg.registrarSaida();

                    long segundos = reg.calcularSegundos();
                    double valor = segundos * precoPorSegundo;

                    System.out.println("\n--- Comprovante ---");
                    System.out.println("Cliente: " + reg.getCliente().getNome());
                    System.out.println("CPF: " + reg.getCliente().getCpf());
                    System.out.println("Placa: " + reg.getCliente().getPlaca());
                    System.out.println("Entrada: " + reg.getHorarioEntrada());
                    System.out.println("Saida: " + reg.getHorarioSaida());
                    System.out.println("Tempo: " + segundos + " segundos");
                    System.out.printf("Valor: R$ %.2f\n", valor);

                    estacionamento.remove(placaSaida);

                    break;

                case 3:

                    if(estacionamento.isEmpty()){
                        System.out.println("Nenhum veiculo.");
                    }else{

                        System.out.println("\n--- Veiculos no estacionamento ---");

                        for(Registro r : estacionamento.values()){
                            System.out.println(r);
                        }

                    }

                    break;

                case 4:

                    System.out.print("Digite CPF: ");
                    String cpfBusca = scanner.nextLine();

                    buscarPorCpf(estacionamento, cpfBusca);

                    break;

                case 5:

                    System.out.print("Quantidade: ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();

                    gerarDadosTeste(estacionamento, qtd);

                    break;

                case 6:

                    cont = false;
                    System.out.println("Encerrando sistema...");

                    break;

                default:
                    System.out.println("Opcao invalida.");

            }

        }

        scanner.close();

    }

}
