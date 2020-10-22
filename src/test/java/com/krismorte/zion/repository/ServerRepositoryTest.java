package com.krismorte.zion.repository;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServerRepositoryTest {

    ServerRepository mockRepository;
    Group group;
    Server server;
    Server secondServer;

    @BeforeEach
    void setUp() {
        mockRepository= Mockito.mock(ServerRepository.class);
        server= Mockito.mock(Server.class);
        secondServer= Mockito.mock(Server.class);
        group= Mockito.mock(Group.class);
        when(mockRepository.save(server))
                .thenReturn(server);
        when(mockRepository.list())
                .thenReturn(Arrays.asList(server,secondServer));
        when(mockRepository.list(group))
                .thenReturn(Arrays.asList(server));
    }

    @Test
    void save() {
        Server newServer = mockRepository.save(server);
        assertEquals(server,newServer);
    }

    @Test
    void list() {
        assertEquals(2,mockRepository.list().size());
    }

    @Test
    void testList() {
        assertEquals(1,mockRepository.list(group).size());
    }
}