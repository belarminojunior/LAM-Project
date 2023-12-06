package logica;

import ferramentas.Ficheiros;
import ferramentas.*;
import entidades.*;
import java.util.Vector;

/**
 *
 * @author Isaltina Pepete
 */
public class GerirAdministracao {

    private Vector<Rota> rotas;
    private Vector<Aviao> avioes;
    private Vector<Filial> filiais;
    private Vector<Voo> voos;
    private Vector<Classe> classes;
    
    // Configuracoes
    private static GerirAdministracao unicaInstancia = new GerirAdministracao();

    private GerirAdministracao() {
        inicializarVectores();
    }
    
    public static GerirAdministracao getUnicaInstancia() {
        return unicaInstancia;
    }

    private void inicializarVectores() {
        rotas = (Vector<Rota>) Ficheiros.lerObjecto("FICHEIROS/Objectos/rotas.dat");
        filiais = (Vector<Filial>) Ficheiros.lerObjecto("FICHEIROS/Objectos/filiais.dat");
        avioes = (Vector<Aviao>) Ficheiros.lerObjecto("FICHEIROS/Objectos/avioes.dat");
        voos = (Vector<Voo>) Ficheiros.lerObjecto("FICHEIROS/Objectos/voos.dat");
        classes = (Vector<Classe>) Ficheiros.lerObjecto("FICHEIROS/Objectos/classes.dat");
        
        rotas = (rotas == null) ? rotas = new Vector<>() : rotas; 
        filiais = (filiais == null) ? filiais = new Vector<>() : filiais; 
        avioes = (avioes == null) ? avioes = new Vector<>() : avioes; 
        voos = (voos == null) ? voos = new Vector<>() : voos; 
        classes = (classes == null) ? classes = new Vector<>() : classes;
    }

    public void gravarVectores() {
        rotas.trimToSize(); filiais.trimToSize(); avioes.trimToSize();
        voos.trimToSize(); classes.trimToSize();
        Ficheiros.gravarObjecto(rotas, "FICHEIROS/Objectos/rotas.dat");
        Ficheiros.gravarObjecto(filiais, "FICHEIROS/Objectos/filiais.dat");
        Ficheiros.gravarObjecto(avioes, "FICHEIROS/Objectos/avioes.dat");
        Ficheiros.gravarObjecto(voos, "FICHEIROS/Objectos/voos.dat");
        Ficheiros.gravarObjecto(classes, "FICHEIROS/Objectos/classes.dat");
    }
    
    
    // Cadastros
    public void cadastrarVoo() {

        System.out.println("++++CADASTRAR VOO++++");
        
        String horaP = Validacao.validarString("Hora De Partida: ", 5, 8);
        String horaC = Validacao.validarString("Hora De Chegada: ", 5, 8);
        String data = Validacao.validarString("Data: ", 8, 10);
        verRotas();
        int codRota = Validacao.validarInt("Codigo Da Rota: ", 1000, rotas.lastElement().getCodigo());
        verAviao();
        int codAviao = Validacao.validarInt("Codigo Do Aviao: ", 1000, avioes.lastElement().getCodigo());
        float precoBase = Validacao.validarFloat("Preço Base: ", 1000, 100000);
        
        voos.add(new Voo(
                (voos.size() > 0) ? (voos.lastElement().getCodigo() + 1) : 1000, //codigo
                horaP, horaC, data, codRota, codAviao, precoBase));
        
        System.out.println("\nCadastro Efectuado Com Sucesso!");
        System.out.println("O seu código é: " + voos.lastElement().getCodigo());
    }

    public void cadastrarRota() {

        System.out.println("Cadastrar Rota");
        rotas.add(new Rota(
                (rotas.size() > 0) ? (rotas.lastElement().getCodigo() + 1) : 1000,
                Validacao.validarString("Aeroporto De Partida: ", 4, 20),
                Validacao.validarString("Aeroporto De Chegada: ", 4, 20),
                Validacao.validarFloat("Distancia(km): ", 100, 3000)
        ));
        System.out.println("\nCadastro Efectuado Com Sucesso!");
        System.out.println("O seu código é: " + rotas.lastElement().getCodigo());
    }

    public void cadastrarFilial() {

        System.out.println("Cadastrar Filial");
        filiais.add(new Filial(
                (filiais.size() > 0) ? (filiais.lastElement().getCodigo() + 1) : 1000,
                Validacao.validarString("Nome: ", 4, 10),
                Validacao.validarString("Endereco: ", 4, 40),
                Validacao.validarString("Provincia: ", 4, 20)
        ));
        System.out.println("\nCadastro Efectuado Com Sucesso!");
        System.out.println("O seu código é: " + filiais.lastElement().getCodigo());
    }

    public void cadastrarAviao() {

        System.out.println("Cadastrar Aviao");
        
        String modelo = Validacao.validarString("Modelo: ", 2, 25);
        int capacidade = Validacao.validarInt("Assentos: ", 20, 300);
        verFiliais();
        int codFilial = Validacao.validarInt("Codigo Da Filial: ", 1000, getUltimaFilial().getCodigo());
        
        avioes.add(new Aviao(
                (avioes.size() > 0) ? (avioes.lastElement().getCodigo() + 1) : 1000,
                modelo, capacidade, codFilial ));
        
        System.out.println("\nCadastro Efectuado Com Sucesso!");
        System.out.println("O seu código é: " + avioes.lastElement().getCodigo());
    }

    public void cadastrarClasse() {
        System.out.println("Cadastrar Classe");
        classes.add(new Classe(
                (classes.size() > 0) ? (classes.lastElement().getCodigo() + 1) : 1000,
                Validacao.validarString("Nome: ", 1, 10),
                Validacao.validarString("Descrição: ", 2, 300),
                Validacao.validarFloat("Peso Limite: ", 10f, 50f)
        ));
        System.out.println("\nCadastro Efectuado Com Sucesso!");
        System.out.println("O seu código é: " + classes.lastElement().getCodigo());
    }
    
    // Visualizacao
    public void verRotas() {
        Table tabela = new Table("ROTAS");
        tabela.setHeaders("CÓDIGO", "A. PARTIDA", "A. CHEGADA", "DISTÂNCIA");
        for (int i = 0; i < rotas.size(); i++) {
            tabela.addRow(rotas.elementAt(i).toString().split(";"));
        }
        tabela.print();
    }

    public void verFiliais() {
        Table tabela = new Table("FILIAIS");
        tabela.setHeaders("CÓDIGO", "NOME", "ENDERECO", "PROVINCIA");
        for (int i = 0; i < filiais.size(); i++) {
            tabela.addRow(filiais.elementAt(i).toString().split(";"));
        }
        tabela.print();
    }

    public void verAviao() {
        Table tabela = new Table("AVIÕES");
        tabela.setHeaders("CÓDIGO", "MODELO", "CAPACIDADE", "CODIGO FILIAL");
        for (int i = 0; i < avioes.size(); i++) {
            tabela.addRow(avioes.elementAt(i).toString().split(";"));
        }
        tabela.print();
    }

    public void verVoo() {
        verVoo(voos);
    }
    
    public void verVoo(Vector<Voo> voosEspecificos) {
        Table tabela = new Table("VOOS");
        tabela.setHeaders("CÓDIGO", "H.PARTIDA", "H.CHEGADA", "DATA", "PREÇO BASE", "PARTIDA", "CHEGADA", "AVIAO", "ASSENTOS");

        for (int i = 0; i < voosEspecificos.size(); i++) {

            Rota rota = getRota(voosEspecificos.get(i).getCodigoRota());
            Aviao aviao = getAviao(voosEspecificos.get(i).getCodigoAviao());

            tabela.addRow((voosEspecificos.get(i).toString()
                    + ';' + rota.getAeroPartida() + ';' + rota.getAeroChegada()
                    + ';' + aviao.getModelo() + ';' + aviao.getCapacidade())
                    .split(";"));
        }
        tabela.print();
    }

    public void verClasse() {
        Table tabela = new Table("CLASSES");
        tabela.setHeaders("CÓDIGO", "NOME", "DESCRIÇÃO", "LIMITE BAGAGEM");
        for (int i = 0; i < classes.size(); i++) {
            tabela.addRow(classes.elementAt(i).toString().split(";"));
        }
        tabela.print();
    }
    
    // Pesquisa de voos - Reserva
    public void pesquisarVoo() {
        System.out.println("Pesquisa De Voos");
        
        String aeroP = Validacao.validarString("Aeroporto De Partida: ", 2, 20);
        String aeroC = Validacao.validarString("Aeroporto De Chegada: ", 2, 20);
        
        Vector<Voo> voosEncontrados = new Vector<>();
        for (Voo voo: voos) {
            
            // verificar a sua rota
            Rota rota = getRota(voo.getCodigoRota());
            
            if (rota.getAeroPartida().equalsIgnoreCase(aeroP)
                && rota.getAeroChegada().equalsIgnoreCase(aeroC))
                
                voosEncontrados.add(voo);
        }
        
        if (voosEncontrados.size() > 0){
            //imprimir
            verVoo(voosEncontrados);
        }else{
            System.out.println("Nenhum Voo Foi Encontrado.");
        }
    }
    
    // Getters Por Codigo
    public Rota getRota(int codigo) {
        for (int i = 0; i < rotas.size(); i++) {
            if (rotas.get(i).getCodigo() == codigo) {
                return rotas.get(i);
            }
        }
        return null;
    }

    public Aviao getAviao(int codigo) {
        for (int i = 0; i < avioes.size(); i++) {
            if (avioes.get(i).getCodigo() == codigo) {
                return avioes.get(i);
            }
        }
        return null;
    }
    
    public Classe getClasse(int codigo) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getCodigo() == codigo) {
                return classes.get(i);
            }
        }
        return null;
    }

    public Voo getVoo(int codigo) {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getCodigo() == codigo) {
                return voos.get(i);
            }
        }
        return null;
    }
    
    public Filial getFilial(int codigo) {
        for (int i = 0; i < filiais.size(); i++) {
            if (filiais.get(i).getCodigo() == codigo) {
                return filiais.get(i);
            }
        }
        return null;
    }
    
    public Filial getUltimaFilial(){
        return (filiais.size() > 0) ? filiais.lastElement() : null;
    }
    
    // Verificacoes e Consultas
    public boolean existeVoo(int codigo) {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public boolean existeClasse(int codigo) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
    
    public boolean podeCadastrarVoo(){
        return rotas.size() > 0 && avioes.size() > 0;
    }
    
    public boolean podeCadastrarAviao(){
        return filiais.size() > 0;
    }
}
