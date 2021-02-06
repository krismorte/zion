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
public class ScreenUtil {


    public static void fullfilBox(JComboBox box, Object[] list) {
        box.removeAllItems();
        for (Object o : list) {
            box.addItem(o);
        }
    }


    public static void fullfilBox(JComboBox box, String first, Object[] list) {
        box.removeAllItems();
        box.addItem(first);
        for (Object o : list) {
            box.addItem(o);
        }

    }


    public static void fullfilBox(JComboBox box, Object first, Object[] list) {
        box.removeAllItems();
        for (Object o : list) {
            box.addItem(o);
        }
        box.setSelectedItem(first);
    }


    public static void initAbstractButton(AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
    }


    public static void initAbstractButton(boolean selectFirst, AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        lstBtn[0].setSelected(selectFirst);
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
    }


    public static void initAbstractButton(AbstractButton selectedButton, boolean doClick, AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        grp.add(selectedButton);
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
        selectedButton.doClick();
    }


    public static int valueRadioToInt(JRadioButton btn) {
        if (btn.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }


    public static int valueCheckToInt(JCheckBox btn) {
        if (btn.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }


    public static void valueRadioForSex(JRadioButton btnMan, JRadioButton btnWon, String value) {
        if (value.toUpperCase().equals("F")) {
            btnWon.setSelected(true);
        } else if (value.toUpperCase().equals("M")) {
            btnMan.setSelected(true);
        }
    }


    public static String valueRadioForSex(JRadioButton btnMan, JRadioButton btnWon) {
        if (btnWon.isSelected()) {
            return "F";
        } else if (btnMan.isSelected()) {
            return "M";
        } else {
            return "E";
        }
    }


    public static boolean verifyEmptyFields(JComponent component, String errorMsg, JTextComponent... txt) {
        boolean answer = true;
        for (JTextComponent t : txt) {
            if (t.getText().equals("")) {
                answer = false;
            }
        }
        if (!answer) {
            JOptionPane.showMessageDialog(component, errorMsg);
        }
        return answer;
    }

    public static File getFilePath(String file) {

        javax.swing.JFileChooser fileName = new javax.swing.JFileChooser();

        if (file.equals("")) {
            fileName.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                fileName.setCurrentDirectory(f);
            } else {
                fileName.setSelectedFile(f);
            }
        }
        fileName.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);

        int answer = fileName.showSaveDialog(null);

        if (!(answer == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File arquivoNome = fileName.getSelectedFile();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public static File[] getFilesPath(String file) {

        javax.swing.JFileChooser fileName = new javax.swing.JFileChooser();

        if (file.equals("")) {
            fileName.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                fileName.setCurrentDirectory(f);
            } else {
                fileName.setSelectedFile(f);
            }
        }

        fileName.setMultiSelectionEnabled(true);
        fileName.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        int answer = fileName.showSaveDialog(null);

        if (!(answer == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File[] arquivoNome = fileName.getSelectedFiles();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public static File getDirectoryPath(String file) {

        javax.swing.JFileChooser fileName = new javax.swing.JFileChooser();
        if (file.equals("")) {
            fileName.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                fileName.setCurrentDirectory(f);
            } else {
                fileName.setSelectedFile(f);
            }
        }
        fileName.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        int answer = fileName.showSaveDialog(null);

        if (!(answer == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File arquivoNome = fileName.getSelectedFile();

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
