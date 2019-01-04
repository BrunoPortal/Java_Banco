/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.model;

/**
 *
 * @author Bruno
 */
public class BancoException extends Exception{
    
    private int numeroConta;
    
    public BancoException(int numeroConta, String mensagem){
        super(mensagem);
        this.numeroConta = numeroConta;
    }
}
