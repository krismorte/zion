/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ConnectionFactory {

    public static int TIPO_CONEXAO;
    public static final int MSSQL = 1;
    public static final int MySQL = 2;
    public static final int POSTGRESQL = 3;
    public static final int H2 = 4;
    public static String ERROR_MSG;
    private static final String DRIVEMSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
   // private static final String DRIVEMySQL = "com.mysql.jdbc.Driver";
   private static final String DRIVEMySQL = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVEPOSTGRESQL = "org.postgresql.Driver";
    private static final String DRIVEH2 = "org.h2.Driver";

    public static Connection connectBD(String servidor, String base, String usario, String senha) throws Exception {
        return abre(MSSQL, servidor, base, usario, senha);
    }

    public static Connection abre(int tipo, String srv, String bd, String usuario, String senha) throws Exception {
        TIPO_CONEXAO = tipo;
        if (tipo == MSSQL) {
            Class.forName(DRIVEMSSQL);
            return DriverManager.getConnection("jdbc:sqlserver://" + srv + ";databaseName=" + bd + ";", usuario, senha);
        } else if (tipo == MySQL) {
            Class.forName(DRIVEMySQL);
            return DriverManager.getConnection("jdbc:mysql://" + srv + ":3306/" + bd, usuario, senha);
        } else if (tipo == POSTGRESQL) {
            Class.forName(DRIVEPOSTGRESQL);
            return DriverManager.getConnection("jdbc:postgresql://" + srv + ":5432/" + bd, usuario, senha);
        } else if (tipo == H2) {
            Class.forName(DRIVEH2);
            return DriverManager.getConnection("jdbc:h2:tcp://" + srv + "//" + bd, usuario, senha);
        } else {
            ERROR_MSG = "Conexao: problemas na conexão.";
            JOptionPane.showMessageDialog(null, "Utilitarios.dao :" + ERROR_MSG);
            return null;
        }
        /*
        Class.forName(tipo);
        //return DriverManager.getConnection("jdbc:sqlserver://d001bdp23;databaseName=Dsup999;", "S999Web", "S999@123");
        //return DriverManager.getConnection("jdbc:sqlserver://d001bdp28;databaseName=mysql;", "root", "indio5896");
        return DriverManager.getConnection("jdbc:sqlserver://"+srv+";databaseName="+bd+";", usuario, senha);
         * */
    }

    public static String dateFucntion() {
        if (TIPO_CONEXAO == MSSQL) {
            return "getDate()";
        } else if (TIPO_CONEXAO == MySQL) {
            return "now()";
        } else if (TIPO_CONEXAO == POSTGRESQL) {
            return "now()";
        } else if (TIPO_CONEXAO == H2) {
            return "now()";
        } else {
            return "now()";
        }
    }

    public static void fecha(Connection con, PreparedStatement comando) throws Exception {
        con.close();
        comando.close();
    }
}
