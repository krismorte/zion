/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.util;

import java.awt.*;
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
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class Tabela extends JPanel {

    private String texto = "";
    private static final long serialVersionUID = 1l;
    public static JTable table = null;
    private String celula = null;
    public TabelaModelo model;
    private boolean ordenavel = false;
    public JScrollPane scrollPane;
    private static Dimension bounds;
    public static String[] colunas;
    public static Object[][] linhas;
    private Font font = new Font("Impact", Font.ITALIC, 15);

    /**
     * Create a new instance of Tabela
     *
     * @param colunas String[]
     * @param linhas Object[][]
     */
    public Tabela(String[] colunas, Object[][] linhas) {
        super(new GridLayout(1, 1));
        model = new TabelaModelo(colunas, linhas);
        Tabela.colunas = colunas;
        Tabela.linhas = linhas;
        table = new JTable(model);
        settupTableDisplay();
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(bounds);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setOpaque(false);
        add(scrollPane);
    }

    public Tabela(String[] colunas, Object[][] linhas, TableCellRenderer renderer) {
        super(new GridLayout(1, 1));
        //this.setSize(hori, vert);
        model = new TabelaModelo(colunas, linhas);
        Tabela.colunas = colunas;
        Tabela.linhas = linhas;
        table = new JTable(model);
        settupTableDisplay();

        try {
            table.setDefaultRenderer(Class.forName("java.lang.String"), renderer);
        } catch (ClassNotFoundException ex) {
            System.exit(0);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(bounds);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setOpaque(false);
        add(scrollPane);
    }

    public Tabela(String[] colunas, Object[][] linhas, int[] col_Editaveis, TableCellRenderer renderer) {
        super(new GridLayout(1, 1));
        //this.setSize(tam);
        model = new TabelaModelo(colunas, linhas, col_Editaveis);
        Tabela.colunas = colunas;
        Tabela.linhas = linhas;
        table = new JTable(model);
        settupTableDisplay();
        try {
            table.setDefaultRenderer(Class.forName("java.lang.String"), renderer);
        } catch (ClassNotFoundException ex) {
            System.exit(0);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(bounds);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setOpaque(false);
        add(scrollPane);
    }

    public Tabela(String[] colunas, Object[][] linhas, int[] col_Editaveis) {
        super(new GridLayout(1, 1));
        //this.setSize(tam);
        model = new TabelaModelo(colunas, linhas, col_Editaveis);
        Tabela.colunas = colunas;
        Tabela.linhas = linhas;
        table = new JTable(model);
        settupTableDisplay();
        this.setSize(bounds);
        scrollPane.setSize(bounds);
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setOpaque(false);
        add(scrollPane);
    }

    public static Tabela preencheTabela(JPanel panel, String[] colunas, Object[][] dados) {
        //int[] col_Editaveis = new int[1];//passra o campo null diretamente gera ambiguidade
        bounds = new Dimension(panel.getWidth(), panel.getHeight());
        Tabela resultados = new Tabela(colunas, dados);
        resultados.setVisible(true);

        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();

        panel.add(resultados);
        panel.setLayout(new GridLayout(1, 1));
        //Imagem image = new Imagem(i, panel.getWidth(), panel.getHeight());
        //bounds = panel.getSize();        
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        //resultados.setSize(panel.getWidth(), panel.getHeight());
        return resultados;
    }

    public static Tabela preencheTabela(JPanel panel, String[] colunas, Object[][] dados, TableCellRenderer renderer) {
        bounds = panel.getSize();
        Tabela resultados = new Tabela(colunas, dados, renderer);
        resultados.setVisible(true);

        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.add(resultados);
        // panel.setVisible(true);
        panel.setLayout(new GridLayout(1, 1));        
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        return resultados;
    }

    public static Tabela preencheTabela(JPanel panel, JTextField txt, String[] colunas, Object[][] dados) {
        //int[] col_Editaveis = new int[1];//passra o campo null diretamente gera ambiguidade
        bounds = panel.getSize();
        Tabela resultados = new Tabela(colunas, dados);
        resultados.setVisible(true);
        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        txt.setText("" + dados.length);
        panel.add(resultados);
        
        resultados.table.setColumnSelectionAllowed(true);
        panel.setLayout(new GridLayout(1, 1));
        panel.validate();
        return resultados;
    }

    public static Tabela preencheTabela(JPanel panel, JTextField txt, String[] colunas, Object[][] dados, TableCellRenderer renderer) {
        bounds = panel.getSize();
        Tabela resultados = new Tabela(colunas, dados, renderer);
        resultados.setVisible(true);
        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.add(resultados);
        panel.setVisible(true);
        panel.setLayout(new GridLayout(1, 1));        
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        txt.setText("" + dados.length);
        return resultados;
    }

    public static Tabela preencheTabela(int[] num, JPanel panel, String[] colunas, Object[][] dados) {
        bounds = panel.getSize();
        Tabela resultados = new Tabela(colunas, dados, num);
        resultados.setVisible(true);
        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.add(resultados);
        panel.setVisible(true);
        panel.setLayout(new GridLayout(1, 1));        
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        return resultados;
    }

    public static Tabela preencheTabela(JTextField txt, int[] num, JPanel panel, String[] colunas, Object[][] dados, TableCellRenderer renderer) {
        bounds = panel.getSize();
        Tabela resultados = new Tabela(colunas, dados, num, renderer);
        resultados.setVisible(true);
        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.add(resultados);
        panel.setVisible(true);
        panel.setLayout(new GridLayout(1, 1));        
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        txt.setText("" + dados.length);
        return resultados;
    }

    public static Tabela preencheTabela(Tabela resultados, int[] num, JPanel panel, String[] colunas, Object[][] dados) {
        bounds = panel.getSize();
        resultados = new Tabela(colunas, dados, num);
        resultados.setVisible(true);
        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.add(resultados);
        panel.setVisible(true);
        panel.setLayout(new GridLayout(1, 1));        
        panel.validate();
        resultados.table.setColumnSelectionAllowed(true);
        return resultados;
    }

    private void settupTableDisplay(){
        table.setAutoCreateRowSorter(true);
        table.setSize(bounds);
        table.setOpaque(false);
        table.setFont(font);
        table.setRowHeight(27);
    }

    public Object[][] buscaValores(String texto) {
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

    public static Object[][] buscaValores(String[] colunas, Object[][] linhas, String texto) {
        Object[][] linhasTmp = null;
        List<Object[]> list = new ArrayList<Object[]>();
        for (Object[] o : linhas) {
            for (int i = 0; i < o.length; i++) {
                if (o[i] != null) {
                    if (o[i].toString().toUpperCase().trim().contains(texto.toUpperCase().trim())) {
                        //System.out.println("Vlr " + o[i].toString());
                        list.add(o);
                        break;
                    }
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

    public static void adicionaMouseListener(Tabela resultados, MouseListener listener) {
        resultados.table.addMouseListener(listener);
    }

    public static void adicionaMouseListener(MouseListener listener) {
        table.addMouseListener(listener);
    }

    /**
     * says whether JTable is sortable.
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

    /**
     * set JTable sortable.
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
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] colunas = {"Demanda", "Tipo", "Status", "Resumo"};
        Object[][] linha = {{"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}};
        int[] num = {1, 2};
        Tabela tb = new Tabela(colunas, linha, num);
        tb.setVisible(true);

        frame.add(tb);
        frame.setVisible(true);
        frame.pack();
    }
}
