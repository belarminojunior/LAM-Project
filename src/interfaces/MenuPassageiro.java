package interfaces;

import ferramentas.Validacao;
import logica.GerirAdministracao;
import logica.GerirReserva;

/**
 *
 * @author Nuno Fonseca
 */
public class MenuPassageiro {

    private GerirReserva reserva;
    private GerirAdministracao admin;
    private MenuReserva menuR;

    public MenuPassageiro() {
        admin = GerirAdministracao.getUnicaInstancia();
        reserva = new GerirReserva();
    }

    private byte opcaoPassageiro() {
        System.out.println();
        System.out.println("==============================");
        System.out.println("=====Menu De Passageiros======");
        System.out.println("=====(EM DESENVOLVIMENTO)=====");
        System.out.println("[1] Comprar Bilhete");
        System.out.println("[2] Ver voos disponíveis");
        System.out.println("[3] Pesquisar Voo");
        System.out.println("");
        System.out.println("\n[0] Voltar\n");

        return Validacao.validarByte("> ", (byte) 0, (byte) 3);
    }

    public void menuPassageiro() {
        menuR = new MenuReserva();
        switch (opcaoPassageiro()) {
            case 0:
                return;
            case 1:
                menuR.menuReserva();
                break;
            case 2:
                admin.verVoo();
                break;
            case 3:
                admin.pesquisarVoo();
                break;

        }
        String aux = Validacao.validarString("\nPressione Enter ", (byte) 0, (byte) 0);
        menuPassageiro();
    }
}
