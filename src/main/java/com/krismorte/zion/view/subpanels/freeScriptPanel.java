/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
package com.krismorte.zion.view.subpanels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.krismorte.zion.model.Script;
import com.krismorte.zion.view.CadastroDeScript;
import com.krismorte.zion.view.util.EditorSQL;
import com.krismorte.zion.util.SQLDialect;
import com.krismorte.zion.view.operationPanel;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JTextField;

/**
 *
 * @author C007329
 */
public class freeScriptPanel extends scriptPanel {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    private EventoItem eventoItem = new EventoItem();

    /**
     * Creates new form panelScriptLivre
     */
    public freeScriptPanel(operationPanel panel) {
        super(panel);
        initComponents();
        prencheBox();
        boxScript.addItemListener(eventoItem);
        //disable button for the next version
        boxScript.setEnabled(false);
        boxScript.setToolTipText("on the next version");
        btnFindScript.setEnabled(false);
        btnFindScript.setToolTipText("on the next version");
    }

    private void prencheBox() {
        boxScript.removeItemListener(eventoItem);
        boxScript.removeAllItems();

        boxScript.addItem("");

        for (Script s : Script.getListaDeScripts("MSSQL")) {
            boxScript.addItem(s);
        }
        boxScript.addItemListener(eventoItem);
    }

    class EventoItem implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            if (boxScript.getSelectedItem() != null) {
                if (!boxScript.getSelectedItem().toString().equals("")) {
                    String txt = Script.getScriptPorNome("MSSQL", boxScript.getSelectedItem().toString());
                    txtScript.setText("");
                    txtScript.setText(txt);
                }
            }
        }
    }

    public String getScript() {
        return txtScript.getText();
    }

    public void setScript(String script) {
        txtScript.setText(script);
    }
    
    public String getPGDatabase(){
        if(txtPGDatabase.getText().equals(getStringI18n("PG_DATABASE_PLACEHOLDER"))){
            return "";
        }
        return txtPGDatabase.getText();
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
        boxScript = new javax.swing.JComboBox();
        btnFindScript = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtScript = new EditorSQL(SQLDialect.getDialectMSSQL());
        txtPGDatabase = new JTextField();

        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        jLabel1.setText("Scripts usuais:");

        btnFindScript.setText("jButton1");
        btnFindScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindScriptActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(txtScript);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxScript, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindScript, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPGDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(boxScript, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindScript)
                    .addComponent(txtPGDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
        );

        txtPGDatabase.setForeground(Color.GRAY);
        txtPGDatabase.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtPGDatabase.getText().equals(getStringI18n("PG_DATABASE_PLACEHOLDER"))) {
                    txtPGDatabase.setText("");
                    txtPGDatabase.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtPGDatabase.getText().isEmpty()) {
                    txtPGDatabase.setForeground(Color.GRAY);
                    txtPGDatabase.setText(getStringI18n("PG_DATABASE_PLACEHOLDER"));
                }
            }
        });
        txtPGDatabase.grabFocus();
        txtPGDatabase.setToolTipText(getStringI18n("PG_DATABASE_TOOLTIP"));
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindScriptActionPerformed
        CadastroDeScript tel = new CadastroDeScript(null, true);
        tel.setVisible(true);
        prencheBox();
    }//GEN-LAST:event_btnFindScriptActionPerformed

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxScript;
    private javax.swing.JButton btnFindScript;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtPGDatabase;
    private javax.swing.JTextPane txtScript;
    // End of variables declaration//GEN-END:variables

 
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }
    
}
