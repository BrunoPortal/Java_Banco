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
public class FrmTransferencia extends javax.swing.JFrame {

    /**
     * Creates new form FrmDeposito
     */
    public FrmTransferencia() {
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
        lblNumeroAgenciaDestino = new javax.swing.JLabel();
        txtNumeroAgencia = new javax.swing.JTextField();
        lblNumeroContaDestino = new javax.swing.JLabel();
        txtNumeroConta = new javax.swing.JTextField();
        lblValorTransferencia = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnTransferir = new javax.swing.JButton();
        txtRS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Deposito Sem Cartão");

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Banco LTP S.A.");

        lblNumeroAgenciaDestino.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroAgenciaDestino.setText("Número da Agência de Destino:");

        txtNumeroAgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroAgenciaActionPerformed(evt);
            }
        });

        lblNumeroContaDestino.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroContaDestino.setText("Número da Conta de Destino:");

        lblValorTransferencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorTransferencia.setText("Valor da Tranferência:");

        btnTransferir.setText("Transferir");
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });

        txtRS.setText("R$:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNumeroAgenciaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                        .addComponent(lblNumeroContaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblValorTransferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(txtRS)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumeroConta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTransferir, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(txtValor))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblNumeroAgenciaDestino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNumeroContaDestino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblValorTransferencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRS))
                .addGap(18, 18, 18)
                .addComponent(btnTransferir)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroAgenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroAgenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroAgenciaActionPerformed

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
        
        int agencia = Integer.parseInt(txtNumeroAgencia.getText());
        int conta = Integer.parseInt(txtNumeroConta.getText());
        double valor = Double.parseDouble(txtValor.getText());
        
        try{
            boolean sucesso = Principal.banco.transferirAutenticado(agencia, conta, valor);
            
            if(sucesso)
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso.", "Transferência", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Não foi possível realizar a transferência.", "Transferência", JOptionPane.ERROR_MESSAGE);
        }
        catch(BancoException bex){
            JOptionPane.showMessageDialog(this, bex.getMessage(), "Transferência", JOptionPane.ERROR_MESSAGE);
        } 
        
        this.dispose();
    }//GEN-LAST:event_btnTransferirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTransferir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNumeroAgenciaDestino;
    private javax.swing.JLabel lblNumeroContaDestino;
    private javax.swing.JLabel lblValorTransferencia;
    private javax.swing.JTextField txtNumeroAgencia;
    private javax.swing.JTextField txtNumeroConta;
    private javax.swing.JLabel txtRS;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}