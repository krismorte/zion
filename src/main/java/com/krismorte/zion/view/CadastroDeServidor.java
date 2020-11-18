/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * CadastroDeServidor.java
 *
 * Created on 03/02/2015, 16:47:42
 */
package com.krismorte.zion.view;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.ServerCredential;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ServerFactory;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.krismorte.zion.service.ServerService;
import com.krismorte.zion.service.ServerServiceImpl;
import com.krismorte.zion.util.LimpaCampos;
import com.krismorte.zion.view.repository.ServerRepositoryFileImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

import com.krismorte.zion.view.service.ConnectionFactoryImpl;
import com.krismorte.zion.view.threads.SingleExecutionThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class CadastroDeServidor extends javax.swing.JDialog implements InternationalizedUI {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    private static Logger logger = LoggerFactory.getLogger(CadastroDeServidor.class);
    private DefaultMutableTreeNode node;
    private DefaultTreeModel modelTree;
    private Server server;
    private ServerService serverService = new ServerServiceImpl(new ServerRepositoryFileImpl());

    /**
     * Creates new form CadastroDeServidor
     */
    public CadastroDeServidor(JFrame parent, boolean enable) {
        super(parent, enable);
        initComponents();
        enableSalve(false);
        initTypes();
        this.setVisible(true);
    }

    private void enableSalve(boolean hab) {
        btnSalvar.setEnabled(hab);
    }

    public CadastroDeServidor(java.awt.Frame parent, DefaultMutableTreeNode node, DefaultTreeModel modelTree) {
        super(parent, true);
        initComponents();
        this.node = node;
        this.modelTree = modelTree;
        this.setTitle(String.format(getStringI18n("TITLE_ADD_SERVER"), node.toString()));
        initTypes();
    }

    private void initTypes() {
        for(SupportedDatabases d: SupportedDatabases.values()){
            boxSQLType.addItem(d);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbServer = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lbUser = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        btnTestar = new javax.swing.JButton();
        lbVersion = new javax.swing.JLabel();
        txtVersao = new javax.swing.JTextField();
        lbDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        btnSalvar = new javax.swing.JButton();
        lbType = new javax.swing.JLabel();
        boxSQLType = new javax.swing.JComboBox();
        lbPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbServer.setText(getStringI18n("SERVER")+":");
        lbServer.setName("lbServer"); // NOI18N

        lbUser.setText(getStringI18n("USER")+":");
        lbUser.setName("lbUser"); // NOI18N

        lbPass.setText(getStringI18n("PASS")+":");
        lbPass.setName("lbPass"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Sistema só funciona com usuário sysadmin.");

        btnTestar.setText(getStringI18n("TEST_CONNECTION"));
        btnTestar.setName("btnTestConn"); // NOI18N
        btnTestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestarActionPerformed(evt);
            }
        });

        lbVersion.setText(getStringI18n("VERSION")+":");
        lbVersion.setName("lbVersion"); // NOI18N

        txtVersao.setEditable(false);

        lbDescription.setText(getStringI18n("DESCRIPTION")+":");
        lbDescription.setName("lbDescription"); // NOI18N

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        btnSalvar.setText(getStringI18n("SAVE"));
        btnSalvar.setName("btnSave"); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        lbType.setText(getStringI18n("TYPE")+":");
        lbType.setName("lbType"); // NOI18N

        lbPort.setText(getStringI18n("PORT")+":");
        lbPort.setName("lbPort"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnSalvar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbDescription))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel4)))
                        .addGap(0, 218, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPass)
                                    .addComponent(lbUser)
                                    .addComponent(lbServer)
                                    .addComponent(lbType))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome)
                                    .addComponent(txtUsuario)
                                    .addComponent(txtSenha)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnTestar, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(boxSQLType, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbPort)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPort))))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbVersion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVersao)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbServer)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbType)
                    .addComponent(boxSQLType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPort)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPass)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTestar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbVersion)
                    .addComponent(txtVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (verifyFields()) {
                if (server == null) {
                    server = createNewServer();
                }
                serverService.add(server);
                modelTree.insertNodeInto(new DefaultMutableTreeNode(server), node, node.getChildCount());
                JOptionPane.showMessageDialog(this, getStringI18n("ADD_SERVER_MSG"));
                logger.info(getStringI18n("ADD_SERVER_MSG"));
                enableSalve(false);
                LimpaCampos.limpaCampos(this);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnTestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestarActionPerformed
        try {
            if (verifyFields()) {
                server = createNewServer();
            }
            enableSalve(true);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(SingleExecutionThread.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, e.getMessage(),"error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, getStringI18n("USE_SYSADMIN_MSG"), getStringI18n("CONNECTION_ERROR"), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTestarActionPerformed

    private boolean verifyFields() {
        if (txtNome.getText().equals("") || txtPort.getText().equals("") || txtUsuario.getText().equals("") || getSenha(txtSenha).equals("")) {
            JOptionPane.showMessageDialog(this, getStringI18n("ALL_FIELDS_REQUIRED_MSG"));
            return false;
        }
        return true;
    }

    private Server createNewServer() throws Exception {
        ServerCredential serverCredential = new ServerCredential(txtNome.getText().toUpperCase(),txtPort.getText(), (SupportedDatabases)boxSQLType.getSelectedItem(), "", txtUsuario.getText(), getSenha(txtSenha));
        Server server = ServerFactory.newInstance(new ConnectionFactoryImpl(),serverCredential);
        txtVersao.setText(server.getVersion());
        server.setDescription(txtDescricao.getText());
        server.setGroup(new Group(node.toString()));
        return server;
    }

    public String getSenha(JPasswordField pass) {
        char[] c = pass.getPassword();
        String str = "";
        for (char c1 : c) {
            str += c1;
        }
        return str;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                CadastroDeServidor dialog = new CadastroDeServidor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxSQLType;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnTestar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbPort;
    private javax.swing.JLabel lbServer;
    private javax.swing.JLabel lbType;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbVersion;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPort;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtVersao;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }
}
