/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.model.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class Arquivo {
    
    public static boolean escreverRegistro(Path caminho, String linha){
        if(!linha.endsWith("\n")) linha += "\n";
        
        try{
            Files.write(caminho, linha.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            return true;
        }
        catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean atualizarRegistro(Path caminho, String chave, String linha ){
        if(!linha.endsWith("\n")) linha += "\n";
        
        List<String> linhas = lerRegistros(caminho);
        String novasLinhas = "";
        for(String l : linhas){
            if(l.startsWith(chave))
                novasLinhas += linha + "\n";
            else
                novasLinhas += l + "\n";
        }
        
        return escreverRegistros(caminho, novasLinhas);
    }
    
    public static boolean escreverRegistros(Path caminho, String linhas){
        try{
            Files.write(caminho, linhas.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        }
        catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
        return false;
    }
    
    public static List<String> lerRegistros(Path caminho){
        try{
            if(!Files.exists(caminho)) return new ArrayList<String>();
            return Files.readAllLines(caminho);
        }
        catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    } 
}
