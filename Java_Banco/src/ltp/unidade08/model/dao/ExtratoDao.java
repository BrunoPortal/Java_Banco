/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.model.dao;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import ltp.unidade08.model.Conta;
import ltp.unidade08.model.Extrato;

/**
 *
 * @author Bruno
 */
public class ExtratoDao {
    
    private static Path caminhoExtrato = Paths.get(System.getProperty("user.dir"), "extratos.csv");
    
    public static boolean salvarExtrato(Extrato extrato){
        String linha = retornarRegistro(extrato);
        return Arquivo.escreverRegistro(caminhoExtrato, linha);
        
    }
    
    public static boolean salvarExtratos(List<Extrato> extratos){
        String linhas = "";
        
        for(Extrato extrato : extratos){
            linhas += retornarRegistro(extrato);
        }
        
        return Arquivo.escreverRegistros(caminhoExtrato, linhas);
    }
    
    private static Extrato retornarExtrato(String linha) throws ParseException{
        String[] colunas = linha.split(";");
       
        int agencia = Integer.parseInt(colunas[0]);
        int conta = Integer.parseInt(colunas[1]);
        Date dataLancamento = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(colunas[2]);
        String lancamento = colunas[3];
        
        return new Extrato(conta, agencia, dataLancamento, lancamento);
    }
    
    public static List<Extrato> retornarExtratos(int agencia, int conta) throws ParseException{
        return retornarExtratos().stream().filter(c -> c.getConta() == conta && c.getAgencia() == agencia).collect(Collectors.toList());
    }
    
    public static List<Extrato> retornarExtratos() throws ParseException{
        List<String> linhas = Arquivo.lerRegistros(caminhoExtrato);
        List<Extrato> extratos = new ArrayList<>();
        
        for(String linha : linhas)
            extratos.add(retornarExtrato(linha));
        
        return extratos;
    }
    
    private static String retornarRegistro(Extrato extrato){
        String registro = "";
        
        registro += extrato.getAgencia() + ";";
        registro += extrato.getConta() + ";";
        registro += new SimpleDateFormat("yyyy-M-dd hh:mm:ss").format(extrato.getDataLancamento()) + ";";
        registro += extrato.getLancamento() + ";\n";
        
        return registro;
    }
}
