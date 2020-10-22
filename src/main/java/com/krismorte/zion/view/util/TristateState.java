/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krismorte.zion.view.util;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public enum TristateState {
    SELECTED {
        public TristateState next() {
            return INDETERMINATE;
        }
    },
    INDETERMINATE {
        public TristateState next() {
            return DESELECTED;
        }
    },
    DESELECTED {
        public TristateState next() {
            return SELECTED;
        }
    };

    public abstract TristateState next();
}