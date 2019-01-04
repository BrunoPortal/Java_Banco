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
import ltp.unidade08.model.BancoException;
import ltp.unidade08.model.Conta;
import ltp.unidade08.model.ContaCorrente;
import ltp.unidade08.model.ContaPoupanca;

/**
 *
 * @author Bruno
 */
public class ContaDao {
    
    private static Path caminhoContas = Paths.get(System.getProperty("user.dir"), "contas.csv");
    
    public static boolean salvarContas(List<Conta> contas){
        String linhas = "";
        
        for(Conta conta : contas){
            linhas += retornarRegistro(conta);
        }
        
        return Arquivo.escreverRegistros(caminhoContas, linhas);
    }
    
    public static boolean salvarConta(Conta conta){
        String linha = retornarRegistro(conta);
        return Arquivo.escreverRegistro(caminhoContas, linha);   
    }
    
    public static boolean atualizarConta(Conta conta){
        String linha = retornarRegistro(conta);
        return Arquivo.atualizarRegistro(caminhoContas, conta.getTipo() + ";" + conta.getTitular() + ";" + conta.getAgencia() + ";" + conta.getNumero() + ";", linha);
    }
    
    private static String retornarRegistro(Conta conta){
        String registro = "";
        
        registro += conta.getTipo() + ";";
        registro += conta.getTitular() + ";";
        registro += conta.getAgencia() + ";";
        registro += conta.getNumero() + ";";
        registro += conta.getSenha() + ";";
        registro += conta.getSaldo() + ";\n";
        
        return registro;
    }
    
    public static Conta retornarConta(int agencia, int numero){
        return retornarContas().stream().filter(c -> c.getAgencia() == agencia && c.getNumero() == numero).findFirst().get();
    }
    
    public static List<Conta> retornarContas(){
        List<String> linhas = Arquivo.lerRegistros(caminhoContas);
        List<Conta> contas = new ArrayList<>();
        
        for(String linha : linhas){
            if(linha == null || linha.trim().equals("")) continue;
            contas.add(retornarConta(linha));
        }
        return contas;
    }
    
    public static int retornarProximoNumeroConta(){
        List<Conta> contas = retornarContas();
        return contas.stream().max(Comparator.comparing(c -> c.getNumero())).get().getNumero() + 1;
    }
    
    private static Conta retornarConta(String linha){
        String[] colunas = linha.split(";");
        Conta conta = null;
        
        String tipoConta = colunas[0];
        String titular = colunas[1];
        int agencia = Integer.parseInt(colunas[2]);
        int numero = Integer.parseInt(colunas[3]);
        String senha = colunas[4];
        double saldo = Double.parseDouble(colunas[5]);
        
        try{
            if(tipoConta.equals("Conta Corrente"))
                conta = new ContaCorrente(titular, agencia, numero, senha);
            else if(tipoConta.equals("Conta Poupança"))
                conta = new ContaPoupanca(titular, agencia, numero, senha);
            
            conta.setSaldo(saldo);
        }
        catch(BancoException bex){
            bex.printStackTrace();
        }

        return conta;
    }
    
}
