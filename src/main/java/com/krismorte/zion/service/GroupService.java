/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public interface GroupService {
    
    public Group create(Group group) throws Exception;
    
    public Group rename(Group group,String newName) throws Exception;
    
    public void delete(Group group) throws Exception;    
    
    public List<Group> getAll();
    
}
