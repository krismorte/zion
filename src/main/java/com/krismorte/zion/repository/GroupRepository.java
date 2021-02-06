/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.repository;

import com.krismorte.zion.model.Group;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public interface GroupRepository {
    
    public Group add(Group group);
    
    public Group rename(Group group,String newName);
    
    public void remove(Group group);
    
    public boolean exists(Group group);
    
    public boolean hasContent(Group group);
    
    public List<Group> list();
    
}
