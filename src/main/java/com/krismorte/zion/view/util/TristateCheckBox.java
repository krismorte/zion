/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.util;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ActionMapUIResource;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public final class TristateCheckBox extends JCheckBox {
    // Listener on model changes to maintain correct focusability

    private final ChangeListener enableListener =
            new ChangeListener() {

                public void stateChanged(ChangeEvent e) {
                    TristateCheckBox.this.setFocusable(
                            getModel().isEnabled());
                }
            };

    public TristateCheckBox(String text) {
        this(text, null, TristateState.DESELECTED);
    }

    public TristateCheckBox(String text, Icon icon,
            TristateState initial) {
        super(text, icon);

        //Set default single model
        setModel(new TristateButtonModel(initial));

        // override action behaviour
        super.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                TristateCheckBox.this.iterateState();
            }
        });
        ActionMap actions = new ActionMapUIResource();
        actions.put("pressed", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                TristateCheckBox.this.iterateState();
            }
        });
        actions.put("released", null);
        SwingUtilities.replaceUIActionMap(this, actions);
    }

    // Next two methods implement new API by delegation to model
    public void setIndeterminate() {
        getTristateModel().setIndeterminate();
    }

    public boolean isIndeterminate() {
        return getTristateModel().isIndeterminate();
    }

    public TristateState getState() {
        return getTristateModel().getState();
    }

    public void setState(TristateState state) {
        getTristateModel().setState(state);
    }

    //Overrides superclass method
    public void setModel(ButtonModel newModel) {
        super.setModel(newModel);

        //Listen for enable changes
        if (model instanceof TristateButtonModel) {
            model.addChangeListener(enableListener);
        }
    }

    //Empty override of superclass method
    public void addMouseListener(MouseListener l) {
    }

    // Mostly delegates to model
    private void iterateState() {
        //Maybe do nothing at all?
        if (!getModel().isEnabled()) {
            return;
        }

        grabFocus();

        // Iterate state
        getTristateModel().iterateState();

        // Fire ActionEvent
        int modifiers = 0;
        AWTEvent currentEvent = EventQueue.getCurrentEvent();
        if (currentEvent instanceof InputEvent) {
            modifiers = ((InputEvent) currentEvent).getModifiers();
        } else if (currentEvent instanceof ActionEvent) {
            modifiers = ((ActionEvent) currentEvent).getModifiers();
        }
        fireActionPerformed(new ActionEvent(this,
                ActionEvent.ACTION_PERFORMED, getText(),
                System.currentTimeMillis(), modifiers));
    }

    //Convenience cast
    public TristateButtonModel getTristateModel() {
        return (TristateButtonModel) super.getModel();
    }
}
