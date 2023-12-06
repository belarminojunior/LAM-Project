package entidades;

import ferramentas.Ficheiros;
import java.io.Serializable;
import java.time.LocalDate;
import logica.GerirPessoas;

public class Transacao implements Serializable{

    private int codigo;
    private String dataReserva;
    private float valor;
    private int codigoClasse;
    private Cliente cliente;
    private int codigoVoo;
    
    private GerirPessoas gerirPessoas;

    public Transacao(int codigoCliente,
            int codigoClasse, int codigoVoo) {
        
        LocalDate data = LocalDate.now();
        this.dataReserva = data.toString();
        
        this.codigo = (int)(1000 + Math.random() * 1000);
        gerirPessoas = GerirPessoas.getUnicaInstancia();
        this.cliente = gerirPessoas.getCliente(codigoCliente);
        this.codigoClasse = codigoClasse;
        this.codigoVoo = codigoVoo;
    }


    public void aplicarCobranca() {
        //formula do preco
    }

    public void imprimirRecibo() {
        String[] recibo = new String[]{
                "                                     ",
                " |    RECIBO Nr: " + codigo,
                " |                                   ",
                " |    NOME: "+ cliente.getNome(),
                " |    BAGAGEM: " + cliente.getPesoBagagem() + " KG",
                " |    CLASSE: " + codigoClasse,
                " |                                   ",
                " |    VALOR:                         ",
                " |                                   ",
                " |    VOO Nr: " + codigoVoo,
                " |                                   ",
                " |    DATA DE RESERVA: " + dataReserva,
                "                                     ",
                "                              LAM    "};
        
        System.out.println("*----------------------------------------*");
        for (String linha: recibo) {
            System.out.printf("|%-40s|\n", linha);
        }
        System.out.println("*----------------------------------------*");
    }

    public void salvarRecibo() {
        String recibo = "RECIBO Nr: " + codigo
                + "\n| CODIGO CLIENTE: " + cliente.getCodigo()
                + "\n| NOME: "+ cliente.getNome()
                + "\n| BAGAGEM: " + cliente.getPesoBagagem() + " KG"
                + "\n| CLASSE: " + codigoClasse
                + "\n| VALOR: "
                + "\n| VOO Nr: " + codigoVoo
                + "\n| DATA DE RESERVA: " + dataReserva;
        
        // nome do txt : LAM 'codigo do recibo' C 'codigo do cliente' .txt
        Ficheiros.escreverFicheiroTxt("FICHEIROS/Recibos/LAM"+codigo+"C"+cliente.getCodigo()+".txt", recibo, false);
    }

}
