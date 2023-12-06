package entidades;

import java.io.Serializable;

/**
 *
 * @author Isaltina Pepete
 */
public class Pessoa implements Serializable {

    protected int codigo;
    protected String nome, apelido, nacionalidade;
    protected String email, dataDeNascimento, telefoneSecundario, telefonePrimario, endereco;

    public Pessoa(int codigo, String nome, String apelido, String nacionalidade, String email, 
            String dataDeNascimento, String telefonePrimario, String telefoneSecundario, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.apelido = apelido;
        this.nacionalidade = nacionalidade;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.telefonePrimario = telefonePrimario;
        this.telefoneSecundario = telefoneSecundario;
        this.endereco = endereco;
    }

    

    public String getEndereco() {
        return endereco;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public String getTelefonePrimario() {
        return telefonePrimario;
    }

    public String getEmail() {
        return email;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getApelido() {
        return apelido;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return   codigo + ";" + nome + ";" + apelido + ";" + nacionalidade + ";" + email + ";" + dataDeNascimento + ";" + telefoneSecundario + ";" + telefonePrimario + ";" + endereco ;
    }

    
}
