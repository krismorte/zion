/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultMutableTreeNode;
import com.krismorte.zion.model.Server;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class MainScreen extends JPanel implements SelectServers {

    private static Logger logger = LoggerFactory.getLogger(MainScreen.class);
    private JEditorPane htmlPane;
    private JSplitPane splitPane;
    private operationPanel panelLateral;
    private int splitPosition = 400;
    private List<Server> servers = new ArrayList<Server>();
    private TreeView treeView;
    private JFrame mainFrame;

    public MainScreen() {
        this.setLayout(new GridLayout(1, 2));
        treeView = new TreeView();

        JScrollPane treeScroll = new JScrollPane(treeView.getTree());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setTopComponent(treeScroll);

        splitPane.setPreferredSize(new Dimension(1250, 700));
        panelLateral = new operationPanel(this);
        splitPane.setBottomComponent(panelLateral);
        splitPane.setDividerLocation(splitPosition);
        add(splitPane);
    }

    @Override
    public List<Server> getServers() {
        servers.clear();
        List<DefaultMutableTreeNode> listSelectedLeafNodes = treeView.getSelectedLeafs();
        if (listSelectedLeafNodes.size() > 0) {
            for (DefaultMutableTreeNode n : listSelectedLeafNodes) {
                if(n.getUserObject() instanceof Server){
                    servers.add((Server) n.getUserObject());
                }
            }
            return servers;
        } else {
            return servers;
        }
    }

    public void configure(){
        MenuModel menu = new MenuModel();
        mainFrame = new JFrame();
        //frame.setIconImage(icon);
        mainFrame.setTitle("Zion - sql command distributor!");
        mainFrame.addWindowListener(
                new WindowAdapter() {

                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        mainFrame.getContentPane().add(new MainScreen());
        mainFrame.setJMenuBar(menu);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.pack();

    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public static void main(String[] args) {

        MainScreen mainScreen=new MainScreen();
        mainScreen.configure();
        mainScreen.getMainFrame().setVisible(true);
    }
}
