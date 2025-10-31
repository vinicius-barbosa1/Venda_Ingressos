package model;

public class IngressosCamarote extends Ingresso{
    private String localizacao;

    public IngressosCamarote(double preco, String localizacao){
        if(preco <= 0){
            System.err.println("O preço do ingresso não pode ser negativo.");
            this.preco = 0;
        }else{
            this.preco = preco;
            this.status = "disponivel";
            this.localizacao = localizacao;
        }
       // super(preco);
    }

    public String getLocalizacao(){
        return localizacao;
    }

    public void vender(){
        super.vender();
    }

    @Override
    public String toString(){
        System.out.println("===model.Ingresso Camarote===");
        return super.toString() + "\n Localização: " + getLocalizacao();
    }
}
