package com.krismorte.zion.sql;

import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.ServerCredential;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class OracleTest extends DefaultOperationTest{

    @Container
    private static final OracleContainer oracleContainer = new OracleContainer("wnameless/oracle-xe-11g-r2")
            .withUsername("system")
            .withPassword("oracle");
    ServerCredential serverCredential;
    ConnectionFactory connectionFactory;
    Server oracle;

    @BeforeEach
    void setUp() throws Exception{
        oracleContainer.addExposedPort(1521);
        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection open(Server server) throws Exception {
                Class.forName(oracleContainer.getDriverClassName());
                return DriverManager.getConnection(oracleContainer.getJdbcUrl(), oracleContainer.getUsername(),oracleContainer.getPassword());
            }
        };
        serverCredential = new ServerCredential(oracleContainer.getContainerIpAddress(),oracleContainer.getFirstMappedPort().toString(),
                SupportedDatabases.ORACLE,"XE",oracleContainer.getUsername(),oracleContainer.getPassword());
    }

    @Test
    void runCommand() throws Exception{
        oracle =  new Oracle(connectionFactory,serverCredential);
        SQLResult sqlResult = oracle.runCommand("selet 1");
        assertNull(sqlResult.coluna);
        assertNotNull(sqlResult.sqlErro);
        sqlResult = oracle.runCommand("SELECT USERNAME FROM ALL_USERS  WHERE  USERNAME IN ('SYS','SYSTEM')");
        assertNotNull(sqlResult.coluna);
        assertEquals(2,sqlResult.linhas.length);

    }

    @Test
    void getJdbcConnection() throws Exception{
        oracle =  new Oracle(connectionFactory,serverCredential);
        assertNotNull(oracle.getJdbcConnection());
    }

    @Test
    void testToString()  throws Exception{
        oracle =  new Oracle(connectionFactory,serverCredential);
        assertEquals(oracleContainer.getContainerIpAddress(),oracle.toString());
    }

    @Test
    void getDefaultDatabaseName() throws Exception {
        oracle =  new Oracle(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(oracle),"SELECT sys_context('userenv','instance_name') FROM dual");
        assertEquals(result,oracle.getDefaultDatabaseName());
    }

    @Test
    void getVersionQuery()  throws Exception{
        oracle =  new Oracle(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(oracle),oracle.getVersionQuery());
        assertEquals(result,oracle.getVersion());
    }

}