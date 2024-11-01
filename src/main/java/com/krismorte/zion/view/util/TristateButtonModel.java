/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krismorte.zion.view.util;

import java.awt.event.ItemEvent;
import javax.swing.JToggleButton.ToggleButtonModel;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class TristateButtonModel extends ToggleButtonModel {
    private TristateState state = TristateState.DESELECTED;

    public TristateButtonModel(TristateState state) {
        setState(state);
    }

    public TristateButtonModel() {
        this(TristateState.DESELECTED);
    }

    public void setIndeterminate() {
        setState(TristateState.INDETERMINATE);
    }

    public boolean isIndeterminate() {
        return state == TristateState.INDETERMINATE;
    }

    // Overrides of superclass methods
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        // Restore state display
        displayState();
    }

    public void setSelected(boolean selected) {
        setState(selected ?
                TristateState.SELECTED : TristateState.DESELECTED);
    }

    // Empty overrides of superclass methods
    public void setArmed(boolean b) {
    }

    public void setPressed(boolean b) {
    }

    void iterateState() {
        setState(state.next());
    }

    public void setState(TristateState state) {
        //Set internal state
        this.state = state;
        displayState();
        if (state == TristateState.INDETERMINATE && isEnabled()) {
            // force the events to fire

            // Send ChangeEvent
            fireStateChanged();

            // Send ItemEvent
            int indeterminate = 3;
            fireItemStateChanged(new ItemEvent(
                    this, ItemEvent.ITEM_STATE_CHANGED, this,
                    indeterminate));
        }
    }

    private void displayState() {
        super.setSelected(state != TristateState.DESELECTED);
        super.setArmed(state == TristateState.INDETERMINATE);
        super.setPressed(state == TristateState.INDETERMINATE);
    }

    public TristateState getState() {
        return state;
    }
}