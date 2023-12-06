package ferramentas;

import java.io.*;

/**
 * @author Nuno Fonseca
 */
public class Ficheiros {

    public static Object lerObjecto(String nomeFich) {
        Object obj = null;
        try {
            inicializarFicherio(nomeFich);
            //este try dá close() das streams automaticamete
            FileInputStream fis = new FileInputStream(nomeFich);
            ObjectInputStream reader = new ObjectInputStream(fis);

            obj = reader.readObject();

            reader.close();
            fis.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada: (" + e.getMessage() + ")");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static void gravarObjecto(Object objecto, String nomeFich) {
        try {
            FileOutputStream fos = new FileOutputStream(nomeFich);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objecto);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar objecto: (" + e.getMessage() + ")");
        }
    }

    public static String lerFicheiroTxt(String nome) {
        String info = "";
        try {
            inicializarFicherio(nome);
            FileReader fr = new FileReader(nome);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                info += line;
                line = reader.readLine();
                // se tiver proxima linha, separa com '\n'
                if (line != null) {
                    info += "\n";
                }
            }
            reader.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return info;
    }

    public static void escreverFicheiroTxt(String nome, String info, boolean acrescentar) {

        try {
            FileWriter fw = new FileWriter(nome, acrescentar);
            BufferedWriter writer = new BufferedWriter(fw);

            writer.write(info);
            writer.newLine();
            writer.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inicializarFicherio(String nomeFich) {
        File arquivo = new File(nomeFich);
        try {
            arquivo.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro ao criar ficheiro.");
        }
    }
}
