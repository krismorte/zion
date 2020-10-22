package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GroupServiceTest {


    GroupRepository mockRepository;
    GroupService groupService;
    Group group;
    Group secondGroup;

    @BeforeEach
    void setUp() {
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
                .thenReturn(Boolean.FALSE);
        groupService = new GroupServiceImpl(mockRepository);
    }

    @Test
    void create() throws Exception{
        Group newGroup = groupService.create(group);
        assertEquals(group,newGroup);
    }

    @Test
    void rename()throws Exception {
        Group newGroup = groupService.rename(group,"new test");
        assertEquals(secondGroup,newGroup);
        assertEquals(secondGroup.getName(),newGroup.getName());
    }

    @Test
    void delete() {
    }
}