/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.model;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class SQLResult {

    public String[] coluna;
    public Object[][] linhas;
    public boolean eTexto = false;
    public String texto = "";
    public SQLError sqlErro = null;
}
