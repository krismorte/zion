/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.model;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.krismorte.zion.service.ConnectionFactory;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
@Getter
@Setter
public abstract class Server {

    private String host;
    private String port;
    private SupportedDatabases serverType;
    private String database;
    private String user;
    private String pass;
    private String version;
    private String description;
    private Group group;
    private SQLResult sqlResult;
    private ConnectionFactory connectionFactory;

    public Server(ConnectionFactory connectionFactory,ServerCredential credential) throws Exception {
        this.connectionFactory = connectionFactory;
        this.host = credential.getHost();
        this.port = credential.getPort();
        this.serverType = credential.getServerType();
        this.database = credential.getDatabase();
        this.user = credential.getUser();
        this.pass = credential.getPass();
        getInfo();
    }

    public Server(ConnectionFactory connectionFactory,String host,String port, SupportedDatabases serverType, String database, String user, String pass, String version, String description, Group group) {
        this.connectionFactory = connectionFactory;
        this.host = host;
        this.port = port;
        this.serverType = serverType;
        this.setDatabase(database);
        this.user = user;
        this.pass = pass;
        this.version = version;
        this.description = description;
        this.group = group;
    }

    public void getInfo() throws Exception {
        Connection con = getJdbcConnection();

        String query = getVersionQuery();
        PreparedStatement cmd = con.prepareStatement(query);
        ResultSet set = cmd.executeQuery();
        if (set.next()) {
            setVersion(set.getString(1));
        }
        cmd.close();
        con.close();
    }
    public abstract SQLResult runCommand(String command) throws Exception;
    public abstract String toString();
    public abstract String getDefaultDatabaseName();
    public abstract String getVersionQuery();


    public Connection getJdbcConnection() throws Exception {
        return connectionFactory.open(this);
    }

    public void buildResult(ResultSet set) throws Exception {

        if (set == null){
            set = new EmptyResultSet();
        }

        sqlResult = new SQLResult();
        ResultSetMetaData meta = set.getMetaData();
        List<String[]> lista = new ArrayList<String[]>();

        while (set.next()) {
            String[] lTmp = new String[meta.getColumnCount()];
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                if (set.getObject(i) == null) {
                    lTmp[i - 1] = "";
                } else {
                    lTmp[i - 1] = set.getObject(i).toString();
                }
            }
            lista.add(lTmp);

        }

        sqlResult.coluna = new String[meta.getColumnCount()];
        sqlResult.linhas = new Object[lista.size()][sqlResult.coluna.length];
        for (int i = 1; i <= sqlResult.coluna.length; i++) {
            if (meta.getColumnName(i).equals("")) {
                sqlResult.coluna[i - 1] = "(Sem nome)";
            } else {
                sqlResult.coluna[i - 1] = meta.getColumnName(i);
            }
        }

        int linha = 0;
        for (String[] s : lista) {
            for (int i = 1; i <= sqlResult.coluna.length; i++) {
                sqlResult.linhas[linha][i - 1] = s[i - 1];
            }
            linha++;
        }
    }

    public void buildError(SQLException e) {
        sqlResult = new SQLResult();
        SQLError erro = new SQLError();
        erro.codigoErro = e.getErrorCode();
        erro.estadoErro = e.getSQLState();
        erro.msgErro = e.getMessage();
        sqlResult.sqlErro = erro;
    }

    public String getDatabase() {
        return database== null? getDefaultDatabaseName() : database;
    }


}
