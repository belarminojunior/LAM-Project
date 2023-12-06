package entidades;

import java.io.Serializable;

public class Rota implements Serializable{

    private int codigo;
    private String aeroPartida, aeroChegada;
    private float distancia;

    public Rota(int codigo, String aeroPartida, String aeroChegada, float distancia) {
        this.codigo = codigo;
        this.aeroPartida = aeroPartida;
        this.aeroChegada = aeroChegada;
        this.distancia = distancia;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getAeroPartida() {
        return aeroPartida;
    }

    public String getAeroChegada() {
        return aeroChegada;
    }

    public float getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return  codigo + ";" + aeroPartida + ";" + aeroChegada + ";" + distancia;
    }


   
    
}
