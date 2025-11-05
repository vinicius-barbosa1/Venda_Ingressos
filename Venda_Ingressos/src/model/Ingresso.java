package model;

public class Ingresso {
    private static int proximoCodigo = 1;

    protected int codigo;
    protected double preco;
    protected String status;

    public Ingresso(double preco){
        this.codigo = proximoCodigo++;

        if(preco <= 0){
            System.err.println("O preço do ingresso não pode ser negativo. Preço definido para 0.");
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
        if(getStatus().equals("vendido")){
            System.out.println("Este ingresso já foi vendido!");
            return;
        }else{
            this.status = "vendido";
            System.out.println("Ingresso de Código " + this.codigo + " vendido com sucesso!");
        }
    }

    @Override
    public String toString(){
        return "Código: " + getCodigo() + " | Preço: R$" + String.format("%.2f", getPreco()) + " | Tipo: Pista | Status: " + getStatus();
    }
}