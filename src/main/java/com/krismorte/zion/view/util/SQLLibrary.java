/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.util;

import java.awt.Color;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
@Getter 
@Setter
public class SQLLibrary {

    private String[] reservedWords;
    private Color color;

    public SQLLibrary(String[] _reservedWords, Color _color) {
        reservedWords = _reservedWords;
        color = _color;
    }

    public String toStringRegexSearch() {
        String regexOr = "";
        for (int i = 0; i < reservedWords.length; i++) {
            if (i == 0) {
                regexOr = reservedWords[i];
            } else {
                regexOr += "|" + reservedWords[i];
            }
        }
        return regexOr;
    }

}
 