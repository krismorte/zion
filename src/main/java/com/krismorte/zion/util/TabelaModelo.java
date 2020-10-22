/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.util;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class TabelaModelo extends AbstractTableModel {

    private boolean DEBUG = false;
    private String[] columnNames = null;
    private Object[][] data = null;
    private int[] colEditavies;

    /** Create a new instance of  TabelaModelo
     *
     * @param colunas a String[]
     * @param linhas a Object[][]
     */
    public TabelaModelo(String[] colunas, Object[][] linhas) {
        columnNames = colunas;
        data = linhas;
    }

    public TabelaModelo(String[] colunas, Object[][] linhas, int[] _colEditaveis) {
        columnNames = colunas;
        data = linhas;
        colEditavies = _colEditaveis;
    }

    public Class getColumnClass(int c) {
        if (getValueAt(0, c) == null) {
            return Object.class;
        } else {
            return getValueAt(0, c).getClass();
        }

    }/*
    @Override
    public Class getColumnClass(int column) {
    Class returnValue;
    if ((column >= 0) && (column < getColumnCount())) {
    returnValue = getValueAt(0, column).getClass();
    } else {
    returnValue = Object.class;
    }
    return returnValue;
    }*/


    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (colEditavies == null) {
            return false;
        } else {
            boolean resp = false;
            verifica:
            for (int i = 0; i < colEditavies.length; i++) {
                if (col == colEditavies[i]) {
                    resp = true;
                    break verifica;
                } else {
                    resp = false;
                }
            }
            return resp;
        }
    }

    /**Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of " + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
