package logica;

import entidades.*;
import ferramentas.Ficheiros;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Transacao implements Serializable{

    private int codigo; // do recibo
    private String dataReserva;
    private float valorFinal;
    
    private Classe classe;
    private Cliente cliente;
    private Voo voo;
    private Rota rota;
    private Aviao aviao;
    private Filial filial;
    
    private DecimalFormat moeda;

    public Transacao(int codigoCliente, int codigoClasse, int codigoVoo) {
        
        GerirPessoas gerirPessoas = GerirPessoas.getUnicaInstancia();
        GerirAdministracao admin = GerirAdministracao.getUnicaInstancia();
        
        LocalDate data = LocalDate.now();
        dataReserva = data.toString();
        
        codigo = (int)(10000 + Math.random() * 89999); //do recibo
        moeda = new DecimalFormat("###,###.00MZN");
        
        // Relacoes
        cliente = gerirPessoas.getCliente(codigoCliente);
        voo = admin.getVoo(codigoVoo);
        classe = admin.getClasse(codigoClasse);
        rota = admin.getRota(voo.getCodigoRota());
        aviao = admin.getAviao(voo.getCodigoAviao());
        filial = admin.getFilial(aviao.getcodigoFilial());
    }

    public void aplicarCobranca() {
        float PB, LC, D, B, NB;
        
        /* 
        Fórmula: 
        
        PB - Preço Base do Voo
        LC - Limite Bagagem Da Classe
        D - Distancia Da Rota
        B - Bagagem Do Passageiro
        NB - Número De Bebês
        
        Preco = PB + 100LC + 0.5D + 50B - 200NB
        */
        
        PB = voo.getPrecoBase();
        LC = classe.getPesoLimite();
        D = rota.getDistancia();
        B = cliente.getPesoBagagem();
        NB = cliente.getNumBebes();
        
        valorFinal = PB + 100 * LC + 0.5f * D + 50 * B - 200 * NB;
    }

    public void imprimirRecibo() {
        
        String[] recibo = new String[]{
                "                                     ",
                " |    RECIBO Nr: " + codigo,
                "                                     ",
                " |    NOME: "+ cliente.getNome(),
                " |    BAGAGEM: " + cliente.getPesoBagagem() + " KG",
                " |    CLASSE: " + classe.getNome(),
                "                                     ",
                " |    Voo Nr: " + voo.getCodigo(),
                " |    Partida: " + rota.getAeroPartida(),
                " |    Chegada: " + rota.getAeroChegada(),
                " |    Data: " + voo.getData() + " " + voo.getHoraPartida() + " - " + voo.getHoraChegada(), // DO Voo
                " |    Avião: " + aviao.getModelo(),
                "                                     ",
                " |    DATA DE RESERVA: " + dataReserva, 
                " |    VALOR: " + moeda.format(valorFinal),
                "                                     ",
                "                                     ",
                "                                     ",
                "   Confirme o pagamamento por email. ",
                "                                     ",
                "                                     ",
                "                 LAM dá-te ASAS, ",
                "                 Aproveite a viagem. ",
                " |               LAM, " + filial.getNome(),
                "                                     ",
                " |                          Grupo-2  ",
                "                                     "};
        
        // Visualizacao
        System.out.println("*---------------------------------------------*");
        for (String linha: recibo) {
            System.out.printf("|%-45s|\n", linha);
        }
        System.out.println("*---------------------------------------------*");
    }

    public void salvarRecibo() {
        String recibo = "RECIBO: " + codigo
                + "\n| CODIGO CLIENTE: " + cliente.getCodigo()
                + "\n| NOME: "+ cliente.getNome()
                + "\n| BAGAGEM: " + cliente.getPesoBagagem() + " KG"
                + "\n| CLASSE: " + classe.getCodigo()
                + "\n| VALOR: " + valorFinal
                + "\n| VOO: " + voo.getCodigo()
                + "\n| FILIAL: " + filial.getCodigo()
                + "\n| AVIÃO: " + aviao.getCodigo()
                + "\n| ROTA: " + rota.getCodigo()
                + "\n| DATA DE RESERVA: " + dataReserva;
        
        // nome do txt : LAM 'codigo do recibo' C 'codigo do cliente' D 'data'.txt
        String nometxt = "LAM"+codigo+"C"+cliente.getCodigo()+"D"+dataReserva+".txt";
        Ficheiros.escreverFicheiroTxt(
                "FICHEIROS/Recibos/" + nometxt, recibo, false);
    }

}
