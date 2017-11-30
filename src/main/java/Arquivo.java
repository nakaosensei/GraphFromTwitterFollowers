package br.com.nakao.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Nakao
 */
public class Arquivo {
    private FileReader arq;
    private BufferedReader buff;
    private List<String> file;

    public List<String> lerArquivo(String path){
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


}