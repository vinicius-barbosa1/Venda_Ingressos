package controller;

import model.Ingresso;
import model.IngressosCamarote;
import model.Show;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControleShows {
    private List<Show> listaShow;

    public ControleShows(){
        this.listaShow = new ArrayList<>();
    }

    private Show buscarShowPorNome(String nomeShow){
        for(Show show : this.listaShow){
            if(show.getNome().equals(nomeShow)){
                return show;
            }
        }
        return null;
    }

    private Show buscarShowPorNomeEData(String nomeShow, LocalDate data){

        for(Show show : this.listaShow){
            if(show.getNome().equals(nomeShow) /* && show.getData().equals(data)*/){
                return show;
            }
        }
        return null;
    }




    public void cadastrarShow(String nome, String data, String local){
        Show show = new Show(nome, data, local);
        if(show.getNome() == null || show.getData() == null || show.getLocal() == null){
            System.out.println("Impossível criar um Show sem algum parâmetro!");
        }else{
            System.out.println("Show criado com sucesso!");
            listaShow.add(show);
        }
    }



    public void criarLoteIngressos(String nomeShow, String tipoIngresso, int
            quantidade, double preco, String localizacaoCamarote){

        Show show = new Show(nomeShow);

        if(preco<=0){
            System.err.println("O ingresso tem que ter um preço positivo!");
            return;
        }
        String tipo = tipoIngresso.toLowerCase();
        if(tipo.equals("pista") || tipo.equals("camarote")){
            System.err.println("O ingresso tem que ser do tipo: pista ou camarote!");
            return;
        }
        Show showEncontrado = buscarShowPorNome(nomeShow);
        if(showEncontrado == null){
            System.err.println("ERRO: O show '" + nomeShow + "' não foi encontrado");
            return;
        }

        for(int i = 0; i < quantidade; i++){
            Ingresso novoIngresso = null;

            if(tipo.equals("camarote")){
                novoIngresso = new IngressosCamarote(preco, localizacaoCamarote);
            }else{
                novoIngresso = new Ingresso(preco);
            }
            showEncontrado.adicionarIngresso(novoIngresso);
        }
        System.out.println("Sucesso! " + quantidade + " ingressos do tipo: " + tipoIngresso +
                " criados e adicionados ao show: " + nomeShow);
    }

    public void venderIngresso(String nomeShow, int codigoIngresso){
        Show showEncontrado = buscarShowPorNome(nomeShow);

        if(showEncontrado == null){
            System.err.println("ERRO: O show '" + nomeShow + "' não foi encontrado");
            // return;
        }else{
            for(Ingresso ingresso : showEncontrado.listaIngressos){
                if(ingresso.getCodigo() == codigoIngresso){
                    ingresso.vender();
                }else{
                    System.err.println("Ingresso não encontrado!");
                }
            }
        }
    }

    public void listarShows(){
        System.out.println("===Informações dos Shows===");
        for (Show show : listaShow){
            System.out.println("Nome: " + show.getNome());
            System.out.println("Data: " + show.getData());
            System.out.println("Local: " + show.getLocal());
            System.out.println("Quantidade de Ingressos: " + show.getQtd_ingresso());
        }
    }

    public void listarIngressos(String nomeShow, String status){
        Show showEncontrado = buscarShowPorNome(nomeShow);
        Ingresso ingresso = new Ingresso();
        if(showEncontrado == null) {
            System.err.println("ERRO: O show '" + nomeShow + "' não foi encontrado");
        }

        if(!ingresso.getStatus().equals("vendido") || !ingresso.getStatus().equals("disponivel")){
            System.err.println("ERRO: erro inesperado no tipo de status do ingresso!");
        }else if(showEncontrado != null){
            if(ingresso.getStatus().equals("disponivel")){
                showEncontrado.listarIngressosDisponiveis();
            }
            if(ingresso.getStatus().equals("vendido")){
                showEncontrado.listarIngressosVendidos();
            }
        }

    }

    public void gerarRelatorioFinanceiro(String nomeShow, LocalDate data){
        Show showEncontrado  = buscarShowPorNomeEData(nomeShow, data);
        if(showEncontrado == null) {
            System.err.println("ERRO: O show '" + nomeShow + "' não foi encontrado");
        }else{
            System.out.println("===Informações do Show===");
            System.out.println("Nome: " + showEncontrado.getNome());
            System.out.println("Data: " + showEncontrado.getData());
            System.out.println("Local: " + showEncontrado.getLocal());
            System.out.println("Total Arrecadado: " + showEncontrado.calcularTotalArrecadado());

        }
    }



}
