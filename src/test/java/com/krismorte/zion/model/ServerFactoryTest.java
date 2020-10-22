package com.krismorte.zion.model;

import com.krismorte.zion.service.ConnectionFactory;
import com.krismorte.zion.service.ServerFactory;
import com.krismorte.zion.sql.SQLServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class ServerFactoryTest {

    @Container
    private static final  MSSQLServerContainer mssqlserver = new MSSQLServerContainer();
    ServerCredential serverCredential;
    ConnectionFactory connectionFactory;
    Server server;
    Server secondServer;
    Group group;

    @BeforeEach
    void setUp() throws Exception{
        group= Mockito.mock(Group.class);

        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection open(Server server) throws Exception {
                Class.forName(mssqlserver.getDriverClassName());
                return DriverManager.getConnection(mssqlserver.getJdbcUrl(), mssqlserver.getUsername(),mssqlserver.getPassword());
            }
        };

        serverCredential = new ServerCredential(mssqlserver.getContainerIpAddress(),mssqlserver.getFirstMappedPort().toString(),
                SupportedDatabases.MSSQL,"master",mssqlserver.getUsername(),mssqlserver.getPassword());
        server = ServerFactory.newInstance(connectionFactory,serverCredential);
        secondServer = ServerFactory.newInstance(connectionFactory,mssqlserver.getContainerIpAddress()
                ,mssqlserver.getFirstMappedPort().toString(), SupportedDatabases.MSSQL, "master", mssqlserver.getUsername(),mssqlserver.getPassword(), "version", "description", group);
//        BDDMockito.given(ServerFactory.newInstance(null,null))
//                .willThrow(Exception.class);
//        BDDMockito.given(ServerFactory.newInstance(connectionFactory,serverCredential))
//                .willReturn(server);
//        BDDMockito.given(ServerFactory.newInstance(connectionFactory,"host", SupportedDatabases.MSSQL, "database", "user", "pass", "version", "description", group))
//                .willReturn(secondServer);
//        when(serverCredential.getServerType())
//                .thenReturn(new String("MSSQL"));
    }

    @Test
    void newInstance() throws Exception{
        Exception exception = assertThrows(Exception.class, () -> {
            ServerFactory.newInstance(null,null);
        });
        assertNotNull(exception);
        Server newServer = ServerFactory.newInstance(connectionFactory,serverCredential);
        assertEquals(server.toString(),newServer.toString());
    }

    @Test
    void testNewInstance() throws Exception{
        Server newServer = ServerFactory.newInstance(connectionFactory,mssqlserver.getContainerIpAddress()
                ,mssqlserver.getFirstMappedPort().toString(), SupportedDatabases.MSSQL, "master", mssqlserver.getUsername(),mssqlserver.getPassword(), "version", "description", group);
        assertEquals(secondServer.toString(),newServer.toString());
    }
}