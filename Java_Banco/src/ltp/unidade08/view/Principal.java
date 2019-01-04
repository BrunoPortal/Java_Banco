/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.view;

import java.text.ParseException;
import ltp.unidade08.control.Agencia;
import ltp.unidade08.control.Banco;
import ltp.unidade08.model.BancoException;
import ltp.unidade08.model.dao.AgenciaDao;
import ltp.unidade08.model.dao.ContaDao;

/**
 *
 * @author Bruno
 */
public class Principal {
    
    protected static Banco banco = new Banco();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BancoException, ParseException{

            //banco.cadastrarAgencia();
            banco.carregarAgencias();
            banco.setProximaAgencia(AgenciaDao.retornarProximoNumeroAgencia());
            banco.carregarContas();
            Agencia.setProximaConta(ContaDao.retornarProximoNumeroConta());
            //banco.cadastrarConta(1, 1, "Luiz", "111111");
            //banco.cadastrarConta(2, 1, "Fernando", "222222");

        new FrmLogin().setVisible(true);
        
    }
    
}
