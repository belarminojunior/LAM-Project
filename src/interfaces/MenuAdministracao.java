package interfaces;

import entidades.Filial;
import ferramentas.Validacao;
import logica.GerirAdministracao;
import logica.GerirPessoas;

public class MenuAdministracao {

    private GerirPessoas gerirPessoa;
    private GerirAdministracao admin;

    public MenuAdministracao() {
        gerirPessoa = GerirPessoas.getUnicaInstancia();
        admin = GerirAdministracao.getUnicaInstancia();
    }

    private byte selecionarOpcao() {
        System.out.println();
        System.out.println("==============================");
        System.out.println("=====Menu Administrativo======");
        System.out.println("=====(EM DESENVOLVIMENTO)=====");
        System.out.println("[1] Cadastrar Trabalhadores");
        System.out.println("[2] Adicionar Rotas");
        System.out.println("[3] Adicionar Avioes");
        System.out.println("[4] Adicionar Filiais");
        System.out.println("[5] Adicionar Voo");
        System.out.println("[6] Adicionar Classe\n");
        
        System.out.println("[7] Imprimir Clientes");
        System.out.println("[8] Imprimir Trabalhadores");
        System.out.println("[9] Imprimir Rotas");
        System.out.println("[10] Imprimir Aviões");
        System.out.println("[11] Imprimir Filiais");
        System.out.println("[12] Imprimir Classes\n");

        System.out.println("[13] Apagar Cliente");
        System.out.println("[14] Demitir Trabalhador");

        System.out.println("\n[0] Voltar\n");

        return Validacao.validarByte("> ", (byte) 0, (byte) 14);
    }

    public void menuAdmin() {

        switch (selecionarOpcao()) {
            case 1:
                Filial ultimaFilial = admin.getUltimaFilial();
                if (ultimaFilial != null){
                    admin.verFiliais();
                    gerirPessoa.cadastrarTrabalhador(ultimaFilial.getCodigo());
                }else
                    System.out.println("Cadaste uma filial para continuar.");
                break;
                
            case 2:
                admin.cadastrarRota();
                break;
                
            case 3:
                if (admin.podeCadastrarAviao())
                    admin.cadastrarAviao();
                else
                    System.out.println("Cadastre Filial para continuar.");
                break;
                
            case 4:
                admin.cadastrarFilial();
                break;
            case 5:
                if (admin.podeCadastrarVoo())
                    admin.cadastrarVoo();
                else
                    System.out.println("Cadastre Rotas e Aviões para continuar.");
                break;
            case 6:
                admin.cadastrarClasse();
                break;    
            case 7:
                gerirPessoa.imprimirClientes();
                break;
                
            case 8:
                gerirPessoa.imprimirTrabalhadores();
                break;
                
            case 9:
                admin.verRotas();
                break;

            case 10:
                admin.verAviao();
                break;
                
            case 11:
                admin.verFiliais();
                break;
                
            case 12:
                admin.verClasse();
                break;    
                
            case 13:
                gerirPessoa.apagarPessoa("Cliente",
                        Validacao.validarInt("Código Do Cliente: ", 1000, 9999));
                break;
            case 14:
                gerirPessoa.apagarPessoa("Trabalhador",
                        Validacao.validarInt("Código Do Trabalhador: ", 1000, 9999));
                break;
                
            case 0:
                return;

        }
        String aux = Validacao.validarString("Pressione Enter ", (byte) 0, (byte) 0);
        menuAdmin();
    }
}
