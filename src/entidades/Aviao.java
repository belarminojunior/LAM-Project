package entidades;

import java.io.Serializable;

public class Aviao implements Serializable{

    private int codigo;
    private String modelo;
    private int capacidade;
    private int codigoFilial;

    public Aviao(int codigo, String modelo, int capacidade, int codigoFilial) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.capacidade = capacidade;
        this.codigoFilial = codigoFilial;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getcodigoFilial() {
        return codigoFilial;
    }

    @Override
    public String toString() {
        return codigo + ";" + modelo + ";" + capacidade + ";" + codigoFilial;
    }
    
    
}
