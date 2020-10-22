/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.repository.GroupRepository;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group create(Group group) throws Exception {
        if (!groupRepository.exists(group)) {
            return groupRepository.add(group);
        }
        throw new Exception("Group already exists!");
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.list();
    }

    @Override
    public Group rename(Group group, String newName) throws Exception {
        if (!groupRepository.exists(new Group(newName))) {
            return groupRepository.rename(group, newName);
        }
        throw new Exception("Group already exists!");
    }

    @Override
    public void delete(Group group) throws Exception {
        if (!groupRepository.hasContent(group)) {
            groupRepository.remove(group);
            return;
        }
        throw new Exception("Remove all servers from the group first!");
    }

}
