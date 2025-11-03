package model;

public class Ingresso {
    protected int codigo;
    protected double preco;
    protected String status;

    public Ingresso(double preco){
        if(preco <= 0){
            System.err.println("O preço do ingresso não pode ser negativo.");
            this.preco = 0;
        }else{
            this.preco = preco;
            this.status = "disponivel";
        }
    }

    public Ingresso() {
    }

    public int getCodigo(){
        return codigo;
    }

    public String getStatus() {
        return status;
    }

    public double getPreco() {
        return preco;
    }

    public void vender(){
        if(getStatus().equals("indisponivel")){
            System.out.println("Este ingresso já foi vendido!");
            return;
        }else{
            this.status = "indisponivel";
            System.out.println("Ingresso vendido com sucesso!");
        }
    }

    @Override
    public String toString(){
        System.out.println("===Ingresso Pista===");
        return "Código: " + getCodigo() + "\nPreço: " + getPreco() + "\nStatus: " + getStatus();
    }
}
