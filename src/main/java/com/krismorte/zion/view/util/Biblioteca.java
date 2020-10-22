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
public class Biblioteca {

    private String[] palavrasReservadas;
    private Color cor;

    public Biblioteca(String[] _palavrasReservadas, Color _cor) {
        palavrasReservadas = _palavrasReservadas;
        cor = _cor;
    }

    public String toStringRegexSearch() {
        String regexOr = "";
        for (int i = 0; i < palavrasReservadas.length; i++) {
            if (i == 0) {
                regexOr = palavrasReservadas[i];
            } else {
                regexOr += "|" + palavrasReservadas[i];
            }
        }
        return regexOr;
    }

}
 