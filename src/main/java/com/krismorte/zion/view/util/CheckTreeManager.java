/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class CheckTreeManager extends MouseAdapter implements TreeSelectionListener {

    private CheckTreeSelectionModel selectionModel;
    private JTree tree = new JTree();
    int hotspot = new JCheckBox().getPreferredSize().width;
    private CheckTreeCellRenderer checkTreeCellRender;
    private List<DefaultMutableTreeNode> listSelectedLeafNodes = new ArrayList<DefaultMutableTreeNode>();

    public CheckTreeManager(JTree tree) {
        this.tree = tree;
        selectionModel = new CheckTreeSelectionModel(tree.getModel());
        checkTreeCellRender = new CheckTreeCellRenderer(tree.getCellRenderer(), selectionModel);
        tree.setCellRenderer(checkTreeCellRender);
        tree.addMouseListener(this);
        selectionModel.addTreeSelectionListener(this);
    }

    public DefaultMutableTreeNode findNode(String nome) {
        DefaultMutableTreeNode node = null;
        DefaultMutableTreeNode m_rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        Enumeration e = m_rootNode.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            if (nome.equals(node.getUserObject().toString())) {
                break;
            }
        }
        return node;
    }

    public void mouseClicked(MouseEvent me) {
        TreePath path = tree.getPathForLocation(me.getX(), me.getY());
        if (path == null) {
            return;
        }
        if (me.getX() > tree.getPathBounds(path).x + hotspot) {
            return;
        }

        boolean selected = selectionModel.isPathSelected(path, true);
        selectionModel.removeTreeSelectionListener(this);


        try {
            if (selected) {
                removeSelecedLeafNodes((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
                //System.out.println("Tamanho Remove " + getListSelectedLeafNodes().size());
                selectionModel.removeSelectionPath(path);
            } else {
                appendSelecedLeafNodes((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
                //System.out.println("Tamanho add " + getListSelectedLeafNodes().size());
                selectionModel.addSelectionPath(path);
            }
        } finally {
            selectionModel.addTreeSelectionListener(this);
            tree.treeDidChange();
        }
    }

    private boolean appendSelecedLeafNodes(DefaultMutableTreeNode node) {
        //System.out.println(node.toString());
        boolean result = true;
        if (node.isLeaf()) {
            if (!getListSelectedLeafNodes().contains(node)) {
                getListSelectedLeafNodes().add(node);
                //System.out.println("no " + node.toString());
                result = false;
            } else {
                result = false;
            }

        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                result = appendSelecedLeafNodes((DefaultMutableTreeNode) node.getChildAt(i));
            }
        }
        return result;
    }

    private boolean removeSelecedLeafNodes(DefaultMutableTreeNode node) {
        //System.out.println(node.toString());
        boolean result = true;
        if (node.isLeaf()) {
            getListSelectedLeafNodes().remove(node);
            //System.out.println("no " + node.toString());
            result = false;
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                result = removeSelecedLeafNodes((DefaultMutableTreeNode) node.getChildAt(i));
            }
        }
        return result;
    }

    public void clearSelections() {
        listSelectedLeafNodes.clear();
        selectionModel.clearSelection();
    }

    /*public CheckTreeSelectionModel getSelectionModel() {
    return selectionModel;
    }*/
    public void valueChanged(TreeSelectionEvent e) {
        tree.treeDidChange();
    }

    /**
     * @return the listSelectedLeafNodes
     */
    public List<DefaultMutableTreeNode> getListSelectedLeafNodes() {
        return listSelectedLeafNodes;
    }
}
