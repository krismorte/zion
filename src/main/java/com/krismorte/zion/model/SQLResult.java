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

    public String[] columns;
    public Object[][] rows;
    public boolean isText = false;
    public String text = "";
    public SQLError sqlError = null;
}
