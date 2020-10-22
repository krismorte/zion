/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public interface ServerService {
 
    public Server add(Server server)throws Exception ;
    public void remove(Server server);
    
    public List<Server> getAll() ;
    
    public List<Server> getAllByGroup(Group group) ;
    
}
