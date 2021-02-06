/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.ServerCredential;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.sql.MySQL;
import com.krismorte.zion.sql.Oracle;
import com.krismorte.zion.sql.Postgres;
import com.krismorte.zion.sql.SQLServer;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */

public class ServerFactory {


    public static Server newInstance(ConnectionFactory connectionFactory, ServerCredential credential) throws Exception{
        switch (credential.getServerType()){
            case MSSQL:
                return new SQLServer(connectionFactory,credential);
            case MYSQL:
                return new MySQL(connectionFactory,credential);
            case POSTGRES:
                return new Postgres(connectionFactory,credential);
            case ORACLE:
                return new Oracle(connectionFactory,credential);
            default:
                return null;
        }

    }
    
    public static Server newInstance(ConnectionFactory connectionFactory,String host,String port, SupportedDatabases serverType, String database, String user, String pass, String version, String description, Group group) throws Exception{
        switch (serverType){
            case MSSQL:
                return new SQLServer(connectionFactory,host,port, serverType, database, user, pass, version, description, group);
            case MYSQL:
                return new MySQL(connectionFactory,host,port, serverType, database, user, pass, version, description, group);
            case POSTGRES:
                return new Postgres(connectionFactory,host,port, serverType, database, user, pass, version, description, group);
            case ORACLE:
                return new Oracle(connectionFactory,host,port, serverType, database, user, pass, version, description, group);
            default:
                return null;
        }
    }
    
}
