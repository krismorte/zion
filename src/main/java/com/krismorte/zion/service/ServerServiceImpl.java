package com.krismorte.zion.service;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.repository.GroupRepository;
import com.krismorte.zion.repository.ServerRepository;
import com.krismorte.zion.view.InternationalizedUI;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ServerServiceImpl implements ServerService, InternationalizedUI {

    private ServerRepository serverRepository;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Server add(Server server) throws Exception {
        if (!serverRepository.exists(server)) {
            return serverRepository.save(server);
        }
        throw new Exception(getStringI18n("MSG_SRV_EXISTS"));
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

    @Override
    public String getStringI18n(String key) {
        return resourceBundle.getString(key);
    }
}
