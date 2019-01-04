/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.regex.Pattern;
import ltp.unidade08.model.dao.ContaDao;
import ltp.unidade08.model.dao.ExtratoDao;

/**
 *
 * @author Bruno
 */
public abstract class Conta {
   
    private String titular;
    private int agencia;
    private int numero;
    protected double saldo;
    private String senha;
    private List<Extrato> extrato;
    private int diaUltimoSaque;
    private double[] saquesHoje;
    private final double LIMITE_SAQUE = 500;
    private final int MAX_SAQUES = 100;
    
    public Conta(){
        this.saquesHoje = new double[MAX_SAQUES];
        this.saldo = 0;
        extrato = new ArrayList<>();
    }
    
    public Conta(String titular, int agencia, int numero, String senha) throws BancoException{
        this();
        this.agencia = agencia;
        this.titular = titular;
        this.numero = numero;
        this.setSenha(senha);
    }
    
    public List<Extrato> getExtrato(){
        return extrato;
    }

    public void setExtrato(List<Extrato> extratos) {
        this.extrato = extratos;
    }
        
    public boolean adicionarExtrato(String transacao){
        Extrato extrato = new Extrato(this.numero, this.agencia, transacao);
        this.extrato.add(extrato);
        ExtratoDao.salvarExtrato(extrato);
        return true;
    }
    
    public abstract String getTipo();
    
    public int getAgencia(){
        return this.agencia;
    }
    
    public double getSaldo(){
        return this.saldo;
    }
    
    public boolean autenticar(String senha){
        return this.senha.equals(senha);
    }

    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) throws BancoException{
        if(senha.length() > 6)
            throw new BancoException(this.numero, "A senha deve ter no máximo 6 numeros");
        
        Pattern padrao = Pattern.compile("^[0-9]{6}$");
        
        if(padrao.matcher(senha).matches())
            this.senha = senha;
        else
           throw new BancoException(this.numero, "A senha deve ter somente 6 dígitos númericos"); 
    }
    
    public String getTitular(){
        return this.titular;
    }
    
    public void setTitular(String titular){
        this.titular = titular;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public boolean depositar(double valor) throws BancoException{
        if(valor <= 0)throw new BancoException(this.numero, "O valor para o depósito é inválido.");
        
        this.saldo += valor;
        ContaDao.atualizarConta(this);
        adicionarExtrato("Depósito: R$" + valor);
        return true;
    }
    
    protected boolean verificarLimiteSaque(double valor) throws BancoException{
        if(valor > this.LIMITE_SAQUE)
            throw new BancoException(this.numero, "Limite de saque diário excedido.");
            
        if(this.diaUltimoSaque != new Date().getDay()){
            this.saquesHoje = new double[MAX_SAQUES];
            this.saquesHoje[0] = valor;
            this.diaUltimoSaque = new Date().getDay();
            return true;
        }
        else{
            double total = valor;
            for(int i = 0; i < this.saquesHoje.length; i++){
                total += this.saquesHoje[i];
            }
            if(total > this.LIMITE_SAQUE)
                throw new BancoException(this.numero, "Limite de saques diário excedido.");
            
                for(int i = 0; i < this.saquesHoje.length; i++){
                    if(this.saquesHoje[i] == 0){
                        this.saquesHoje[i] = valor;
                        return true;
                    }
                }
                
                throw new BancoException(this.numero, "Número máximo de saques diário excedido.");
            }
    }
    
    public boolean verificarSaldoSaque(double valor) throws BancoException{
        if(valor > this.saldo)
            throw new BancoException(this.numero, "Não há saldo suficiente para concluir a operação.");
        return true;
    }
    
    public boolean sacar(double valor) throws BancoException{
        if(!verificarSaldoSaque(valor) || !verificarLimiteSaque(valor))
            throw new BancoException(this.numero, "Não foi possível realizar este saque por regra do banco.");
            
        this.saldo -= valor;
        ContaDao.atualizarConta(this);
        adicionarExtrato("Saque: R$" + valor);
        return true;
    }
    
    public boolean transferir(Conta conta, double valor) throws BancoException{
        boolean sucesso = this.sacar(valor);
        if(sucesso)
            return conta.depositar(valor);
        return false;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
