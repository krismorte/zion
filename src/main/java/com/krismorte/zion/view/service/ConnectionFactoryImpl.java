/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.service;

import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ConnectionFactoryImpl implements ConnectionFactory {

    public static int TIPO_CONEXAO;

    public static String ERROR_MSG;
    private static final String DRIVEMSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
   private static final String DRIVEMySQL = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVEPOSTGRESQL = "org.postgresql.Driver";
    private static final String DRIVEH2 = "org.h2.Driver";



    @Override
    public Connection open(Server server) throws Exception {
        switch (server.getServerType()){
            case MSSQL:
                //Class.forName(DRIVEMSSQL);
                return DriverManager.getConnection("jdbc:sqlserver://" + server.getHost() + ":"+server.getPort()+";databaseName=" + server.getDatabase() + ";", server.getUser(), server.getPass());
            case MYSQL:
                //Class.forName(DRIVEMySQL);
                return DriverManager.getConnection("jdbc:mysql://" + server.getHost() + ":"+server.getPort()+"/" + server.getDatabase(), server.getUser(), server.getPass());
            case POSTGRES:
                //Class.forName(DRIVEPOSTGRESQL);
                return DriverManager.getConnection("jdbc:postgresql://" + server.getHost() + ":"+server.getPort()+"/" + server.getDatabase(), server.getUser(), server.getPass());
            case ORACLE:
                //Class.forName(DRIVEPOSTGRESQL);
                return DriverManager.getConnection("jdbc:oracle:thin:@"+server.getHost()+":"+server.getPort()+":"+server.getDatabase()+"", server.getUser(), server.getPass());
            default:
                return null;
            }
        }
}
