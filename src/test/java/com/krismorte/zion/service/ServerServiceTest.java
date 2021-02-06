package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.repository.GroupRepository;
import com.krismorte.zion.repository.ServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServerServiceTest {

    ServerService serverService;
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
        serverService = new ServerServiceImpl(mockRepository);
    }

    @Test
    void add() throws Exception{
        Server newServer = serverService.add(server);
        assertEquals(server,newServer);
    }

    @Test
    void getAll() {
        assertEquals(2,serverService.getAll().size());
    }

    @Test
    void getAllByGroup() {
        assertEquals(1,serverService.getAllByGroup(group).size());
    }
}