/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltp.unidade08.view;

import javax.swing.JOptionPane;
import ltp.unidade08.model.BancoException;

/**
 *
 * @author Bruno
 */
public class FrmAdmin extends javax.swing.JFrame {

    /**
     * Creates new form FrmIndex
     */
    public FrmAdmin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblInstrucao = new javax.swing.JLabel();
        btnListar = new javax.swing.JButton();
        btnSenha = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu de Operações");

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Banco LTP S.A.");

        lblInstrucao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstrucao.setText("Admininstrador escolha uma opção:");

        btnListar.setText("Listar Conta");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnSenha.setText("Alterar Senha");
        btnSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSenhaActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir Conta");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar Conta");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblInstrucao, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(lblInstrucao)
                .addGap(18, 18, 18)
                .addComponent(btnListar)
                .addGap(19, 19, 19)
                .addComponent(btnCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnExcluir)
                .addGap(18, 18, 18)
                .addComponent(btnSenha)
                .addGap(18, 18, 18)
                .addComponent(btnSair)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        
        this.dispose();
        
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        
        new FrmListagem().setVisible(true);
        
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        
        new FrmCadastro().setVisible(true);
        
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
        String agenciaString = JOptionPane.showInputDialog("Digite a agência da conta:");
        String contaString = JOptionPane.showInputDialog("Digite a conta:");
        
        int agencia = Integer.parseInt(agenciaString);
        int conta = Integer.parseInt(contaString);
        
        try{
            boolean sucesso = Principal.banco.excluirConta(agencia, conta);
            if(sucesso){
                JOptionPane.showMessageDialog(this, "Conta excluida com sucesso", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "Não foi possível excluir a conta.", "Exclusão" , JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(BancoException bex){
            JOptionPane.showMessageDialog(this, bex.getMessage(), "Excluisão" ,  JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSenhaActionPerformed
        
        String agenciaString = JOptionPane.showInputDialog("Digite a agência da conta:");
        String contaString = JOptionPane.showInputDialog("Digite a conta:");
        String senha = JOptionPane.showInputDialog("Digite a nova senha:");
        
        int agencia = Integer.parseInt(agenciaString);
        int conta = Integer.parseInt(contaString);
        
        try{
            boolean sucesso = Principal.banco.alterarSenha(agencia, conta, senha);
            if(sucesso){
                JOptionPane.showMessageDialog(this, "Senha alterada com sucesso", "Nova Senha", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "Não foi possível alterar a senha.", "Nova Senha" , JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(BancoException bex){
            JOptionPane.showMessageDialog(this, bex.getMessage(), "Nova Senha" ,  JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSenhaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblInstrucao;
    // End of variables declaration//GEN-END:variables
}