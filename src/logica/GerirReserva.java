package logica;

import entidades.Classe;
import ferramentas.Validacao;

/**
 * @author Isaltina Pepete
 */
public class GerirReserva {
    
    private GerirPessoas gerirPessoa;
    private GerirAdministracao admin;
    
    private int codigoCliente;
    private int codigoVoo;
    private int codigoClasse;
    
    private boolean cadastrou;
    private boolean escolheuVoo;
    private boolean escolheuClasse;
    
    public GerirReserva() {
        gerirPessoa = GerirPessoas.getUnicaInstancia();
        admin = GerirAdministracao.getUnicaInstancia();
    }
    
    // Cadastro
    public void cadastrar() {
        gerirPessoa.cadastrarCliente();
        codigoCliente = gerirPessoa.getPessoas().lastElement().getCodigo();
        cadastrou = true;
    }
    
    public void procurarCliente() {
        int codigo = Validacao.validarInt("Código do Cliente: ", 1000, 9999);
        if (gerirPessoa.existeCliente(codigo)) {
            cadastrou = true;
            codigoCliente = codigo;
        } else {
            System.out.println("Cliente Não Encontrado! Por favor, Cadastre - se.");
        }
        
    }
    
    // Escolhas de Voo e Classe
    public void escolherVoo() {
        int codigo = Validacao.validarInt("Introduza o codigo do voo: ", 1000, 9999);
        if (admin.existeVoo(codigo)) {
            escolheuVoo = true;
            codigoVoo = codigo;
        } else {
            System.out.println("Codigo de Voo Não Encontrado!");
        }
    }
    
    public void escolherClasse() {
        int codigo = Validacao.validarInt("Introduza o codigo da classe: ", 1000, 9999);
        Classe classe = admin.getClasse(codigo);
        
        if (classe != null) {
            float bagagem = Validacao.validarFloat("Introduz A Sua Bagagem: ", 0f, 999f);
            
            if (bagagem <= classe.getPesoLimite()) {
                (gerirPessoa.getCliente(codigoCliente)).setPesoBagagem(bagagem);
                escolheuClasse = true;
                codigoClasse = codigo;
            } else {
                System.out.println("\nBagagem Excedeu o Limite Da Classe Escolhida.");
                System.out.println("Reduza a Bagagem ou Escolha Uma Classe Melhor (Sugeito a Cobranças).");
            }
        } else {
            System.out.println("Codigo de Classe Não Encontrado!");
        }
    }
    
    // Reserva
    public void reservar() {        
        Transacao transacao = new Transacao(codigoCliente, codigoClasse, codigoVoo);
        transacao.aplicarCobranca(); 
        transacao.imprimirRecibo();        
        transacao.salvarRecibo();
    }
    
    
    // Verificacoes e Consultas
    public boolean cadastroConfirmado() {
        return cadastrou;
    }
    
    public boolean vooConfirmado() {
        return escolheuVoo;
    }
    
    public boolean classeConfirmada() {
        return escolheuClasse;
    }
    
    public boolean podeReservar() {
        return cadastrou && escolheuVoo && escolheuClasse;
    }
}
