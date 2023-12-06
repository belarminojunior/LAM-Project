package entidades;

//import java.util.Date;


public class Trabalhador extends Pessoa{

    private String cargo;
    private int codigoFilial;

    public Trabalhador(int codigo, String nome, String apelido, String nacionalidade, String email, String dataDeNascimento,
            String telefonePrimario, String telefoneSecundario, String endereco, String cargo, int codigoFilial) {
        super(codigo, nome, apelido, nacionalidade, email, dataDeNascimento, telefonePrimario, telefoneSecundario, endereco);
        this.cargo = cargo;
        this.codigoFilial = codigoFilial;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return  super.toString()+ ";"+cargo + ";" + codigoFilial ;
    }

    
}
