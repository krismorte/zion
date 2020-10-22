/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.util;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.text.JTextComponent;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class TelaUtil {

    /**
     * Method to fulfill JComboBox
     *
     * @box a instance of JComboBox to fulfill
     * @lista a Object[] list with data
     */
    public static void preencheBox(JComboBox box, Object[] lista) {
        box.removeAllItems();
        for (Object o : lista) {
            box.addItem(o);
        }
    }

    /**
     * Method to fulfill JComboBox
     *
     * @box a instance of JComboBox to fulfill
     * @primeiro a String to put first
     * @lista a Object[] list with data
     */
    public static void preencheBox(JComboBox box, String primeiro, Object[] lista) {
        box.removeAllItems();
        box.addItem(primeiro);
        for (Object o : lista) {
            box.addItem(o);
        }

    }

    /**
     * Method to fulfill JComboBox
     *
     * @box a instance of JComboBox to fulfill
     * @primeiro a Object to put select
     * @lista a Object[] list with data
     */
    public static void preencheBox(JComboBox box, Object primeiro, Object[] lista) {
        box.removeAllItems();
        for (Object o : lista) {
            box.addItem(o);
        }
        box.setSelectedItem(primeiro);
    }

    /**
     * Method thats initiate Radio and Check Button
     *
     * @lstBtn a list of AbstractButton to initiate
     */
    public static void iniciaAbstractButton(AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
    }

    /**
     * Method thats initiate Radio and Check Button
     *
     * @param selecFirst
     * @param lstBtn
     */
    public static void iniciaAbstractButton(boolean selecFirst, AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        lstBtn[0].setSelected(true);
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
    }

    /**
     * Method thats initiate Radio and Check Button
     *
     * @param selectedButton
     * @param doClick
     * @param lstBtn
     */
    public static void iniciaAbstractButton(AbstractButton selectedButton, boolean doClick, AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        grp.add(selectedButton);
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
        selectedButton.doClick();
    }

    /**
     * Return 1 if JRadioButton is selected
     *
     * @param btn
     * @return
     */
    public static int valueRadioToInt(JRadioButton btn) {
        if (btn.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Return 1 if JCheckBox is selected
     *
     * @param btn
     * @return
     */
    public static int valueCheckToInt(JCheckBox btn) {
        if (btn.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Verify if btnMan or btnWon match the the value. The JRadioButton
     * represents Male and Female sex respective
     *
     * @param btnMan
     * @param btnWon
     * @param value
     */
    public static void valueRadioForSex(JRadioButton btnMan, JRadioButton btnWon, String value) {
        if (value.toUpperCase().equals("F")) {
            btnWon.setSelected(true);
        } else if (value.toUpperCase().equals("M")) {
            btnMan.setSelected(true);
        }
    }

    /**
     * Return M if btnMan is selected and F to btnWon. The JRadioButton
     * represents Male and Female sex respective
     *
     * @param btnMan
     * @param btnWon
     * @return
     */
    public static String valueRadioForSex(JRadioButton btnMan, JRadioButton btnWon) {
        if (btnWon.isSelected()) {
            return "F";
        } else if (btnMan.isSelected()) {
            return "M";
        } else {
            return "E";
        }
    }

    /**
     * Verify is a JTextComponent is empty
     *
     * @param componente
     * @param errorMsg
     * @param txt
     * @return
     */
    public static boolean verifyEmpytFields(JComponent componente, String errorMsg, JTextComponent... txt) {
        boolean retorno = true;
        for (JTextComponent t : txt) {
            if (t.getText().equals("")) {
                retorno = false;
            }
        }
        if (!retorno) {
            JOptionPane.showMessageDialog(componente, errorMsg);
        }
        return retorno;
    }

    public static File getFilePath(String file) {

        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();

        if (file.equals("")) {
            arquivo.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                arquivo.setCurrentDirectory(f);
            } else {
                arquivo.setSelectedFile(f);
            }
        }
        arquivo.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);

        int resultadoArq = arquivo.showSaveDialog(null);

        if (!(resultadoArq == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File arquivoNome = arquivo.getSelectedFile();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public static File[] getFilesPath(String file) {

        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();

        if (file.equals("")) {
            arquivo.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                arquivo.setCurrentDirectory(f);
            } else {
                arquivo.setSelectedFile(f);
            }
        }

        arquivo.setMultiSelectionEnabled(true);
        arquivo.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        int resultadoArq = arquivo.showSaveDialog(null);

        if (!(resultadoArq == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File[] arquivoNome = arquivo.getSelectedFiles();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public static File getDirectoryPath(String file) {

        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();
        if (file.equals("")) {
            arquivo.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                arquivo.setCurrentDirectory(f);
            } else {
                arquivo.setSelectedFile(f);
            }
        }
        arquivo.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        int resultadoArq = arquivo.showSaveDialog(null);

        if (!(resultadoArq == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File arquivoNome = arquivo.getSelectedFile();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public static void addEventsToJMenuItens(JMenu menu, ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        for (JMenuItem i : itens) {
            i.addActionListener(events);
            menu.add(i);
        }
    }


    public static void addEventsToJMenuItens(JPopupMenu menu, ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        for (JMenuItem i : itens) {
            i.addActionListener(events);
            menu.add(i);
        }
    }

    public static void addEventsToJMenuItens(ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        for (JMenuItem i : itens) {
            i.addActionListener(events);
        }
    }
}
