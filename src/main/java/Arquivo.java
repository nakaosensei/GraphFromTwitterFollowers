import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Nakao
 */
public class Arquivo {
    public static FileReader arq;
    public static BufferedReader buff;
    public  static List<String> file;

    public static List<String> lerArquivo(String path){
        file = new ArrayList<String>();
        try {
            arq = new FileReader (path);
            buff = new BufferedReader(arq);
            while (buff.ready()){
                file.add(buff.readLine());
            }
            buff.close();
            return file;
        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo");
            return file;
        }
    }

    public static void escreverArquivo(String texto,String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        buffWrite.append(texto + "\n");
        buffWrite.close();
    }
}