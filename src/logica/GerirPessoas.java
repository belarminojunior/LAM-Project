package logica;

import ferramentas.Ficheiros;
import entidades.*;
import ferramentas.Table;
import ferramentas.Validacao;

import java.util.Vector;

public class GerirPessoas {

    private Vector<Pessoa> pessoas;
    
    private static GerirPessoas unicaInstancia = new GerirPessoas();
    
    private GerirPessoas() {
        pessoas = (Vector<Pessoa>) Ficheiros.lerObjecto("FICHEIROS/Objectos/ClientesTrabalhadores.dat");
        pessoas = (pessoas == null) ? new Vector<>() : pessoas;
    }
    
    public static GerirPessoas getUnicaInstancia() {
        return unicaInstancia;
    }
    
    public void guardarDados() {
        pessoas.trimToSize();
        Ficheiros.gravarObjecto(pessoas, "FICHEIROS/Objectos/ClientesTrabalhadores.dat");
    }
    
    private int gerarCodigoAutomatico(String nomeFich) {
        return Integer.parseInt(Ficheiros.lerFicheiroTxt(nomeFich)) + 1;
    }
    
    // Cadastro
    public void cadastrarCliente() {
        System.out.println("\nInsira os seus dados: ");
        int codigo = gerarCodigoAutomatico("FICHEIROS/Textos/Codigos/codigoCliente.txt");
        pessoas.add(new Cliente(
                codigo, //codigo
                Validacao.validarString("Nome: ", (byte) 4, (byte) 18), //nome
                Validacao.validarString("Apelido: ", (byte) 4, (byte) 15), //apelido
                Validacao.validarString("Nacionalidade: ", (byte) 4, (byte) 15), //nacionalidade
                Validacao.validarEmail("Email: "),//email
                Validacao.validarString("Data de Nascimento: ", (byte) 7, (byte) 9), //dataNasc
                Validacao.validarString("Telefone1: ", (byte) 7, (byte) 9), //telPrimario
                Validacao.validarString("Telefone2: ", (byte) 7, (byte) 9), //telSecundario
                Validacao.validarString("Endereco: ", (byte) 4, (byte) 15), //endereco
                Validacao.validarByte("Nr de Bebes: ", (byte) 0, (byte) 4)//numBebes
        ));
        
        Ficheiros.escreverFicheiroTxt("FICHEIROS/Textos/Codigos/codigoCliente.txt", codigo + "", false);
        System.out.println("\nCadastro Efectuado Com Sucesso!");
        System.out.println("O seu código é: " + codigo);
    }

    public void cadastrarTrabalhador(int codigoMaxFilial) {
        int codigo = gerarCodigoAutomatico("FICHEIROS/Textos/Codigos/codigoTrabalhador.txt");
        int codfilial = Validacao.validarInt("\nCódigo de Filial: ", 1000, codigoMaxFilial);
        
        System.out.println("\nInsira os seus dados: ");
        pessoas.add(new Trabalhador(
                codigo, //codigo
                Validacao.validarString("Nome: ", (byte) 4, (byte) 18), //nome
                Validacao.validarString("Apelido: ", (byte) 4, (byte) 15), //apelido
                Validacao.validarString("Nacionalidade: ", (byte) 4, (byte) 15), //nacionalidade
                Validacao.validarEmail("Email: "),//email
                Validacao.validarString("Data de Nascimento: ", (byte) 7, (byte) 9), //dataNasc
                Validacao.validarString("Telefone1: ", (byte) 7, (byte) 9), //telPrimario
                Validacao.validarString("Telefone2: ", (byte) 7, (byte) 9), //telSecundario
                Validacao.validarString("Endereco: ", (byte) 4, (byte) 15), //endereco

                Validacao.validarStringAlternativo("Cargo: ", new String[]{
                "Piloto", "Co-Piloto", "Aeromoca", "Seguranca", "\n", "Servente", "Gerente", "Recepcionista",
                "Engenheiro", "Mecanico"}), //cargo
                
                codfilial // codigo de filial
        ));
        
        Ficheiros.escreverFicheiroTxt("FICHEIROS/Textos/Codigos/codigoTrabalhador.txt", codigo + "", false);
        System.out.println("\nCadastro Efectuado Com Sucesso!");
        System.out.println("O seu código é: " + codigo);
    }

    // Visualizacao
    public void imprimirClientes() {
        Table tabela = new Table("PASSAGEIROS");
        tabela.setHeaders("CÓDIGO", "NOME", "APELIDO", "NACIONALIDADE", "EMAIL", "NASC.",
                "TEL1", "TEL2", "ENDEREÇO", "BAGAGEM(KG)", "BEBÉS");
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.elementAt(i) instanceof Cliente) {
                tabela.addRow(pessoas.get(i).toString().split(";"));
            }
        }
        tabela.print();
    }

    public void imprimirTrabalhadores() {

        Table tabela = new Table("TRABALHADORES");
        tabela.setHeaders("CÓDIGO", "NOME", "APELIDO", "NACIONALIDADE", "EMAIL", "NASC.",
                "TEL1", "TEL2", "ENDEREÇO", "CARGO", "CÓDIGO FILIAL");
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.elementAt(i) instanceof Trabalhador) {
                tabela.addRow(pessoas.elementAt(i).toString().split(";"));
            }
        }
        tabela.print();
    }
    
    // Remocao
    public void apagarPessoa(String tipo, int codigoPessoa) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.elementAt(i) instanceof Cliente && tipo.equalsIgnoreCase("Cliente")) {
                if (codigoPessoa == pessoas.get(i).getCodigo()) {
                    pessoas.remove(i);
                    System.out.println("CLIENTE REMOVIDO COM SUCESSO !");
                    return;
                }
            } else if (pessoas.elementAt(i) instanceof Trabalhador && tipo.equalsIgnoreCase("Trabalhador")) {
                if (pessoas.get(i).getCodigo() == codigoPessoa) {
                    pessoas.remove(i);
                    System.out.println("TRABALHADOR REMOVIDO COM SUCESSO !");
                    return;
                }
            }
        }
        System.out.println("PESSOA NÃO ENCONTRADA !");
    }

    // Consultas e Verificacoes
    public boolean existeCliente(int codigo) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i) instanceof Cliente && pessoas.get(i).getCodigo() == codigo)
                return true;
        }
        return false;
    }
    
    // Getters
    public Vector<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public Cliente getCliente(int codigo) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i) instanceof Cliente && pessoas.get(i).getCodigo() == codigo)
                return (Cliente) pessoas.get(i);
        }
        return null;
    }
}
