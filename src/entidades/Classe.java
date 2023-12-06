package entidades;

import java.io.Serializable;

public class Classe implements Serializable{

    private int codigo;
    private String nome;
    private String descricao;
    private float pesoLimite;

    public Classe(int codigo, String nome, String descricao, float pesoLimite) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.pesoLimite = pesoLimite;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getPesoLimite() {
        return pesoLimite;
    }

    public void setPesoLimite(float pesoLimite) {
        this.pesoLimite = pesoLimite;
    }

    @Override
    public String toString() {
        return  codigo + ";" + nome + ";" + descricao + ";" + pesoLimite;
    }
    
}
