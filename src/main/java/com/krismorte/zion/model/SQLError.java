/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.model;
/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class SQLError {

    public int codigoErro;
    public String estadoErro;
    public String msgErro;

    public String getRaisError() {
        return "" + codigoErro + " " + estadoErro + msgErro;
        //Msg 50000, Level 16, State 1, Line 1
    }
}
