package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Show {
    public ArrayList<Ingresso> listaIngressos = new ArrayList<>();
    protected String nome;
    protected String data;
    protected String local;
    protected int qtd_ingresso = 0;

    public Show(String nome){
        this.nome = nome;
    }

    public Show(String nome, String data, String local){
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public int getQtd_ingresso(){
        return qtd_ingresso;
    }

    public void adicionarIngresso(Ingresso ingresso){
        if(ingresso != null){
            listaIngressos.add(ingresso);
            System.out.println("Ingresso adicionado com sucesso.");
        }else{
            System.err.println("ERRO: Não foi possível cadastrar o ingresso.");
        }
    }

    public void listarIngressosDisponiveis(){
        for(Ingresso ingresso : listaIngressos){
            if(ingresso.getStatus().equals("disponivel")){
                System.out.println(ingresso.toString());
                qtd_ingresso++;
            }
        }
    }

    public void listarIngressosVendidos(){
        for(Ingresso ingresso : listaIngressos){
            if(ingresso.getStatus().equals("vendido")){
                System.out.println(ingresso.toString());
            }
        }
    }

    public double calcularTotalArrecadado(){
        double somaTotal = 0;
        for (Ingresso ingresso : listaIngressos) {
            if (ingresso.getStatus().equals("vendido")) {
                somaTotal += ingresso.getPreco();
            }
        }
        System.out.print("O total arrecadado foi: R$");
        return somaTotal;
    }

    @Override
    public String toString(){
        System.out.println("===Detalhes do Show===");
        return "Nome: " + getNome() + "\nData: " + StringParaData(data) + "\nLocal: " + getLocal() + "\nTotal de Ingressos: " + qtd_ingresso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Show outroShow = (Show) obj;

        return this.nome.equals(outroShow.nome) &&
                this.data.equals(outroShow.data) &&
                this.local.equals(outroShow.local);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, data, local);
    }

    public LocalDate StringParaData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formato);
    }

    public String DataParaString(LocalDate data){
        if(data == null){
            return null;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formato);
    }
}
