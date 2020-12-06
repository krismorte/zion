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

    public int errorCode;
    public String errorState;
    public String errorMessage;

    public String getRaisedError() {
        return "" + errorCode + " " + errorState + errorMessage;
        //Msg 50000, Level 16, State 1, Line 1
    }
}
