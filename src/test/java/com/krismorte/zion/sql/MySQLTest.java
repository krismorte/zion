package com.krismorte.zion.sql;

import com.krismorte.zion.model.*;
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
        assertNull(sqlResult.columns);
        assertNotNull(sqlResult.sqlError);
        sqlResult = mySQL.runCommand("SELECT schema_name FROM information_schema.schemata where schema_name in ('mysql','information_schema')");
        assertNotNull(sqlResult.columns);
        assertEquals(2,sqlResult.rows.length);

    }

    @Test
    void createRemoveUser() throws Exception{
        mySQL =  new MySQL(connectionFactory,serverCredential);
        SQLResult sqlResult = mySQL.runCommand("create user "+userTest+" identified by '45jk45uiJ'");
        sqlResult = mySQL.runCommand("SELECT count(1) FROM mysql.user where user ='"+userTest+"'");
        assertEquals("1",sqlResult.rows[0][0]);


        sqlResult = mySQL.runCommand("drop user "+userTest);
        sqlResult = mySQL.runCommand("SELECT count(1) FROM mysql.user where user ='"+userTest+"'");
        assertEquals("0",sqlResult.rows[0][0]);
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