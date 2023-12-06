package entidades;

import java.io.Serializable;

public class Voo implements Serializable{

    private int codigo;
    private String horaPartida;
    private String horaChegada;
    private String data;
    private int codigoRota;
    private int codigoAviao;
    private float precoBase;

    public Voo(int codigo, String horaPartida, String horaChegada, String data, int codigoRota, int codigoAviao, float precoBase) {
        this.codigo = codigo;
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
        this.data = data;
        this.codigoRota = codigoRota;
        this.codigoAviao = codigoAviao;
        this.precoBase = precoBase;
    }

    public float getPrecoBase() {
        return precoBase;
    }
   
    public String getHoraPartida() {
        return horaPartida;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public String getData() {
        return data;
    }

    public int getCodigoRota() {
        return codigoRota;
    }

    public int getCodigoAviao() {
        return codigoAviao;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return  codigo + ";" + horaPartida + ";" + horaChegada + ";" + data + ";" + precoBase;
    }

    
}
