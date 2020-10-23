/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.sql;

import com.krismorte.zion.model.*;
import com.krismorte.zion.service.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class SQLServer extends Server {

    public SQLServer(ConnectionFactory connectionFactory, ServerCredential credential) throws Exception {
        super(connectionFactory,credential);
    }

    public SQLServer(ConnectionFactory connectionFactory,String host,String port, SupportedDatabases serverType, String database, String user, String pass, String version, String description, Group group) {
        super(connectionFactory,host,port, serverType, database, user, pass, version, description, group);
    }


    @Override
    public SQLResult runCommand(String command) throws Exception {

        try {
            Connection con = getJdbcConnection();

            PreparedStatement cmd = con.prepareCall(command, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            cmd.setFetchSize(Parametros.row);
            cmd.executeUpdate();

            ResultSet set = cmd.getResultSet();

            buildResult(set);

            cmd.close();
            con.close();
            return getSqlResult();
        } catch (SQLException e) {
            buildError(e);
            e.printStackTrace();
            return getSqlResult();
        }
    }


    @Override
    public String toString() {
        return getHost();
    }

    @Override
    public String getDefaultDatabaseName() {
        return "master";
    }

    @Override
    public String getVersionQuery() {
        return "SELECT  cast(SERVERPROPERTY('productversion') as varchar)";
    }

    //    public Servidor select(String servidor, String base, String usario, String senha) throws Exception {
//        //try {
//        Servidor model = null;
//        Connection con = ConnectionFactory.connectBD(servidor, base, usario, senha);
//        String insert = "SELECT  cast(SERVERPROPERTY('productversion') as varchar),cast( SERVERPROPERTY ('productlevel')as varchar),cast( SERVERPROPERTY ('edition')as varchar)";
//        PreparedStatement cmd = con.prepareStatement(insert);
//        ResultSet set = cmd.executeQuery();
//        if (set.next()) {
//            //String nome, String usuario, String senha, String versao, String edicao, String sp, String descricao
//           // model = new Servidor(servidor, usario, senha, set.getString(1), set.getString(2), set.getString(3));
//        }
//        cmd.close();
//        con.close();
//        return model;
//
//    }
//    public SQLResult exec(String servidor, String base, String usario, String senha, String script) throws Exception {
//        SQLResult resultado = new SQLResult();
//
//        //try {
//        Connection con = ConnectionFactory.connectBD(servidor, base, usario, senha);
//
//        PreparedStatement cmd = con.prepareCall("set rowcount " + Parametros.row + ";\r\n" + script, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//
//        ResultSet set = cmd.executeQuery();
//        ResultSetMetaData meta = set.getMetaData();
//
//        List<String[]> lista = new ArrayList<String[]>();
//
//        while (set.next()) {
//            String[] lTmp = new String[meta.getColumnCount()];
//            for (int i = 1; i <= meta.getColumnCount(); i++) {
//                if (set.getObject(i) == null) {
//                    lTmp[i - 1] = "";
//                } else {
//                    lTmp[i - 1] = set.getObject(i).toString();
//                }
//            }
//            lista.add(lTmp);
//
//        }
//
//        resultado.coluna = new String[meta.getColumnCount()];
//
//        resultado.linhas = new Object[lista.size()][resultado.coluna.length];
//        for (int i = 1; i <= resultado.coluna.length; i++) {
//            if (meta.getColumnName(i).equals("")) {
//                resultado.coluna[i - 1] = "(Sem nome)";
//            } else {
//                resultado.coluna[i - 1] = meta.getColumnName(i);
//            }
//
//        }
//
//        int linha = 0;
//        for (String[] s : lista) {
//            for (int i = 1; i <= resultado.coluna.length; i++) {
//                //System.out.print(s[i - 1] + "; ");
//                resultado.linhas[linha][i - 1] = s[i - 1];
//            }
//            linha++;
//
//        }
//
//        cmd.close();
//        con.close();
//        return resultado;
//    }
}
