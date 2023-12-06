package ferramentas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Nuno Fonseca + Simão Jr
 */
public class Validacao {

    static BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

    public static String validarString(String msg, int min, int max) {
        String res = "";
        do {
            try {
                System.out.print(msg);
                res = bfr.readLine();

                if (res.length() < min || res.length() > max) {
                    System.out.println("Tamanho inválido [" + min + "-" + max + "]");
                }
            } catch (IOException ex) {
                System.out.println("Erro ao ler palavra.");
            }
        } while (res.length() < min || res.length() > max);
        return res;
    }

    public static Float validarFloat(String msg, float min, float max) {
        float res = 0;
        do {
            try {
                System.out.print(msg);
                res = Float.parseFloat(bfr.readLine());

                if (res < min || res > max) {
                    System.out.println("Intervalo inválido [" + min + "-" + max + "]");
                }
            } catch (NumberFormatException e) {
                System.out.println("Tipo de número inválido.");
            } catch (IOException ex) {
                System.out.println("Erro ao ler número.");
            }
        } while (res < min || res > max);
        return res;
    }

    public static byte validarByte(String msg, byte min, byte max) {
        byte res = 0;
        do {
            try {
                System.out.print(msg);
                res = Byte.parseByte(bfr.readLine());

                if (res < min || res > max) {
                    System.out.println("Intervalo inválido [" + min + "-" + max + "]");
                }
            } catch (NumberFormatException e) {
                System.out.println("Tipo de número inválido.");
            } catch (IOException ex) {
                System.out.println("Erro ao ler número.");
            }
        } while (res < min || res > max);
        return res;
    }

    public static short validarShort(String msg, short min, short max) {
        short res = 0;
        do {
            try {
                System.out.print(msg);
                res = Short.parseShort(bfr.readLine());

                if (res < min || res > max) {
                    System.out.println("Intervalo inválido [" + min + "-" + max + "]");
                }
            } catch (NumberFormatException e) {
                System.out.println("Tipo de número inválido.");
            } catch (IOException ex) {
                System.out.println("Erro ao ler número.");
            }
        } while (res < min || res > max);
        return res;
    }

    public static int validarInt(String msg, int min, int max) {
        int res = 0;
        do {
            try {
                System.out.print(msg);
                res = Integer.parseInt(bfr.readLine());

                if (res < min || res > max) {
                    System.out.println("Intervalo inválido [" + min + "-" + max + "]");
                }
            } catch (NumberFormatException e) {
                System.out.println("Tipo de número inválido.");
            } catch (IOException ex) {
                System.out.println("Erro ao ler número.");
            }
        } while (res < min || res > max);
        return res;
    }

    public static String validarEmail(String msg) {
        String email = "";
        boolean valido = false;
        do {
            email = validarString(msg, 6, 40);
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@'
                        && email.substring(email.length() - 4, email.length()).equalsIgnoreCase(".com")) {
                    valido = true;
                }
                //substring [ inicio, fim [
            }
            if (!valido) {
                System.out.println("Introduz um email válido...");
                System.out.println("exemplo@email.com");
            }
        } while (!valido);
        return email;
    }

    public static String validarStringAlternativo(String msg, String[] alternativas) {
        String conteudo = "";
        boolean valido = false;
        
        do {

            System.out.println("Opções possiveis:");
            for (int i = 0; i < alternativas.length; i++) {
                System.out.print(alternativas[i] + ", ");
            }

            conteudo = validarString(msg, 2, 99);
            // Verifica se o conteudo é uma das alternativas
            for (byte i = 0; i < alternativas.length; i++) {
                if (conteudo.equalsIgnoreCase(alternativas[i])) {
                    valido = true;
                    break;
                }
            }

            if (!valido) {
                System.out.println("Digite uma das opções válidas...\n");
            }
        } while (!valido);
        return conteudo.toUpperCase();
    }

    public static boolean validarUsuario(String msg1, String msg2) {
        String nomeUsuario, senha, credencias;
        String[] linhas;
        boolean valido = false;

        nomeUsuario = validarString(msg1, 2, 25);
        senha = validarString(msg2, 3, 6);
        credencias = nomeUsuario + ";" + senha;

        linhas = (Ficheiros.lerFicheiroTxt("FICHEIROS/Textos/credenciais.txt")).split("\n");

        for (String linha : linhas) {
            if (linha.equalsIgnoreCase(credencias)) {
                valido = true;
                break;
            }
        }

        if (!valido) {
            System.out.println("Nome de Usuario Ou Senha Incorretos !");
        }

        return valido;
    }
}
