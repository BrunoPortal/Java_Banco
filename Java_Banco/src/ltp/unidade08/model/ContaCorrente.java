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
public class ContaCorrente extends Conta {
    
    private final double LIMITE_ESPECIAL = 500;
    
    public ContaCorrente(String titular, int agencia, int numero, String senha) throws BancoException{
        super(titular, agencia, numero, senha);
    }
    
    @Override
    public boolean verificarSaldoSaque(double valor) throws BancoException{
        if(valor > (this.saldo + this.LIMITE_ESPECIAL))
            throw new BancoException(this.getNumero(), "Não há saldo suficiente para concluir a operação.");
        return true;
    }

    @Override
    public String getTipo() {
        return "Conta Corrente";
    }
    
}
