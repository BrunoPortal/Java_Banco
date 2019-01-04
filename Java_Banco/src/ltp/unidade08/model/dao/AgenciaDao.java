/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.model.dao;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ltp.unidade08.control.Agencia;

/**
 *
 * @author Bruno
 */
public class AgenciaDao {
    
    private static Path caminhoAgencias = Paths.get(System.getProperty("user.dir"), "agencias.csv");
    
    public static boolean salvarAgencias(List<Agencia> agencias){
        String linhas = "";
        
        for(Agencia agencia : agencias){
            linhas += retornarRegistro(agencia);
        }
        
        return Arquivo.escreverRegistros(caminhoAgencias, linhas);
    }
     
    public static int retornarProximoNumeroAgencia(){
        List<Agencia> agencias = retornarAgencias();
        return agencias.stream().max(Comparator.comparing(c -> c.getNumero())).get().getNumero() + 1;
    }
     
    private static String retornarRegistro(Agencia agencia){
        String registro = "";
        
        registro += agencia.getNumero() + ";\n";
        
        return registro;
    }
    
    public static boolean salvarAgencia(Agencia agencia){
        String linha = retornarRegistro(agencia);
        return Arquivo.escreverRegistro(caminhoAgencias, linha);
        
    }
    
    public static Agencia retornarAgencia(int agencia){
        return retornarAgencias().stream().filter(c -> c.getNumero() == agencia).findFirst().get();
    }
    
    public static List<Agencia> retornarAgencias(){
        List<String> linhas = Arquivo.lerRegistros(caminhoAgencias);
        List<Agencia> agencias = new ArrayList<>();
        
        for(String linha : linhas)
            agencias.add(retornarAgencia(linha));
        
        return agencias;
    }
     
    private static Agencia retornarAgencia(String linha){
        String[] colunas = linha.split(";");
       
        int numero = Integer.parseInt(colunas[0]);
        
        return new Agencia(numero);
    }
}
