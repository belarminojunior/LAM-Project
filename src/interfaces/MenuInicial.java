package interfaces;

import ferramentas.Validacao;
import logica.GerirAdministracao;
import logica.GerirPessoas;

public class MenuInicial {

    private GerirPessoas gerirPessoa;
    private GerirAdministracao admin;

    public MenuInicial() {
        gerirPessoa = GerirPessoas.getUnicaInstancia();
        admin = GerirAdministracao.getUnicaInstancia();
    }

    private byte selecionarOpcao() {
        System.out.println();
        System.out.println("==============================");
        System.out.println("====Sistema de Gestao LAM=====");
        System.out.println("==============================");
        System.out.println("[1] Menu de Passageiros");
        System.out.println("[2] Menu Administrativo");
        System.out.println("\n[0] SALVAR E SAIR\n");
        
        return Validacao.validarByte("> ", (byte) 0, (byte) 2);
    }

    public void menuInicial() {
        MenuPassageiro mp = new MenuPassageiro(); 
        MenuAdministracao ma = new MenuAdministracao();
        
        switch (selecionarOpcao()) {
            case 0:
                gerirPessoa.guardarDados();
                admin.gravarVectores();
                return;
            case 1:
                mp.menuPassageiro();
                break;
            case 2:
                if (Validacao.validarUsuario("Nome de Usuario: ", "Senha: ")) 
                    ma.menuAdmin();
                break;

        }
        String aux = Validacao.validarString("Pressione Enter ", (byte) 0, (byte) 0);
        menuInicial();
    }
}
