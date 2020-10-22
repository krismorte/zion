package com.krismorte.zion.sql;

import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.ServerCredential;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class PostgresTest extends DefaultOperationTest{

    @Container
    private static final PostgreSQLContainer pgsqlserver = new PostgreSQLContainer().withDatabaseName("postgres");
    ConnectionFactory connectionFactory;
    ServerCredential serverCredential;
    Server postgreSQL;


    @BeforeEach
    void setUp() {
        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection open(Server server) throws Exception {
                Class.forName(pgsqlserver.getDriverClassName());
                return DriverManager.getConnection(pgsqlserver.getJdbcUrl(), pgsqlserver.getUsername()
                        ,pgsqlserver.getPassword());
            }
        };
        serverCredential = new ServerCredential(pgsqlserver.getContainerIpAddress(),pgsqlserver.getFirstMappedPort().toString(),
                SupportedDatabases.POSTGRES,"postgres",pgsqlserver.getUsername(),pgsqlserver.getPassword());
    }

    @Test
    void runCommand() throws Exception{
        postgreSQL =  new Postgres(connectionFactory,serverCredential);
        SQLResult sqlResult = postgreSQL.runCommand("selet 1");
        assertNull(sqlResult.coluna);
        assertNotNull(sqlResult.sqlErro);
        sqlResult = postgreSQL.runCommand("select current_database()");
        assertNotNull(sqlResult.coluna);
        assertEquals(1,sqlResult.linhas.length);
    }

    @Test
    void getJdbcConnection() throws Exception{
        postgreSQL =  new Postgres(connectionFactory,serverCredential);
        assertNotNull(postgreSQL.getJdbcConnection());
    }

    @Test
    void testToString() throws Exception{
        postgreSQL =  new Postgres(connectionFactory,serverCredential);
        assertEquals(pgsqlserver.getContainerIpAddress(),postgreSQL.toString());
    }

    @Test
    void getDefaultDatabaseName() throws Exception{
        postgreSQL =  new Postgres(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(postgreSQL),"select current_database()");
        assertEquals(result,postgreSQL.getDefaultDatabaseName());
    }

    @Test
    void getVersionQuery() throws Exception{
        postgreSQL =  new Postgres(connectionFactory,serverCredential);
        String result = getSingleResult(connectionFactory.open(postgreSQL),postgreSQL.getVersionQuery());
        assertEquals(result,postgreSQL.getVersion());
    }

}