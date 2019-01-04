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
public class ContaPoupanca extends Conta{
    
    private final double JURO_MENSAL = 1.006;
    private final double SALDO_INICIAL = 50;
    
    public ContaPoupanca(){
        super();
        this.saldo = SALDO_INICIAL;             
    }
    
     public ContaPoupanca(String titular, int agencia, int numero, String senha) throws BancoException{
        super(titular, agencia, numero, senha);
        this.saldo = SALDO_INICIAL;
    }
    
    public double verificarRendimentos() throws BancoException{
        if(this.saldo <= 0)
            throw new BancoException(this.getNumero(), "Não há saldo em conta para obter rendimentos da Poupança.");
        return this.saldo * this.JURO_MENSAL; // 1.006
    }

    @Override
    public String getTipo() {
        return "Conta Poupança";
    }
    
}
