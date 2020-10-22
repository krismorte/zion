package com.krismorte.zion.repository;

import com.krismorte.zion.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GroupRepositoryTest {

    GroupRepository mockRepository;
    Group group;
    Group secondGroup;


    @BeforeEach
    void init() {
        mockRepository= Mockito.mock(GroupRepository.class);
        group = new Group("test");
        secondGroup = new Group("second test");

        when(mockRepository.add(group))
                .thenReturn(group);
        when(mockRepository.rename(group,"new test"))
                .thenReturn(secondGroup);
        when(mockRepository.list())
                .thenReturn(Arrays.asList(group,secondGroup));
        when(mockRepository.exists(group))
                .thenReturn(Boolean.TRUE);

    }

    @Test
    void add() {

        Group newGroup = mockRepository.add(group);
        assertEquals(group,newGroup);
    }

    @Test
    void rename() {
        Group newGroup = mockRepository.rename(group,"new test");
        assertEquals(secondGroup,newGroup);
        assertEquals(secondGroup.getName(),newGroup.getName());
    }

//    @Test
//    void remove() {
//    }

    @Test
    void exists() {
        Group newGroup = mockRepository.add(group);
        assertTrue(mockRepository.exists(newGroup));
    }

//    @Test
//    void hasContent() {
//    }

    @Test
    void list() {
        assertNotNull(mockRepository.list());
        assertEquals(mockRepository.list().size(),2);
    }
}