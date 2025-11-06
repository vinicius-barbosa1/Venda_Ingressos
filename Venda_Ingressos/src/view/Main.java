package view;
import controller.ControleShows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ControleShows listaControle = new ControleShows();
        String nome;
        String data;

        int opcao;

        do{
            System.out.println("\n===== MENU PRINCIPAL=====");
            System.out.println("1. Cadastrar Show");
            System.out.println("2. Criar Lote de Ingressos");
            System.out.println("3. Vender Ingresso");
            System.out.println("4. Listar ingressos disponíveis (para um show específico)");
            System.out.println("5. Listar ingressos vendidos (para um show específico)");
            System.out.println("6. Gerar relatório financeiro por show");
            System.out.println("7. Listar todos os shows");
            System.out.println("0. Sair");
            System.out.println("========================================");
            System.out.print("Digite sua opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("---Criação de Show---");
                    System.out.print("Nome do Show: ");
                    nome = scanner.nextLine();

                    System.out.print("Local: ");
                    String local = scanner.nextLine();

                    System.out.print("Data (dd/MM/yyyy): ");
                    data = scanner.nextLine();

                    listaControle.cadastrarShow(nome, data, local);

                    teclaEnter();
                    break;

                case 2:
                    System.out.println("---Criar Lote de Ingressos---");
                    System.out.print("Digite o nome do Show que deseja criar o lote: ");
                    nome = scanner.nextLine();

                    System.out.print("Tipo do ingresso (pista ou camarote): ");
                    String tipo = scanner.nextLine();

                    System.out.print("Quantidade de ingressos: ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Valor dos ingressos: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Localização do Camarote (se for Camarote, senão digite -): ");
                    String localizacao = scanner.nextLine();

                    listaControle.criarLoteIngressos(nome, tipo, qtd, preco, localizacao);

                    teclaEnter();
                    break;
                case 3:
                    System.out.print("Digite o nome do Show: ");
                    nome = scanner.nextLine();

                    System.out.print("Digite o código do ingresso: \n");
                    int cod = scanner.nextInt();
                    scanner.nextLine();

                    listaControle.venderIngresso(nome, cod);
                    teclaEnter();
                    break;
                case 4:
                    System.out.print("Digite o nome do Show: ");
                    nome = scanner.nextLine();

                    listaControle.listarIngressos(nome, "disponivel");
                    teclaEnter();
                    break;
                case 5:
                    System.out.print("Digite o nome do Show: ");
                    nome = scanner.nextLine();

                    listaControle.listarIngressos(nome, "vendido");
                    teclaEnter();
                    break;
                case 6:
                    System.out.print("Digite o nome do Show: ");
                    nome = scanner.nextLine();

                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    data = scanner.nextLine();

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataShow = LocalDate.parse(data, formato);
                    listaControle.gerarRelatorioFinanceiro(nome, dataShow);

                    teclaEnter();
                    break;
                case 7:
                    listaControle.listarShows();
                    teclaEnter();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    teclaEnter();
            }
        }while(opcao != 0);

    }

    public static void teclaEnter() {
        System.out.print("\nTecle ENTER para continuar...");
        scanner.nextLine();
    }
}