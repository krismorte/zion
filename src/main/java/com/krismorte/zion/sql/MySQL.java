/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.sql;

import com.krismorte.zion.model.*;
import com.krismorte.zion.service.ConnectionFactory;

import java.sql.*;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class MySQL extends Server {

    public MySQL(ConnectionFactory connectionFactory, ServerCredential credential) throws Exception {
        super(connectionFactory,credential);
    }

    public MySQL(ConnectionFactory connectionFactory,String host,String port, SupportedDatabases serverType, String database, String user, String pass, String version, String description, Group group) {
        super(connectionFactory,host,port, serverType, database, user, pass, version, description, group);
    }


    @Override
    public SQLResult runCommand(String command) throws Exception {

        try {
            Connection con = getJdbcConnection();

            PreparedStatement cmd = con.prepareCall(command, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            cmd.setFetchSize(ZionParameters.row);
            if (command.trim().toLowerCase().startsWith("select")){

                cmd.executeQuery();
            }else{
                cmd.executeUpdate();
            }

            ResultSet set = cmd.getResultSet();

            buildResult(set);

            cmd.close();
            con.close();
            return getSqlResult();
        }catch (SQLSyntaxErrorException e) {
            buildError(e);
            //e.printStackTrace();
            return getSqlResult();
        } catch (SQLException e) {
            buildError(e);
            //e.printStackTrace();
            return getSqlResult();
        }
    }


    @Override
    public String toString() {
        return getHost();
    }

    @Override
    public String getDefaultDatabaseName() {
        return "mysql";
    }

    @Override
    public String getVersionQuery() {
        return "SELECT VERSION()";
    }

}
