/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.repository.GroupRepository;
import com.krismorte.zion.view.InternationalizedUI;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class GroupServiceImpl implements GroupService, InternationalizedUI {

    private GroupRepository groupRepository;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group create(Group group) throws Exception {
        if (!groupRepository.exists(group)) {
            return groupRepository.add(group);
        }
        throw new Exception(getStringI18n("MSG_GRP_EXISTS"));
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
        throw new Exception(getStringI18n("MSG_GRP_EXISTS"));
    }

    @Override
    public void delete(Group group) throws Exception {
        if (!groupRepository.hasContent(group)) {
            groupRepository.remove(group);
            return;
        }
        throw new Exception(getStringI18n("RM_ALL_SERVER"));
    }

    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }

}
