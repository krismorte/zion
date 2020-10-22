package com.krismorte.zion.view.service;

import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.ServerCredential;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ConnectionFactory;
import com.krismorte.zion.sql.MySQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class ConnectionFactoryImplTest {

    @Container
    private static final MySQLContainer mySQLContainer = new MySQLContainer().withDatabaseName("mysql");
    ConnectionFactoryImpl connectionFactoryImpl;
    ServerCredential serverCredential;
    ConnectionFactory connectionFactory;
    Server mySQL;
    @BeforeEach
    void setUp() {
        connectionFactoryImpl = new ConnectionFactoryImpl();
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
    void open() throws Exception {
        mySQL =  new MySQL(connectionFactory,serverCredential);
        assertNotNull(connectionFactoryImpl.open(mySQL));
    }
}