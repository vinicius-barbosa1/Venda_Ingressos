package controller;

import model.Ingresso;
import model.IngressosCamarote;
import model.Show;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControleShows {
    public List<Show> listaShow;

    public ControleShows(){
        listaShow = new ArrayList<>();
    }

    private Show buscarShowPorNome(String nomeShow){
        for(Show show : listaShow){
            if(show.getNome().equalsIgnoreCase(nomeShow)){
                return show;
            }
        }
        return null;
    }

    private Show buscarShowPorNomeEData(String nomeShow, LocalDate data){

        for(Show show : listaShow){
            if(show.getNome().equalsIgnoreCase(nomeShow) && show.getDataAsLocalDate().isEqual(data)){
                return show;
            }
        }
        return null;
    }


    public void cadastrarShow(String nome, String data, String local){
        Show show = new Show(nome, data, local);

        if(nome == null || nome.trim().isEmpty() || data == null || data.trim().isEmpty() || local == null || local.trim().isEmpty()){
            System.out.println("Impossível criar um Show sem algum parâmetro!");
        } else if (show.StringParaData(data) == null) {
            System.out.println("Impossível cadastrar show devido a data inválida.");
        }
        else{
            if (buscarShowPorNomeEData(nome, show.StringParaData(data)) != null) {
                System.err.println("ERRO: Já existe um show com este nome e data cadastrado.");
                return;
            }
            System.out.println("Show criado com sucesso!");
            listaShow.add(show);
        }
    }


    public void criarLoteIngressos(String nomeShow, String tipoIngresso, int
            quantidade, double preco, String localizacaoCamarote){

        if(preco <= 0){
            System.err.println("O ingresso tem que ter um preço positivo!");
            return;
        }
        String tipo = tipoIngresso.toLowerCase();
        if(!tipo.equals("pista") && !tipo.equals("camarote")){
            System.err.println("O ingresso tem que ser do tipo: pista ou camarote!");
            return;
        }
        Show showEncontrado = buscarShowPorNome(nomeShow);
        if(showEncontrado == null){
            System.err.println("ERRO: O show '" + nomeShow + "' não foi encontrado");
            return;
        }

        for(int i = 0; i < quantidade; i++){
            if(tipo.equals("camarote")){
                IngressosCamarote ingressosCamarote = new IngressosCamarote(preco, localizacaoCamarote);
                showEncontrado.adicionarIngresso(ingressosCamarote);
            }else{
                Ingresso ingresso = new Ingresso(preco);
                showEncontrado.adicionarIngresso(ingresso);
            }
        }
        System.out.println("Sucesso! " + quantidade + " ingressos do tipo: " + tipoIngresso +
                " criados e adicionados ao show: " + nomeShow);
    }

    public void venderIngresso(String nomeShow, int codigoIngresso){
        Show showEncontrado = buscarShowPorNome(nomeShow);

        if(showEncontrado == null){
            System.err.println("ERRO: O show '" + nomeShow + "' não foi encontrado");
            return;
        }

        boolean vendido = false;

        for(Ingresso ingresso : showEncontrado.listaIngressos){
            if(ingresso.getCodigo() == codigoIngresso){
                ingresso.vender();
                vendido = true;
                break;
            }
        }

        if (!vendido) {
            System.err.println("ERRO: Ingresso com código " + codigoIngresso + " não encontrado para o show " + nomeShow + ".");
        }
    }

    public void listarShows(){
        System.out.println("=== Lista de Shows Cadastrados ===");
        if (listaShow.isEmpty()) {
            System.out.println("Nenhum show cadastrado no momento.");
            return;
        }
        for (Show show : listaShow){
            System.out.println("----------------------------------------");
            System.out.println("Nome: " + show.getNome());
            System.out.println("Data: " + show.getData());
            System.out.println("Local: " + show.getLocal());
            System.out.println("Quantidade Total de Ingressos: " + show.getQtd_ingresso());
        }
        System.out.println("----------------------------------------");
    }

    public void listarIngressos(String nomeShow, String status){
        Show showEncontrado = buscarShowPorNome(nomeShow);

        if(showEncontrado == null) {
            System.err.println("ERRO: O show '" + nomeShow + "' não foi encontrado");
            return;
        }

        String statusLower = status.toLowerCase();

        if(!statusLower.equals("vendido") && !statusLower.equals("disponivel")){
            System.err.println("ERRO: Status inválido. Use 'vendido' ou 'disponivel'.");
        } else {
            if(statusLower.equals("disponivel")){
                showEncontrado.listarIngressosDisponiveis();
            }
            if(statusLower.equals("vendido")){
                showEncontrado.listarIngressosVendidos();
            }
        }
    }

    public void gerarRelatorioFinanceiro(String nomeShow, LocalDate data){
        Show showEncontrado  = buscarShowPorNomeEData(nomeShow, data);

        if(showEncontrado == null) {
            System.err.println("ERRO: O show '" + nomeShow + "' na data " + showEncontrado.DataParaString(data) + " não foi encontrado");
        }else{
            double totalArrecadado = showEncontrado.calcularTotalArrecadado();
            System.out.println("=== Relatório Financeiro do Show ===");
            System.out.println("Nome: " + showEncontrado.getNome());
            System.out.println("Data: " + showEncontrado.getData());
            System.out.println("Local: " + showEncontrado.getLocal());
            System.out.println("Total Arrecadado: R$" + String.format("%.2f", totalArrecadado));
        }
    }
}