/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.util;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public final class LimpaCampos {

    public LimpaCampos() {
    }
//https://github.com/LGoodDatePicker/LGoodDatePicker/releases

    /**
     * Clear all components in the JFrame.
     *
     * @param JFrame that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCampos(JFrame frame) {

        Component componentes[] = ((JPanel) frame.getContentPane()).getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCampos((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCampos((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCampos((JScrollBar) c);
            }
        }
        return true;

    }

    /**
     * Clear all components in the JPanel.
     *
     * @param JDialog that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCampos(JDialog dialog) {

        Component componentes[] = ((JPanel) dialog.getContentPane()).getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCampos((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCampos((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCampos((JScrollBar) c);
            }
        }
        return true;
    }

    /**
     * Clear all components in the JInternalFrame.
     *
     * @param JInternalFrame that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCampos(JInternalFrame internal) {

        Component componentes[] = ((JPanel) internal.getContentPane()).getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCampos((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCampos((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCampos((JScrollBar) c);
            }
        }
        return true;
    }

    /**
     * Clear all components in the JPanel.
     *
     * @param JPanel that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCampos(JPanel panel) {
        Component componentes[] = panel.getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCampos((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCampos((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCampos((JScrollBar) c);
            }
        }
        return true;

    }

    /**
     * Clear all components in the JScrollPane.
     *
     * @param JScrollPane that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCampos(JScrollPane panel) {
        Component componentes[] = panel.getViewport().getComponents();

        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCampos((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCampos((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCampos((JScrollBar) c);
            }
        }
        return true;

    }

    /**
     * Clear all components in the JScrollBar.
     *
     * @param JScrollBar that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCampos(JScrollBar panel) {
        Component componentes[] = panel.getComponents();

        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCampos((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCampos((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCampos((JScrollBar) c);
            }
        }
        return true;

    }

    /**
     * Clear all components in the JFrame. include JXDatePicker Object
     *
     * @param JFrame that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCamposX(JFrame frame) {

        Component componentes[] = ((JPanel) frame.getContentPane()).getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JXDatePicker) {
                ((JXDatePicker) c).setDate(null);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCamposX((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCamposX((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCamposX((JScrollBar) c);
            }
        }
        return true;

    }

    /**
     * Clear all components in the JPanel. include JXDatePicker Object
     *
     * @param JDialog that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCamposX(JDialog dialog) {

        Component componentes[] = ((JPanel) dialog.getContentPane()).getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JXDatePicker) {
                ((JXDatePicker) c).setDate(null);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCamposX((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCamposX((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCamposX((JScrollBar) c);
            }
        }
        return true;
    }

    /**
     * Clear all components in the JInternalFrame. include JXDatePicker Object
     *
     * @param JInternalFrame that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCamposX(JInternalFrame internal) {

        Component componentes[] = ((JPanel) internal.getContentPane()).getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCamposX((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCamposX((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCamposX((JScrollBar) c);
            }
        }
        return true;
    }

    /**
     * Clear all components in the JPanel. include JXDatePicker Object
     *
     * @param JPanel that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCamposX(JPanel panel) {
        Component componentes[] = panel.getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JXDatePicker) {
                ((JXDatePicker) c).setDate(null);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCamposX((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCamposX((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCamposX((JScrollBar) c);
            }
        }
        return true;

    }

    /**
     * Clear all components in the JScrollPane. include JXDatePicker Object
     *
     * @param JScrollPane that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCamposX(JScrollPane panel) {
        //Component componentes[] = panel.getComponents();
        Component componentes[] = panel.getViewport().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JXDatePicker) {
                ((JXDatePicker) c).setDate(null);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCamposX((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCamposX((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCamposX((JScrollBar) c);
            }
        }
        return true;

    }

    /**
     * Clear all components in the JScrollBar. include JXDatePicker Object
     *
     * @param JScrollBar that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean limpaCamposX(JScrollBar panel) {
        Component componentes[] = panel.getComponents();

        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JRadioButton) {
                if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                    ((JRadioButton) c).setSelected(true);
                }
            } else if (c instanceof JPanel) {
                limpaCampos((JPanel) c);
            } else if (c instanceof JScrollPane) {
                limpaCampos((JScrollPane) c);
            } else if (c instanceof JScrollBar) {
                limpaCamposX((JScrollBar) c);
            }
        }
        return true;

    }

}
