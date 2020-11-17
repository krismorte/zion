package com.krismorte.zion.sql;

import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.ServerCredential;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ConnectionFactory;
import com.krismorte.zion.view.service.ConnectionFactoryImpl;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class SQLServerTest extends DefaultOperationTest{

    @Container
    private static final  MSSQLServerContainer mssqlserver = new MSSQLServerContainer();
    ConnectionFactory connectionFactory;
    ServerCredential serverCredential;
    Server sqlServer;


    @BeforeEach
    void setUp() {
        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection open(Server server) throws Exception {
                Class.forName(mssqlserver.getDriverClassName());
                return DriverManager.getConnection(mssqlserver.getJdbcUrl(), mssqlserver.getUsername(),mssqlserver.getPassword());
            }
        };
        serverCredential = new ServerCredential(mssqlserver.getContainerIpAddress(),mssqlserver.getFirstMappedPort().toString()
                ,SupportedDatabases.MSSQL,"master",mssqlserver.getUsername(),mssqlserver.getPassword());
    }

    @Test
    void runCommand() throws Exception{
        sqlServer =  new SQLServer(connectionFactory,serverCredential);
        SQLResult sqlResult = sqlServer.runCommand("selet 1");
        assertNull(sqlResult.coluna);
        assertNotNull(sqlResult.sqlErro);
        sqlResult = sqlServer.runCommand("select name from sysdatabases where name in ('master','model')");
        assertNotNull(sqlResult.coluna);
        assertEquals(2,sqlResult.linhas.length);
    }

    @Test
    void createRemoveUser() throws Exception{
        sqlServer =  new SQLServer(connectionFactory,serverCredential);
        SQLResult sqlResult = sqlServer.runCommand("create login "+userTest+" with password ='45jk45uiJ'");
        sqlResult = sqlServer.runCommand("SELECT count(1) FROM master..syslogins where name ='"+userTest+"'");
        assertEquals("1",sqlResult.linhas[0][0]);


        sqlResult = sqlServer.runCommand("drop login "+userTest);
        sqlResult = sqlServer.runCommand("SELECT count(1) FROM master..syslogins where name ='"+userTest+"'");
        assertEquals("0",sqlResult.linhas[0][0]);
    }

    @Test
    void getJdbcConnection() throws Exception{
        sqlServer =  new SQLServer(connectionFactory,serverCredential);
        assertNotNull(sqlServer.getJdbcConnection());
    }

    @Test
    void testToString() throws Exception{
        sqlServer =  new SQLServer(connectionFactory,serverCredential);
        assertEquals(mssqlserver.getContainerIpAddress(),sqlServer.toString());
    }

    @Test
    void getDefaultDatabaseName() throws Exception{
        sqlServer =  new SQLServer(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(sqlServer),"select db_name()");
        assertEquals(result,sqlServer.getDefaultDatabaseName());
    }

    @Test
    void getVersionQuery() throws Exception{
        sqlServer =  new SQLServer(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(sqlServer),sqlServer.getVersionQuery());
        assertEquals(result,sqlServer.getVersion());
    }

}