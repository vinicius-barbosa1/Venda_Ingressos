package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Show {
    ArrayList<Ingresso> listaIngressos = new ArrayList<>();
    protected String nome;
    protected String data;
    protected String local;

    public Show(String nome, String data, String local){
        this.nome = nome;
        this.data = data;
        this.local = local;
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
            }
        }
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
