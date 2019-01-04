/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import ltp.unidade08.model.BancoException;
import ltp.unidade08.model.Conta;
import ltp.unidade08.model.dao.AgenciaDao;
import ltp.unidade08.model.dao.ContaDao;
import ltp.unidade08.model.dao.ExtratoDao;

/**
 *
 * @author Bruno
 */
public class Banco {
    
    private static List<Agencia> agencias;
    private static int proximaAgencia = 1;
    private static final int NUMERO_AGENCIAS = 100;
    private final String SENHA_MASTER = "@dmin";
    private static Conta contaAutenticada = null;
    private static int agenciaAutenticada = 0;
    
    public Banco(){
        this.agencias = new ArrayList<>();
    }

    public static void setProximaAgencia(int proximaAgencia) {
        Banco.proximaAgencia = proximaAgencia;
    }
        
    public boolean excluirConta(int agencia, int conta) throws BancoException{
        Agencia ag = buscarAgencia(agencia);
        return ag.excluirConta(conta);
    }
    
    public boolean alterarSenha(int agencia, int conta, String novaSenha) throws BancoException{
        Agencia ag = buscarAgencia(agencia);
        return ag.alterarSenha(conta, novaSenha);
    }
    
    public List<Integer> retornarNumeroAgencias(){
        List<Integer> numeros = new ArrayList<>();
        for(Agencia agencia : agencias)
            numeros.add(agencia.getNumero());
        return numeros;
    }
    
    public void carregarAgencias(){
        this.agencias = AgenciaDao.retornarAgencias();
    }
    
    public void carregarContas() throws ParseException{
        List<Conta> contas = ContaDao.retornarContas();
        
        for(Conta conta : contas){
            conta.setExtrato(ExtratoDao.retornarExtratos(conta.getAgencia(), conta.getNumero()));
        }
        
        for(Agencia agencia : agencias){
            for(Conta conta : contas){
                if(conta.getAgencia() == agencia.getNumero())
                    agencia.cadastrarConta(conta);
            }    
        }
    }
    
    public List<Conta> retornarContas(){
        
        List<Conta> contas = new ArrayList<>();
        
        for(Agencia agencia : agencias){
            contas.addAll(agencia.getContas());
        }
        
        return contas;
    }
    
    public Agencia buscarAgencia(int agencia){
        return this.agencias.stream().filter(a -> a.getNumero() == agencia).findFirst().get();
    }
    
    public Conta getContaAutenticada(){
        return contaAutenticada;
    }
    
    public boolean autenticarAdministrador(String senha){
        return SENHA_MASTER.equals(senha);
    }
    
    public boolean autenticarCliente(int agencia, int numeroConta, String senha){
        Agencia ag = buscarAgencia(agencia);          
        Conta conta = ag.autenticarCliente(numeroConta, senha);
        if(conta != null){
            agenciaAutenticada = ag.getNumero();
            contaAutenticada = conta;
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean cadastrarAgencia(){
        Agencia agencia = new Agencia(proximaAgencia++);
        this.agencias.add(agencia);
        AgenciaDao.salvarAgencia(agencia);
        return true;
    }
    
    public boolean cadastrarConta(int tipo, int agencia, String titular, String senha) throws BancoException{
        Agencia ag = buscarAgencia(agencia);
        return ag.cadastrarConta(tipo, titular, senha);
    }
    
    public boolean depositar(int agencia, int conta, double valor) throws BancoException{
        Agencia ag = buscarAgencia(agencia);
        return ag.depositar(conta, valor);
    }
    
    public boolean transferirAutenticado(int agenciaDestino, int contaDestino, double valor) throws BancoException{
        return transferir(agenciaAutenticada, contaAutenticada.getNumero(), agenciaDestino, contaDestino, valor);    
    }
    
    public boolean transferir(int agenciaOrigem, int contaOrigem, int agenciaDestino, int contaDestino, double valor) throws BancoException{
        Agencia agDestino = buscarAgencia(agenciaDestino);
        Conta conta = agDestino.buscarConta(contaDestino);
        
        Agencia agOrigem = buscarAgencia(agenciaOrigem);
        return agOrigem.transferir(contaOrigem, conta, valor);
    }
    
    public boolean sacar(int agencia, int conta, double valor) throws BancoException{
        Agencia ag = buscarAgencia(agencia);
        return ag.sacar(conta, valor);
    }
    
    public boolean sacarAutenticado(double valor) throws BancoException{
        return sacar(agenciaAutenticada, contaAutenticada.getNumero(), valor);
    }
}
