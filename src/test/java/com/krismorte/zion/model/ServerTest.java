package com.krismorte.zion.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServerTest {

    Server server;
    SQLResult sqlResult;
    String serverName = "server";
    String command = "select 1";

    @BeforeEach
    void setUp() throws Exception {
        server= Mockito.mock(Server.class);
        sqlResult= Mockito.mock(SQLResult.class);


        when(server.toString())
                .thenReturn(serverName);
        when(server.runCommand(null))
                .thenThrow(Exception.class);
        when(server.runCommand(command))
                .thenReturn(sqlResult);
    }

    @Test
    void runCommand() throws Exception{
        Exception exception = assertThrows(Exception.class, () -> {
            server.runCommand(null);
        });
        assertNotNull(exception);
        SQLResult newSQLSqlResult = server.runCommand(command);
        assertEquals(sqlResult,newSQLSqlResult);
    }

    @Test
    void testToString() {
        assertEquals(serverName,server.toString());
    }


}