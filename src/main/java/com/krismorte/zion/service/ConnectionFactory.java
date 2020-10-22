package com.krismorte.zion.service;

import com.krismorte.zion.model.Server;
import com.krismorte.zion.model.SupportedDatabases;

import java.sql.Connection;

/**
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public interface ConnectionFactory {

    public Connection open(Server server) throws Exception;

}
