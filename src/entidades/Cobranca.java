package entidades;

import java.io.Serializable;

/**
 *
 * @author Alexandre
 */
public class Cobranca implements Serializable{

    private float valor;
    private float desconto;
    private int codigo;
    private String titulo;
    private String descricao;

    public Cobranca(float valor, float desconto, int codigo, String titulo, String descricao) {
        this.valor = valor;
        this.desconto = desconto;
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public float getDesconto() {
        return desconto;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
