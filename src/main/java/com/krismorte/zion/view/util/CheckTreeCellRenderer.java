/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.util;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class CheckTreeCellRenderer extends JPanel implements TreeCellRenderer {

    private CheckTreeSelectionModel selectionModel;
    private TreeCellRenderer delegate;
    private TristateCheckBox checkBox = new TristateCheckBox("");

    public CheckTreeCellRenderer(TreeCellRenderer delegate, CheckTreeSelectionModel selectionModel) {
        this.delegate = delegate;
        this.selectionModel = selectionModel;
        setLayout(new BorderLayout());
        setOpaque(false);
        checkBox.setOpaque(false);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component renderer = delegate.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        TreePath path = tree.getPathForRow(row);
        if (path != null) {
            if (selectionModel.isPathSelected(path, true)) {
                checkBox.setState(TristateState.SELECTED);
            } else {
                checkBox.setState(TristateState.DESELECTED);
            }
        }
        removeAll();
        add(checkBox, BorderLayout.WEST);
        add(renderer, BorderLayout.CENTER);
        return this;
    }

    public TristateCheckBox getTristateCheckBox() {
        return checkBox;
    }
}
