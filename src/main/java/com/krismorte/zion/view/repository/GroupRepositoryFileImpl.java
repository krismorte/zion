/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.repository;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.repository.GroupRepository;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class GroupRepositoryFileImpl extends FileRepository implements GroupRepository {

    @Override
    public Group add(Group group) {
        checkServerDir();
        new File(SERVER_DIR.getAbsolutePath() + "/" + group.getName()).mkdir();
        return group;
    }

    @Override
    public boolean exists(Group group) {
        String groupPath = SERVER_DIR.getAbsolutePath() + "/" + group.getName();
        return new File(groupPath).exists();
    }

    @Override
    public List<Group> list() {
        checkServerDir();
        List<Group> groups = new ArrayList<>();
        String[] directories = new File(SERVER_DIR.getAbsolutePath()).list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });        
        for (String s : directories) {
            groups.add(new Group(s));
        }
        return groups;
    }

    @Override
    public Group rename(Group group,String newName) {
        String groupPath = SERVER_DIR.getAbsolutePath() + "/" + group.getName();
        new File(groupPath).renameTo(new File(SERVER_DIR.getAbsolutePath() + "/" + newName));
        group.setName(newName);
        return group;
    }

    @Override
    public void remove(Group group) {
        String groupPath = SERVER_DIR.getAbsolutePath() + "/" + group.getName();
        new File(groupPath).delete();
    }

    @Override
    public boolean hasContent(Group group) {
        String groupPath = SERVER_DIR.getAbsolutePath() + "/" + group.getName();
        if(new File(groupPath).list().length>0){
            return true;
        }
        return false;
    }

}
