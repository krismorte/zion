/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * panelLateral.java
 *
 * Created on 27/01/2015, 18:15:59
 */
package com.krismorte.zion.view;

import java.awt.Color;
import javax.swing.event.ChangeEvent;
import com.krismorte.zion.view.subpanels.resultPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import com.krismorte.zion.model.Parametros;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.service.ExportSQLResult;
import com.krismorte.zion.view.service.ExportSQLResultCsvImpl;
import com.krismorte.zion.view.subpanels.*;
import com.krismorte.zion.view.threads.ExecutionManagerThread;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class operationPanel extends javax.swing.JPanel implements InternationalizedUI {
    
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    private JTabbedPane tabExecucoes = new JTabbedPane();
    private JTabbedPane tabResultados = new JTabbedPane();
    private freeScriptPanel panelScript;
    private listScriptPanel panelLista;
    private SelectServers iSelectedServers;
    private ExecutionManagerThread thread;
    private List<Server> selectedServers;
    private resultPanel panelGridStatus;
    private int count = 0;
    private ExportSQLResult exportSQLResult = new ExportSQLResultCsvImpl();

    /**
     * Creates new form panelLateral
     */
    public operationPanel(SelectServers iSelectedServers) {
        initComponents();
        initiateJLabel();
        criaPanelDeafult();
        this.iSelectedServers = iSelectedServers;
        habilitaCheckBox(0);
    }
    
    public void criaPanelDeafult() {
        tabExecucoes.removeAll();
        panelScript = new freeScriptPanel(this);
        panelLista = new listScriptPanel(this);
        tabExecucoes.addTab(getStringI18n("SCRIPT"), null, panelScript, getStringI18n("SCRIPT"));
        panelExecucoes.setLayout(new GridLayout(1, 1));
        panelExecucoes.add(tabExecucoes);
        
        tabResultados.removeAll();
        tabResultados.addTab(getStringI18n("RESULT"), null, new resultPanel(), getStringI18n("RESULT"));
        
        tabResultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem closer = new JMenuItem(new AbstractAction(getStringI18n("EXPORT")) {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            resultPanel p = (resultPanel) tabResultados.getSelectedComponent();//tabResultados.getTabComponentAt(tabResultados.getSelectedIndex());
                            try {
                                String dirPath = chooseDirectory();
                                if (!dirPath.equals("")) {
                                    exportSQLResult.export(dirPath + "/Result.csv", p.getResult());
                                    JOptionPane.showConfirmDialog(null, getStringI18n("FILE_EXPORT_MSG"));
                                }
                                
                            } catch (Exception ex) {
                                Logger.getLogger(operationPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    menu.add(closer);
                    menu.show(tabResultados, e.getX(), e.getY());
                }
            }
        });
        
        panelResultados.setLayout(new GridLayout(1, 1));
        panelResultados.add(tabResultados);
        progress.setForeground(new Color(152, 251, 152));
        tabExecucoes.addChangeListener(new ChangeListener() {
            
            public void stateChanged(ChangeEvent e) {
                if (tabExecucoes.getSelectedComponent() != null) {
                    if (tabExecucoes.getSelectedComponent() instanceof freeScriptPanel) {
                        habilitaCheckBox(0);
                    } else if (tabExecucoes.getSelectedComponent() instanceof listScriptPanel) {
                        habilitaCheckBox(1);
                    }
                }
            }
        });
    }
    
    private void habilitaCheckBox(int tela) {
        if (tela == 0) {
            radAbas.setEnabled(true);
            if (Parametros.tabResult == 1) {
                radAbas.setSelected(true);
            }
            if (Parametros.tabGridStatus == 1) {
                radGridStatus.setSelected(true);
            }
            if (Parametros.tabGridCross == 1) {
                radGridCruzado.setSelected(true);
            }
        } else {
            radAbas.setEnabled(false);
        }
    }
    
    public List<String[]> getResultTabsName() {
        List<String[]> indTitle = new ArrayList<String[]>();
        for (int i = 0; i < tabResultados.getTabCount(); i++) {
            String[] iT = {String.valueOf(i), tabResultados.getTitleAt(i)};
            indTitle.add(iT);
        }
        return indTitle;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelExecucoes = new javax.swing.JPanel();
        panelResultados = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnExecutar = new javax.swing.JButton();
        radGridCruzado = new javax.swing.JCheckBox();
        radGridStatus = new javax.swing.JCheckBox();
        radAbas = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        txtParalelismo = new javax.swing.JTextField();
        labP = new javax.swing.JLabel();
        txtAguardando = new javax.swing.JTextField();
        labA = new javax.swing.JLabel();
        txtFalha = new javax.swing.JTextField();
        labF = new javax.swing.JLabel();
        txtSucesso = new javax.swing.JTextField();
        labS = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        labT = new javax.swing.JLabel();

        javax.swing.GroupLayout panelExecucoesLayout = new javax.swing.GroupLayout(panelExecucoes);
        panelExecucoes.setLayout(panelExecucoesLayout);
        panelExecucoesLayout.setHorizontalGroup(
            panelExecucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelExecucoesLayout.setVerticalGroup(
            panelExecucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );

        panelResultados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelResultadosLayout = new javax.swing.GroupLayout(panelResultados);
        panelResultados.setLayout(panelResultadosLayout);
        panelResultadosLayout.setHorizontalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        panelResultadosLayout.setVerticalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnExecutar.setIcon(new javax.swing.ImageIcon("/home/krismorte/repo/zion/src/main/resources/imagens/play.png")); // NOI18N
        btnExecutar.setText(getStringI18n("RUN"));
        btnExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutarActionPerformed(evt);
            }
        });

        radGridCruzado.setText(getStringI18n("CHKCROSS"));
        radGridCruzado.setToolTipText(getStringI18n("CHKCROSS_TIP"));

        radGridStatus.setText(getStringI18n("CHKSTATUS"));
        radGridStatus.setToolTipText(getStringI18n("CHKSTATUS_TIP"));

        radAbas.setText(getStringI18n("CHKTABBED"));
        radAbas.setToolTipText(getStringI18n("CHKTABBED_TIP"));

        jButton1.setIcon(new javax.swing.ImageIcon("/home/krismorte/repo/zion/src/main/resources/imagens/stop.png")); // NOI18N
        jButton1.setText(getStringI18n("STOP"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(radAbas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radGridStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radGridCruzado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExecutar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExecutar)
                    .addComponent(radGridCruzado)
                    .addComponent(radGridStatus)
                    .addComponent(radAbas)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(getStringI18n("EXECUTION")));

        labP.setText("Paralelismo");

        txtAguardando.setEditable(false);

        labA.setText("Aguardando");

        txtFalha.setEditable(false);

        labF.setText("Com Falha");

        txtSucesso.setEditable(false);

        labS.setText("Com Sucesso");

        txtTotal.setEditable(false);

        labT.setText("Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSucesso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtFalha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAguardando, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtParalelismo)
                    .addComponent(txtTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labP)
                    .addComponent(labA)
                    .addComponent(labF)
                    .addComponent(labS)
                    .addComponent(labT))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtParalelismo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAguardando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFalha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSucesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelExecucoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelExecucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        System.out.println("Test:"+panelScript.getPGDatabase());
        if (panelScript.getScript().equals("")) {
            JOptionPane.showMessageDialog(this, getStringI18n("TYPE_SCRIPT_MSG"));
            
        } else if (!panelScript.verificaParalelismo()) {
            JOptionPane.showMessageDialog(null, Parametros.errorMessageParallelism, getStringI18n("INVALID_PARALLELISM"), JOptionPane.ERROR_MESSAGE);
        } else {
            selectedServers = iSelectedServers.getServers();
            if (selectedServers.size() == 0) {
                JOptionPane.showMessageDialog(this, getStringI18n("NONE_SERVER_CHOOSEN_MSG"));
            } else {
                if (!radAbas.isSelected() && !radGridCruzado.isSelected() && !radGridStatus.isSelected()) {
                    int option = JOptionPane.showConfirmDialog(null, getStringI18n("NO_DISPLAY_CONF_MSG"), getStringI18n("NO_DISPLAY"), JOptionPane.INFORMATION_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        iniciaThreadExecucao(selectedServers);
                    }
                } else {
                    iniciaThreadExecucao(selectedServers);
                }
            }
        }
    }//GEN-LAST:event_btnExecutarActionPerformed
    
    private void iniciaThreadExecucao(List<Server> servidores) {
        resetProgress(servidores.size());
        tabResultados.removeAll();
        thread = new ExecutionManagerThread(servidores, this, Integer.parseInt(panelScript.getParallelism()), panelScript.getScript());
        thread.start();
        panelResultados.setLayout(new GridLayout(1, 1));
        panelResultados.add(tabResultados);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (thread != null) {
            if (thread.isAlive()) {
                thread.running = false;
                JOptionPane.showMessageDialog(this, getStringI18n("INTERRUPED_EXECUTION"));
                btnExecutar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, getStringI18n("NO_EXECUTION_ALIVE"));
            }
        } else {
            JOptionPane.showMessageDialog(this, getStringI18n("NO_EXECUTION_ALIVE"));
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExecutar;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labA;
    private javax.swing.JLabel labF;
    private javax.swing.JLabel labP;
    private javax.swing.JLabel labS;
    private javax.swing.JLabel labT;
    private javax.swing.JPanel panelExecucoes;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JProgressBar progress;
    private javax.swing.JCheckBox radAbas;
    private javax.swing.JCheckBox radGridCruzado;
    private javax.swing.JCheckBox radGridStatus;
    private javax.swing.JTextField txtAguardando;
    private javax.swing.JTextField txtFalha;
    private javax.swing.JTextField txtParalelismo;
    private javax.swing.JTextField txtSucesso;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void resetProgress(int maxSize) {
        count = 0;
        progress.setValue(count);
        progress.setMinimum(count);
        progress.setMaximum(maxSize);
        progress.setStringPainted(true);
    }
    
    public void startCounter() {
        if (radGridStatus.isSelected()) {
            buildStatusGrid();
        }
        panelScript.iniciaContadores(selectedServers.size(), 0, 0);
    }
    
    public void exibeResultado(Server srv) {
        if (srv.getSqlResult().sqlErro != null) {//  ERRO!
            panelScript.atualizaContadorNegativo();
        } else {
            panelScript.atualizaContadorPositivo();
        }
        if (radAbas.isSelected()) {
            exibeResultadoPorAba(srv);
        }
        if (radGridStatus.isSelected()) {
            atualizaStatus(srv);
        }
        
        if (radGridCruzado.isSelected()) {
            if (count == (selectedServers.size() - 1)) {
                exibeResultadoCruzado(selectedServers);
            }
        }
        count++;
        this.progress.setValue(count);
    }
    
    private void buildStatusGrid() {
        panelGridStatus = new resultPanel(selectedServers, resultPanel.STATUS_GRID);
        tabResultados.addTab(getStringI18n("EXECUTION_STATUS"), null, panelGridStatus, getStringI18n("TOTAL_LINES") + selectedServers.size());
    }
    
    private void exibeResultadoPorAba(Server srv) {
        if (srv.getSqlResult() != null) {
            if (srv.getSqlResult().sqlErro != null) {//  ERRO!

                tabResultados.addTab(srv.getHost() + "!", null, new resultPanel(Arrays.asList(srv), resultPanel.INDIVIDUAL_GRID), "0");
                tabResultados.setForegroundAt(tabResultados.getTabCount() - 1, Color.red);
            } else {
                tabResultados.addTab(srv.getHost(), null, new resultPanel(Arrays.asList(srv), resultPanel.INDIVIDUAL_GRID), getStringI18n("TOTAL_LINES") + srv.getSqlResult().linhas.length);
            }
        }
    }
    
    private void exibeResultadoCruzado(List<Server> srvs) {
        resultPanel panelGridCruzado = new resultPanel(srvs, resultPanel.CROSS_GRID);
        tabResultados.addTab(getStringI18n("CHKCROSS"), null, panelGridCruzado, getStringI18n("SERVER_CROSSED") + srvs.size());
    }
    
    private void atualizaStatus(Server srv) {
        panelGridStatus.atualizaResultadoEmGridStatus(srv);
    }
    
    private void initiateJLabel() {
        labP.setText(getStringI18n("PARALLELISM"));
        labA.setText(getStringI18n("WAITING"));
        labT.setText(getStringI18n("TOTAL"));
        labF.setText(getStringI18n("FAIL"));
        labS.setText(getStringI18n("SUCCESS"));
    }
    
    private String chooseDirectory() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle(getStringI18n("DIR_CHOOSE_MSG"));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                return jfc.getSelectedFile().getAbsolutePath();
            }
        }
        return "";
    }
    
    public void disableButton() {
        btnExecutar.setEnabled(false);
    }
    
    public void enableButton() {
        btnExecutar.setEnabled(true);
    }
    
    public void setFail(String s) {
        txtFalha.setText(s);
    }
    
    public String getFail() {
        return txtFalha.getText();
    }
    
    public void setSuccess(String s) {
        txtSucesso.setText(s);
    }
    
    public String getSuccess() {
        return txtSucesso.getText();
    }
    
    public void setTotal(String s) {
        txtTotal.setText(s);
    }
    
    public String getTotal() {
        return txtTotal.getText();
    }
    
    public void setWainting(String s) {
        txtAguardando.setText(s);
    }
    
    public String getWainting() {
        return txtAguardando.getText();
    }
    
    public void setParallelism(String s) {
        txtParalelismo.setText(s);
    }
    
    public String getParallelism() {
        return txtParalelismo.getText();
    }
    
    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }
}
