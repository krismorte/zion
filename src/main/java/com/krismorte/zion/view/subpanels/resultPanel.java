/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.subpanels;

import com.krismorte.zion.util.Tabela;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextPane;
import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.view.InternationalizedUI;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class resultPanel extends javax.swing.JPanel implements InternationalizedUI {

    public static final int STATUS_GRID = 0;
    public static final int INDIVIDUAL_GRID = 1;
    public static final int CROSS_GRID = 2;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    private String[] statusGridColumns;
    private String[] crossGridColumns;
    private Object[][] statusGridRows;
    private Object[][] crossGridRows;
    private List<Server> servers = new ArrayList<>();
    private JTable table;
    private int gridOption;

    public resultPanel() {
        initComponents();
        initStatusColumns();
        exibeTexto(getStringI18n("NO_RESULT"));
    }

    /**
     * Creates new form panelResultados for grid status and cross
     */
    public resultPanel(List<Server> resultados, int gridOption) {
        initComponents();
        this.servers.clear();
        this.servers = resultados;
        this.gridOption = gridOption;
        switch (gridOption) {
            case STATUS_GRID:
                initStatusColumns();
                mostraResultadoEmGridStatus();
                break;
            case INDIVIDUAL_GRID:
                mostraResultado();
                break;
            case CROSS_GRID:
                initCrossColumns();
                mostraResultadoEmGridCruzado();
                break;
        }
    }

    private void initStatusColumns() {
        String[] colunasGridUnicaTmp = {getStringI18n("SERVER"), getStringI18n("RESULT")};
        statusGridColumns = colunasGridUnicaTmp;
    }

    public SQLResult getResult() {
        switch (gridOption) {
            case STATUS_GRID:
                SQLResult statusResult = new SQLResult();
                statusResult.coluna = statusGridColumns;
                statusResult.linhas = statusGridRows;
                return statusResult;
            case INDIVIDUAL_GRID:
                return servers.get(0).getSqlResult();
            case CROSS_GRID:
                SQLResult gridResult = new SQLResult();
                gridResult.coluna = crossGridColumns;
                gridResult.linhas = crossGridRows;
                return gridResult;
            default:
                return null;
        }
    }

    private void initCrossColumns() {
        int totalColunas = 0;
        Server srvComMaisColunas = null;
        for (Server s : servers) {
            if (s.getSqlResult().linhas != null) {
                if (totalColunas < s.getSqlResult().coluna.length) {
                    totalColunas = s.getSqlResult().coluna.length;
                    srvComMaisColunas = s;
                }
            }
        }

        totalColunas++;

        crossGridColumns = new String[totalColunas];
        crossGridColumns[0] = getStringI18n("SERVER");
        for (int i = 1; i < crossGridColumns.length; i++) {
            crossGridColumns[i] = srvComMaisColunas.getSqlResult().coluna[i - 1];
        }

    }

    private int totalLinhasCruzadas() {
        int total = 0;
        for (Server s : servers) {
            if (s.getSqlResult().linhas != null) {
                total += s.getSqlResult().linhas.length;
            } else {
                total += 1;
            }
        }
        return total;
    }

    private void mostraResultado() {
        //if (this.server != null) {       
        if (servers.get(0).getSqlResult().sqlErro != null) {
            exibeTexto(servers.get(0).getSqlResult().sqlErro.getRaisError());
        } else {
            if (servers.get(0).getSqlResult().eTexto) {
                exibeTexto(servers.get(0).getSqlResult().texto);
            } else {
                exibeGrid(servers.get(0).getSqlResult().coluna, servers.get(0).getSqlResult().linhas);
            }
        }
//        } else {
//            exibeTexto(getStringI18n("NO_RESULT"));
//        }
    }

    private void mostraResultadoEmGridStatus() {
        statusGridRows = new Object[servers.size()][statusGridColumns.length];
        if (servers.size() > 0) {
            for (int i = 0; i < servers.size(); i++) {
                for (int z = 0; z < statusGridColumns.length; z++) {
                    String resultadoTxt = getMsgStatus(null);//inia a tela
                    Object[][] obj = {{servers.get(i).getHost(), resultadoTxt}};
                    statusGridRows[i][z] = obj[0][z];//Rows are dynamics
                }
            }
            exibeGrid(statusGridColumns, statusGridRows);
            //setColumnsRows(statusGridColumns, statusGridRows);
        } else {
            exibeTexto(getStringI18n("NO_RESULT"));
        }
    }

    private void mostraResultadoEmGridCruzado() {

        initCrossColumns();

        crossGridRows = new Object[totalLinhasCruzadas()][crossGridColumns.length];
        int linha = 0;
        if (servers.size() > 0) {

            for (int i = 0; i < servers.size(); i++) {
                if (servers.get(i).getSqlResult().linhas == null) {
                    crossGridRows[linha][0] = servers.get(i).getHost();

                    for (int z = 0; z < crossGridColumns.length - 1; z++) {
                        crossGridRows[linha][z + 1] = getStringI18n("ERROR") + ": " + servers.get(i).getSqlResult().sqlErro.getRaisError();
                    }
                    linha++;
                } else {

                    for (int w = 0; w < servers.get(i).getSqlResult().linhas.length; w++) {

                        int colunasResultado = ((Object[]) servers.get(i).getSqlResult().linhas[w]).length;
                        crossGridRows[linha][0] = servers.get(i).getHost();
                        for (int z = 0; z < crossGridColumns.length - 1; z++) {
                            if (z >= colunasResultado) {
                                crossGridRows[linha][z + 1] = getStringI18n("NO_COLUMN_DEFINED");
                            } else {
                                crossGridRows[linha][z + 1] = servers.get(i).getSqlResult().linhas[w][z] != null ? servers.get(i).getSqlResult().linhas[w][z].toString() : "";
                            }
                        }
                        linha++;
                    }
                }
            }
            exibeGrid(crossGridColumns, crossGridRows);
            //setColumnsRows(crossGridColumns, crossGridRows);
        } else {
            exibeTexto(getStringI18n("NO_RESULT"));
        }
    }

    private String getMsgStatus(SQLResult sqlresultado) {
        String resultadoTxt = "";

        if (sqlresultado == null) {
            resultadoTxt = getStringI18n("RUNNING") + "...";
        } else if (sqlresultado.sqlErro != null) {
            resultadoTxt = "Erro: " + sqlresultado.sqlErro.getRaisError();
        } else if (sqlresultado.linhas == null) {
            resultadoTxt = getStringI18n("RUNNING") + "...";
        } else {
            resultadoTxt = getStringI18n("SUCCESS");
        }
        return resultadoTxt;
    }

    public void atualizaResultadoEmGridStatus(Server srv) {
        for (int i = 0; i < statusGridRows.length; i++) {
            if (statusGridRows[i][0].equals(srv.getHost())) {
                statusGridRows[i][1] = getMsgStatus(srv.getSqlResult());
                exibeGrid(statusGridColumns, statusGridRows);
            }
        }
    }

    private void exibeTexto(String texto) {
        JTextPane p = new JTextPane();
        p.setText(texto);
        this.setLayout(new GridLayout(1, 1));
        this.add(p);
    }

    private void exibeGrid(String[] coluna, Object[][] linhas) {
        this.setLayout(new GridLayout(1, 1));
        Tabela.preencheTabela(this, coluna, linhas);
        table = Tabela.table;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
