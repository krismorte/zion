/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.krismorte.zion.model.Script;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class MenuModel extends JMenuBar implements InternationalizedUI {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    private JMenu menuArv;
    private JMenu menuRel;
    private JMenu menuSis;
    public JMenuItem sair;
    public JMenuItem relServidores;
    public JMenuItem relScripts;
    public JMenuItem sisParamtetros;
    public JMenuItem sisVersao;
    private EventosJMenuItem events = new EventosJMenuItem();
    private MainScreen tela;

    public MenuModel() {
        //this.tela = tela;
        initMenus();
        sair.addActionListener(events);
        relServidores.addActionListener(events);
        relScripts.addActionListener(events);
        sisParamtetros.addActionListener(events);
        sisVersao.addActionListener(events);

        menuArv.add(sair);
        menuRel.add(relServidores);
        menuRel.add(relScripts);
        menuSis.add(sisParamtetros);
        menuSis.add(sisVersao);

        add(menuArv);
        add(menuRel);
        add(menuSis);
    }

    private void initMenus() {
        menuArv = new JMenu(getStringI18n("FILE"));
        menuRel = new JMenu(getStringI18n("REPORT"));
        menuSis = new JMenu(getStringI18n("SYSTEM"));
        sair = new JMenuItem(getStringI18n("EXIT"));
        relServidores = new JMenuItem(getStringI18n("SERVERS"));
        relScripts = new JMenuItem(getStringI18n("SCRIPTS"));
        sisParamtetros = new JMenuItem(getStringI18n("SYSTEM_PARAM"));
        sisVersao = new JMenuItem(getStringI18n("SYSTEM_VERSION"));
    }

    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }

    public class EventosJMenuItem implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(sair.getText())) {
                System.exit(0);

            } else if (e.getActionCommand().equals(sisVersao.getText())) {
                JOptionPane.showMessageDialog(null, getStringI18n("SYSTEM_MSG"), getStringI18n("SYSTEM_VERSION"), JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getActionCommand().equals(sisParamtetros.getText())) {
                TelaParametros par = new TelaParametros(null, true);
                par.setVisible(true);
            }
        }
    }

}
