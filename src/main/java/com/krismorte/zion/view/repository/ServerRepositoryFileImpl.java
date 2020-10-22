/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.repository;

import com.krismorte.zion.model.Group;
import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.SupportedDatabases;
import com.krismorte.zion.service.ServerFactory;
import com.krismorte.zion.repository.ServerRepository;
import com.krismorte.zion.util.Encryptor;
import com.krismorte.zion.view.service.ConnectionFactoryImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ServerRepositoryFileImpl extends FileRepository implements ServerRepository {

    @Override
    public Server save(Server server) {
        try {
            checkServerDir();
            String filePath = SERVER_DIR + "/" + server.getGroup().getName() + "/" + server.getHost() + ".srv";
            FileWriter w = new FileWriter(filePath, false);
            w.write("NAME=" + server.getHost() + "\r");
            w.write("PORT=" + server.getPort() + "\r");
            w.write("TYPE=" + server.getServerType().toString() + "\r");
            w.write("DATABASE=" + server.getDatabase() + "\r");
            w.write("USER=" + server.getUser() + "\r");
            w.write("PASS=" + Encryptor.encrypt(server.getPass()) + "\r");
            w.write("VERSION=" + server.getVersion() + "\r");
            w.write("DESCRIPTION=" + server.getDescription() + "\r");
            w.write("GROUP=" + server.getGroup().getName() + "\r");
            w.flush();
            w.close();
            return server;
        } catch (IOException ex) {
            Logger.getLogger(ServerRepositoryFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void remove(Server server) {
        String filePath = SERVER_DIR + "/" + server.getGroup().getName() + "/" + server.getHost() + ".srv";
        new File(filePath).delete();
    }

    @Override
    public boolean exists(Server server) {
        String filePath = SERVER_DIR + "/" + server.getGroup().getName() + "/" + server.getHost() + ".srv";
        return new File(filePath).exists();
    }

    @Override
    public List<Server> list() {
        List<Server> servers = new ArrayList<>();
        for (String group : SERVER_DIR.list()) {
            String groupDir = SERVER_DIR.getAbsolutePath() + "/" + group;
            for (String serverFiles : getServerFiles(groupDir)) {
                servers.add(extractFromFile(groupDir + "/" + serverFiles));
            }
        }
        return servers;
    }

    @Override
    public List<Server> list(Group group) {
        List<Server> servers = new ArrayList<>();
        String groupDir = SERVER_DIR.getAbsolutePath() + "/" + group.getName();
        for (String serverFiles : getServerFiles(groupDir)) {
            servers.add(extractFromFile(groupDir + "/" + serverFiles));
        }
        return servers;
    }

    private String[] getServerFiles(String groupDir) {
        return new File(groupDir).list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String string) {
                return string.toLowerCase().endsWith(".srv");
            }
        });
    }

    private Server extractFromFile(String filePath) {
        Server server = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File(filePath)));
            server = ServerFactory.newInstance(new ConnectionFactoryImpl(),prop.getProperty("NAME")
                    ,prop.getProperty("PORT"), SupportedDatabases.valueOf(prop.getProperty("TYPE"))
                    ,prop.getProperty("DATABASE"), prop.getProperty("USER")
                    ,Encryptor.decrypt(prop.getProperty("PASS")), prop.getProperty("VERSION")
                    , prop.getProperty("DESCRIPTION"),new Group(prop.getProperty("GROUP")));
        } catch (Exception ex) {
            Logger.getLogger(ServerRepositoryFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return server;
    }

}
