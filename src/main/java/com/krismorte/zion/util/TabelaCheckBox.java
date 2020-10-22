/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.util;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class TabelaCheckBox extends JPanel {

    private String texto = "";
    private static final long serialVersionUID = 1l;
    public static JTable table = null;
    private String celula = null;
    public TabelaModeloCheckBox model;
    private boolean ordenavel = false;
    public JScrollPane scrollPane;
    public static String[] colunas;
    public static Object[][] linhas;

    /** Create a new instance of Tabela
     *
     * @param colunas String[]
     * @param linhas Object[][]
     * @param adiconaCheck boolean
     */
    public TabelaCheckBox(String[] colunas, Object[][] linhas, boolean adicionaCheck) {
        super(new GridLayout(1, 1));
        //this.setSize(hori, vert);
        if (adicionaCheck) {
            String[] colunasNew = getNewColumnObject(colunas);
            TabelaCheckBox.colunas = colunasNew;
            Object[][] linhasNew = getNewRowsObject(colunasNew, linhas);
            TabelaCheckBox.linhas = linhasNew;
            model = new TabelaModeloCheckBox(colunasNew, linhasNew);
        } else {
            model = new TabelaModeloCheckBox(colunas, linhas);
            TabelaCheckBox.colunas = colunas;
            TabelaCheckBox.linhas = linhas;
        }


        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        //table.setPreferredScrollableViewportSize(new Dimension(hori, vert));
        table.setOpaque(false);
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setOpaque(false);
        add(scrollPane);
    }

    public TabelaCheckBox(String[] colunas, Object[][] linhas, int[] col_Editaveis, boolean adicionaCheck) {
        super(new GridLayout(1, 1));
        //this.setSize(tam);

        if (adicionaCheck) {
            String[] colunasNew = getNewColumnObject(colunas);
            Object[][] linhasNew = getNewRowsObject(colunasNew, linhas);
            model = new TabelaModeloCheckBox(colunasNew, linhasNew, col_Editaveis);
            TabelaCheckBox.colunas = colunasNew;
            TabelaCheckBox.linhas = linhasNew;
        } else {
            model = new TabelaModeloCheckBox(colunas, linhas, col_Editaveis);
            TabelaCheckBox.colunas = colunas;
            TabelaCheckBox.linhas = linhas;
        }


        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        //table.setPreferredScrollableViewportSize(tam);
        table.setOpaque(false);
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setOpaque(false);
        add(scrollPane);
    }

    private String[] getNewColumnObject(String[] colunas) {
        String[] coltmp = new String[colunas.length + 1];
        for (int i = 0; i < coltmp.length; i++) {
            if (i == 0) {
                coltmp[i] = "";
            } else {
                coltmp[i] = colunas[i - 1];
            }
        }
        return coltmp;
    }

    private Object[][] getNewRowsObject(String[] colunas, Object[][] linhas) {
        Object[][] lintmp = new Object[linhas.length][colunas.length];

        for (int i = 0; i < lintmp.length; i++) {
            for (int z = 0; z < colunas.length; z++) {
                if (z == 0) {
                    lintmp[i][z] = false;
                } else {
                    lintmp[i][z] = linhas[i][z - 1];
                }
            }
        }
        return lintmp;
    }

    /* public Tabela(String[] colunas, Object[][] linhas) {
    super(new GridLayout(1, 1));
    //this.setSize(tam);
    model = new TabelaModelo(colunas, linhas);

    table = new JTable(model);
    //table.setPreferredScrollableViewportSize(tam);
    table.setOpaque(false);
    scrollPane = new JScrollPane(table);
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.setOpaque(false);
    add(scrollPane);
    }*/
    public static TabelaCheckBox preencheTabela(JPanel panel, String[] colunas, Object[][] dados, boolean adicionaCheck) {
        TabelaCheckBox resultados = new TabelaCheckBox(colunas, dados, null, adicionaCheck);
        resultados.setVisible(true);
        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(resultados);
        panel.setVisible(true);
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        return resultados;
    }

    public static TabelaCheckBox preencheTabela(TabelaCheckBox resultados, int[] num, JPanel panel, String[] colunas, Object[][] dados, boolean adicionaCheck) {
        resultados = new TabelaCheckBox(colunas, dados, num, adicionaCheck);
        resultados.setVisible(true);
        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(resultados);
        panel.setVisible(true);
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        return resultados;
    }

        public static Object[][] buscaValores(String[] colunas, Object[][] linhas, String texto) {
        Object[][] linhasTmp = null;
        List<Object[]> list = new ArrayList<Object[]>();
        for (Object[] o : linhas) {
            for (int i = 0; i < o.length; i++) {
                if (o[i].toString().toUpperCase().trim().contains(texto.toUpperCase().trim())) {
                    //System.out.println("Vlr " + o[i].toString());
                    list.add(o);
                    break;
                }
            }
        }
        if (list.isEmpty()) {
            //System.out.println("Vazio");
            linhasTmp = new Object[0][colunas.length];
        } else {
            //System.out.println("Não Vazio");
            //System.out.println("Total: "+list.size());
            linhasTmp = new Object[list.size()][colunas.length];

            for (int i = 0; i < list.size(); i++) {
                //System.out.println("I");
                for (int z = 0; z < colunas.length; z++) {
                    //System.out.println("Z");
                    //System.out.println("Vlr "+list.get(i)[z].toString());
                    //Object[][] obj = {{lista.get(i).getSistema(), lista.get(i)}};
                    linhasTmp[i][z] = list.get(i)[z];
                }
            }
        }
        return linhasTmp;
    }

    public static void adicionaMouseListener(TabelaCheckBox resultados, MouseListener listener) {
        resultados.table.addMouseListener(listener);
    }

    public static void adicionaMouseListener(MouseListener listener) {
        table.addMouseListener(listener);
    }

    /** says whether JTable is sortable.
     * 
     * @return ordenavel a boolean field
     */
    public boolean isOrdenavel() {
        return ordenavel;
    }

    /**
     *
     */
    public void addMouseListener() {

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setTexto(getCelula());
            }
        });
    }

    /** set JTable sortable.
     * 
     * @param _ordenavel a boolean field
     */
    public void setOrdenavel(boolean _ordenavel) {
        ordenavel = _ordenavel;
        if (_ordenavel == true) {
            // RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
            //   table.setRowSorter(sorter);
        }
    }

    private String getCelula() {
        celula = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
        if (celula.equals("")) {
            celula = null;
        }
        return celula;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String temp) {
        texto = temp;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("teste");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] colunas = {"Demanda", "Tipo", "Status", "Resumo"};
        //Object[][] linha = {{"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}};
        Object[][] linha = {{new Boolean(false), new Boolean(false), "#null#", "#null#"}};
        int[] num = {2};
        TabelaCheckBox tb = new TabelaCheckBox(colunas, linha, num, false);
        tb.setVisible(true);

        frame.add(tb);
        frame.setVisible(true);
        frame.pack();
    }
}
