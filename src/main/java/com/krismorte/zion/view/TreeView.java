/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.repository.ServerRepository;
import com.krismorte.zion.service.GroupService;
import com.krismorte.zion.service.GroupServiceImpl;
import com.krismorte.zion.service.ServerService;
import com.krismorte.zion.service.ServerServiceImpl;
import com.krismorte.zion.view.repository.GroupRepositoryFileImpl;
import com.krismorte.zion.view.repository.ServerRepositoryFileImpl;
import com.krismorte.zion.view.util.CheckTreeManager;
import jdk.nashorn.internal.scripts.JO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class TreeView implements InternationalizedUI {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    private DefaultMutableTreeNode root;
    private JTree tree;
    private DefaultTreeModel modelTree;
    private CheckTreeManager checkTreeManager;
    private JPopupMenu menuFlutuantePasta = new JPopupMenu();
    private JPopupMenu menuFlutuanteArquivo = new JPopupMenu();
    private JMenuItem itemAddGrupo;
    private JMenuItem itemRefresh;
    private JMenuItem itemRenomearGrupo;
    private JMenuItem itemRemoverGrupo;
    private JMenuItem itemAddServidor;
    private JMenuItem itemRemoverServidor;
    private GroupService groupServiceImpl = new GroupServiceImpl(new GroupRepositoryFileImpl());
   private ServerService serverService = new ServerServiceImpl(new ServerRepositoryFileImpl());

    public TreeView() {
        iniciaMenuFlutuante();
        root = new DefaultMutableTreeNode(getStringI18n("SERVERS"));
        mountTree(root);
    }

    private void mountTree(DefaultMutableTreeNode top) {
        modelTree = new DefaultTreeModel(top);
        listNodes(top);

        tree = new JTree(modelTree);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        checkTreeManager = new CheckTreeManager(tree);

        tree.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int row = tree.getRowForLocation(e.getX(), e.getY());
                    if (row == -1) {
                        return;
                    }
                    tree.setSelectionRow(row);
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (node.isRoot()) {
                        itemAddServidor.setEnabled(false);
                        itemRemoverGrupo.setEnabled(false);
                        itemAddGrupo.setEnabled(true);
                        itemRefresh.setEnabled(true);
                        menuFlutuantePasta.show((JComponent) e.getSource(),
                                e.getX(), e.getY());
                    } else if (node.getUserObject() instanceof Server) {
                        menuFlutuanteArquivo.show((JComponent) e.getSource(),
                                e.getX(), e.getY());
                    } else {
                        itemAddServidor.setEnabled(true);
                        itemRemoverGrupo.setEnabled(true);
                        itemAddGrupo.setEnabled(false);
                        itemRefresh.setEnabled(false);
                        menuFlutuantePasta.show((JComponent) e.getSource(),
                                e.getX(), e.getY());
                    }
                }
            }

        });

    }

    public void listNodes(DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode node = null;
        for (Group g : groupServiceImpl.getAll()) {
            node = new DefaultMutableTreeNode(g.getName());
            for (Server server : serverService.getAllByGroup(g)) {
                DefaultMutableTreeNode ns = new DefaultMutableTreeNode(server);
                node.add(ns);
            }
            parent.add(node);
        }

    }

    private void iniciaMenuFlutuante() {
        itemAddGrupo = new JMenuItem(getStringI18n("ADD_GROUP"));
        itemRenomearGrupo = new JMenuItem(getStringI18n("RENAME_GROUP"));
        itemRemoverGrupo = new JMenuItem(getStringI18n("REMOVE_GROUP"));
        itemRefresh = new JMenuItem(getStringI18n("REFRESH"));
        itemAddServidor = new JMenuItem(getStringI18n("ADD_SERVER"));
        itemRemoverServidor = new JMenuItem(getStringI18n("REMOVE_SERVER"));
        itemAddGrupo.addActionListener(new EventosJMenuItem());
        itemAddServidor.addActionListener(new EventosJMenuItem());
        itemRenomearGrupo.addActionListener(new EventosJMenuItem());
        itemRemoverGrupo.addActionListener(new EventosJMenuItem());
        itemRemoverServidor.addActionListener(new EventosJMenuItem());
        itemRefresh.addActionListener(new EventosJMenuItem());

        menuFlutuantePasta.add(itemAddGrupo);
        menuFlutuantePasta.add(itemAddServidor);
        menuFlutuantePasta.add(itemRenomearGrupo);
        menuFlutuantePasta.add(itemRemoverGrupo);
        menuFlutuantePasta.add(itemRefresh);
        menuFlutuanteArquivo.add(itemRemoverServidor);

    }

    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }

    public class EventosJMenuItem implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals(itemAddGrupo.getText())) {
                newGroup();
            } else if (e.getActionCommand().equals(itemAddServidor.getText())) {
                newServer();
            } else if (e.getActionCommand().equals(itemRenomearGrupo.getText())) {
                renameGroup();
            } else if (e.getActionCommand().equals(itemRemoverGrupo.getText())) {
                removeGroup();
            } else if (e.getActionCommand().equals(itemRemoverServidor.getText())) {
                removeServer();
            } else if (e.getActionCommand().equals(itemRefresh.getText())) {
                modelTree.reload();
            }
        }
    }

    private void removeServer() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        Server server = (Server) node.getUserObject();
        serverService.remove(server);
        modelTree.removeNodeFromParent(node);
        modelTree.reload();
    }

    private void newServer() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        CadastroDeServidor cad = new CadastroDeServidor(null, node, modelTree);
        cad.setVisible(true);
        checkTreeManager.clearSelections();
    }

    public void newGroup() {
        String novoNome = JOptionPane.showInputDialog(null, getStringI18n("TYPE_NAME"), getStringI18n("ADD_GROUP"), JOptionPane.INFORMATION_MESSAGE);
        if (novoNome == null || novoNome.equals("")) {
            JOptionPane.showMessageDialog(null, getStringI18n("MANDATORY_VALUE"));
        } else {
            try {
                groupServiceImpl.create(new Group(novoNome));
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(novoNome);
                modelTree.insertNodeInto(newNode, (DefaultMutableTreeNode) tree.getModel().getRoot(), 0);
                //listaGrupos.add(novoNome);
                checkTreeManager.clearSelections();
                //logger.info("Grupo " + newNode.toString() + " adicionado com sucesso");
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
                //logger.error(ex.getMessage());
            }
        }
    }

    private void renameGroup() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        String fileName = JOptionPane.showInputDialog(null, getStringI18n("TYPE_NEW_NAME"), getStringI18n("RENAME_GROUP"), JOptionPane.INFORMATION_MESSAGE);
        try {
            groupServiceImpl.rename(new Group(node.toString()), fileName);
            modelTree.valueForPathChanged(tree.getSelectionPath(), fileName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }

    private void removeGroup() {
        int option = JOptionPane.showConfirmDialog(null, getStringI18n("MSG_CONF_REMOVE_GROUP"), getStringI18n("REMOVE_GROUP"), JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            try {
                groupServiceImpl.delete(new Group(node.toString()));
                modelTree.removeNodeFromParent(node);
                checkTreeManager.clearSelections();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
    }

    public JTree getTree() {
        return tree;
    }

    public List getSelectedLeafs() {
        return checkTreeManager.getListSelectedLeafNodes();
    }

}
