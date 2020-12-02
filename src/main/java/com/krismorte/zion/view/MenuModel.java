/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class MenuModel extends JMenuBar implements InternationalizedUI {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    private JMenu menuFile;
    private JMenu menuReport;
    private JMenu menuSystem;
    public JMenuItem itemExit;
    public JMenuItem itemServers;
    public JMenuItem itemScripts;
    public JMenuItem itemParameters;
    public JMenuItem itemVersion;
    private JMenuItemEvents events = new JMenuItemEvents();

    public MenuModel() {
        initMenus();
        itemExit.addActionListener(events);
        itemServers.addActionListener(events);
        itemScripts.addActionListener(events);
        itemParameters.addActionListener(events);
        itemVersion.addActionListener(events);

        menuFile.add(itemExit);
        menuReport.add(itemServers);
        menuReport.add(itemScripts);
        menuSystem.add(itemParameters);
        menuSystem.add(itemVersion);

        add(menuFile);
        add(menuReport);
        add(menuSystem);
    }

    private void initMenus() {
        menuFile = new JMenu(getStringI18n("FILE"));
        menuReport = new JMenu(getStringI18n("REPORT"));
        menuSystem = new JMenu(getStringI18n("SYSTEM"));
        itemExit = new JMenuItem(getStringI18n("EXIT"));
        itemServers = new JMenuItem(getStringI18n("SERVERS"));
        itemScripts = new JMenuItem(getStringI18n("SCRIPTS"));
        itemParameters = new JMenuItem(getStringI18n("SYSTEM_PARAM"));
        itemVersion = new JMenuItem(getStringI18n("SYSTEM_VERSION"));
    }

    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }

    public class JMenuItemEvents implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(itemExit.getText())) {
                System.exit(0);

            } else if (e.getActionCommand().equals(itemVersion.getText())) {
                JOptionPane.showMessageDialog(null, getStringI18n("SYSTEM_MSG"), getStringI18n("SYSTEM_VERSION"), JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getActionCommand().equals(itemParameters.getText())) {
                TelaParametros par = new TelaParametros(null, true);
                par.setVisible(true);
            }
        }
    }

}
