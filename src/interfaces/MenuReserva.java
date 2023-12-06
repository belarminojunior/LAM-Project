package interfaces;

import ferramentas.Validacao;
import logica.GerirAdministracao;
import logica.GerirPessoas;
import logica.GerirReserva;

public class MenuReserva {

    private GerirPessoas gerirPessoa;
    private GerirReserva reserva;
    private GerirAdministracao admin;

    public MenuReserva() {
        gerirPessoa = GerirPessoas.getUnicaInstancia();
        admin = GerirAdministracao.getUnicaInstancia();
        reserva = new GerirReserva();
    }

    private byte opcaoReserva() {
        System.out.println();
        System.out.println("==============================");
        System.out.println("=====RESERVA DE PASSAGEM======");
        System.out.println("=====(EM DESENVOLVIMENTO)=====");

        if (!reserva.cadastroConfirmado()) {
            System.out.println("[1] Cadastrar-se");
            System.out.println("[2] Acessar Conta Existente");
        }
        if (reserva.cadastroConfirmado()) {

            if (!reserva.vooConfirmado()) {
                System.out.println("[3] Escolher Voo");
            } else {
                System.out.println("[3] Trocar Voo");
            }

            if (!reserva.classeConfirmada()) {
                System.out.println("[4] Escolher Classe");
            } else {
                System.out.println("[4] Trocar Classe");
            }
        }

        if (reserva.podeReservar()) {
            System.out.println("[5] Reservar");
        }

        System.out.println("\n[0] Voltar\n");

        return Validacao.validarByte("> ", (byte) 0, (byte) 5);
    }

    public void menuReserva() {
        switch (opcaoReserva()) {
            case 0:
                return;
            case 1:
                if (!reserva.cadastroConfirmado()) {
                    reserva.cadastrar();
                }
                break;
            case 2:
                if (!reserva.cadastroConfirmado()) {
                    reserva.procurarCliente();
                }
                break;
            case 3:
                if (reserva.cadastroConfirmado()) {
                    admin.verVoo();
                    reserva.escolherVoo();
                }
                break;
            case 4:
                if (reserva.cadastroConfirmado()) {
                    admin.verClasse();
                    reserva.escolherClasse();
                }
                break;
            case 5:
                if (reserva.podeReservar()) {
                    reserva.reservar();
                }
                return;
        }
        String aux = Validacao.validarString("\nPressione Enter ", (byte) 0, (byte) 0);
        menuReserva();
    }
}
