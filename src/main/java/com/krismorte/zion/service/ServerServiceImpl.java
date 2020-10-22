package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.repository.GroupRepository;
import com.krismorte.zion.repository.ServerRepository;

import java.util.List;

/**
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ServerServiceImpl implements ServerService {

    private ServerRepository serverRepository;

    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Server add(Server server) throws Exception {
        if (!serverRepository.exists(server)) {
            return serverRepository.save(server);
        }
        throw new Exception("Server already exists on this group!");
    }

    @Override
    public void remove(Server server) {
        serverRepository.remove(server);
    }

    @Override
    public List<Server> getAll() {
        return serverRepository.list();
    }

    @Override
    public List<Server> getAllByGroup(Group group) {
        return serverRepository.list(group);
    }
}
