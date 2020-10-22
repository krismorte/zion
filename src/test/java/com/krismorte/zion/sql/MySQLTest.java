package com.krismorte.zion.sql;

import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.ServerCredential;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class MySQLTest extends DefaultOperationTest{

    @Container
    private static final  MySQLContainer mySQLContainer = new MySQLContainer().withDatabaseName("mysql");
    ServerCredential serverCredential;
    ConnectionFactory connectionFactory;
    Server mySQL;

    @BeforeEach
    void setUp() throws Exception{
        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection open(Server server) throws Exception {
                Class.forName(mySQLContainer.getDriverClassName());
                return DriverManager.getConnection(mySQLContainer.getJdbcUrl(), mySQLContainer.getUsername(),mySQLContainer.getPassword());
            }
        };
        serverCredential = new ServerCredential(mySQLContainer.getContainerIpAddress(),mySQLContainer.getFirstMappedPort().toString(),
                SupportedDatabases.MYSQL,"mysql",mySQLContainer.getUsername(),mySQLContainer.getPassword());
    }

    @Test
    void runCommand() throws Exception{
        mySQL =  new MySQL(connectionFactory,serverCredential);
        SQLResult sqlResult = mySQL.runCommand("selet 1");
        assertNull(sqlResult.coluna);
        assertNotNull(sqlResult.sqlErro);
        sqlResult = mySQL.runCommand("SELECT schema_name FROM information_schema.schemata where schema_name in ('mysql','information_schema')");
        assertNotNull(sqlResult.coluna);
        assertEquals(2,sqlResult.linhas.length);

    }

    @Test
    void getJdbcConnection() throws Exception{
        mySQL =  new MySQL(connectionFactory,serverCredential);
        assertNotNull(mySQL.getJdbcConnection());
    }

    @Test
    void testToString()  throws Exception{
        mySQL =  new MySQL(connectionFactory,serverCredential);
        assertEquals(mySQLContainer.getContainerIpAddress(),mySQL.toString());
    }

    @Test
    void getDefaultDatabaseName() throws Exception {
        mySQL =  new MySQL(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(mySQL),"select DATABASE()");
        assertEquals(result,mySQL.getDefaultDatabaseName());
    }

    @Test
    void getVersionQuery()  throws Exception{
        mySQL =  new MySQL(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(mySQL),mySQL.getVersionQuery());
        assertEquals(result,mySQL.getVersion());
    }

}