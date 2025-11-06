package model;

public class IngressosCamarote extends Ingresso{
    private String localizacao;

    public IngressosCamarote(double preco, String localizacao){
        super(preco);
        this.localizacao = localizacao;
    }

    public String getLocalizacao(){
        return localizacao;
    }

    @Override
    public void vender(){
        System.out.print("Camarote ");
        super.vender();
    }

    @Override
    public String toString(){
        return "Código: " + getCodigo() + " | Preço: R$" + String.format("%.2f", getPreco()) +
                " | Tipo: Camarote | Localização: " + getLocalizacao() + " | Status: " + getStatus();
    }
}