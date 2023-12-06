package entidades;

import java.io.Serializable;

public class Filial implements Serializable{

    private int codigo;
    private String nome;
    private String endereco;
    private String provincia;

    public Filial(int codigo, String nome, String endereco, String provincia) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;       
        this.provincia = provincia;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getProvincia() {
        return provincia;
    }

    @Override
    public String toString() {
        return  codigo + ";" + nome + ";" + endereco + ";" + provincia;
    }
    
   
}
