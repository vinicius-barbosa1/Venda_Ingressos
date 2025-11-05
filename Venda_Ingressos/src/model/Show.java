package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Show {
    public ArrayList<Ingresso> listaIngressos = new ArrayList<>();
    protected String nome;
    protected LocalDate data;
    protected String local;

    public Show(String nome){
        this.nome = nome;
    }

    public Show(String nome, String data, String local){
        this.nome = nome;
        this.data = LocalDate.parse(data);
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
        return listaIngressos.size();
    }

    public LocalDate getDataAsLocalDate() {
        return StringParaData(this.data);
    }

    public void adicionarIngresso(Ingresso ingresso){
        if(ingresso != null){
            listaIngressos.add(ingresso);
        }else{
            System.err.println("ERRO: Não foi possível cadastrar o ingresso.");
        }
    }

    public void listarIngressosDisponiveis(){
        boolean encontrado = false;
        System.out.println("---Ingressos Disponíveis---");
        for(Ingresso ingresso : listaIngressos){
            if(ingresso.getStatus().equals("disponivel")){
                System.out.println(ingresso.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum ingresso disponível para este show.");
        }
    }

    public void listarIngressosVendidos(){
        boolean encontrado = false;
        System.out.println("---Ingressos Vendidos---");
        for(Ingresso ingresso : listaIngressos){
            if(ingresso.getStatus().equals("vendido")){
                System.out.println(ingresso.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum ingresso vendido para este show.");
        }
    }

    public double calcularTotalArrecadado(){
        double somaTotal = 0;
        for (Ingresso ingresso : listaIngressos) {
            if (ingresso.getStatus().equals("vendido")) {
                somaTotal += ingresso.getPreco();
            }
        }
        return somaTotal;
    }

    @Override
    public String toString(){
        return "Nome: " + getNome() + " | Data: " + getData() + " | Local: " + getLocal() + " | Total de Ingressos: " + listaIngressos.size();
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

<<<<<<< HEAD
    public LocalDate StringParaData(String data){
        if (data == null || data.trim().isEmpty()) {
            return null;
        }
=======
    public LocalDate StringParaData(/*String data*/){
>>>>>>> 2ed72b317a7a24c888d9d0c6f50cc9a2f09201d4
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formato);
      //  return LocalDate.parse(data, formato);
    }

    public String DataParaString(LocalDate data){
        if(data == null){
            return null;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formato);
    }
}