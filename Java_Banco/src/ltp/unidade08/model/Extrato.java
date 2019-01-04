/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Bruno
 */
public class Extrato {
    
    private int conta;
    private int agencia;
    private Date dataLancamento;
    private String lancamento;
    
    public Extrato(int conta, int agencia, String lancamento){
        this(conta, agencia, new Date(), lancamento);
    }
    
    public Extrato(int conta, int agencia, Date dataLancamento, String lancamento){
        this.conta = conta;
        this.agencia = agencia;
        this.lancamento = lancamento;
        this.dataLancamento = dataLancamento;
    }

    public int getConta() {
        return conta;
    }

    public int getAgencia() {
        return agencia;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public String getLancamento() {
        return lancamento;
    }
}
