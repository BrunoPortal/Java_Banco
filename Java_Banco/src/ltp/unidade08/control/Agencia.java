/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.control;

import java.util.ArrayList;
import java.util.List;
import ltp.unidade08.model.BancoException;
import ltp.unidade08.model.Conta;
import ltp.unidade08.model.ContaCorrente;
import ltp.unidade08.model.ContaPoupanca;
import ltp.unidade08.model.dao.ContaDao;

/**
 *
 * @author Bruno
 */
public class Agencia {
    
    private int numero;
    private List<Conta> contas;
    private static int proximaConta = 1;
    private final int TIPO_CONTA_CORRENTE = 1;
    private final int TIPO_CONTA_POUPANCA = 2;
    
    public Agencia(int numero){
        this.numero = numero;
        this.contas = new ArrayList<Conta>();
    }
    
    public List<Conta> getContas(){
        return this.contas;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public static int getProximaConta(){
        return Agencia.proximaConta;
    }

    public static void setProximaConta(int proximaConta) {
        Agencia.proximaConta = proximaConta;
    }
       
    public boolean alterarSenha(int numeroConta, String novaSenha) throws BancoException{
        for(int i = 0; i < contas.size(); i++){
            Conta conta = contas.get(i);
            if(conta.getNumero() == numeroConta){
                conta.setSenha(novaSenha);
                return true;
            }
        }
        return false;
    }
    
    public boolean excluirConta(int numeroConta){
        for(int i = 0; i < contas.size(); i++){
            Conta conta = contas.get(i);
            if(conta.getNumero() == numeroConta){
                contas.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Conta buscarConta(int numeroConta) throws BancoException{
        return contas.stream().filter(c -> c.getNumero() == numeroConta).findFirst().get();
    }
    
    public boolean depositar(int numeroConta, double valor) throws BancoException{
        Conta conta = buscarConta(numeroConta);
        return conta.depositar(valor);
    }
    
    public boolean transferir(int contaOrigem, Conta contaDestino, double valor) throws BancoException{
        Conta conta = buscarConta(contaOrigem);
        return conta.transferir(contaDestino, valor);

    }
    
    public boolean sacar(int numeroConta, double valor) throws BancoException{
        Conta conta = buscarConta(numeroConta);
        return conta.sacar(valor);
    }
    
    public Conta autenticarCliente(int numeroConta, String senha){
        
        try{
            Conta conta = buscarConta(numeroConta);
            boolean sucesso = conta.autenticar(senha);
            
            if(sucesso)
                return conta;
            else
                return null;
        }
        catch(BancoException bex){
            return null; 
        }
    }
    
    public boolean cadastrarConta(Conta conta){
        this.contas.add(conta);
        return false;
    }
    
    public boolean cadastrarConta(int tipo, String titular, String senha) throws BancoException{
        Conta conta = null;
        
        if(tipo == TIPO_CONTA_CORRENTE)
            conta = new ContaCorrente(titular, numero, proximaConta, senha); 
        else if(tipo == TIPO_CONTA_POUPANCA)
            conta = new ContaPoupanca(titular, numero, proximaConta, senha);
        else
            throw new BancoException(proximaConta, "Tipo de conta inválido.");

        contas.add(conta);
        ContaDao.salvarConta(conta);

        proximaConta++;

        return true;

    }
}
