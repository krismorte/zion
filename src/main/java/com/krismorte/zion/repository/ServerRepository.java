/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.repository;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public interface ServerRepository {
    
    public Server save(Server server);
    public void remove(Server server);
    public boolean exists(Server server);
    
    public List<Server> list();  
    public List<Server> list(Group group);  
    
}
