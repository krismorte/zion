/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
@AllArgsConstructor
@Data
public class ServerCredential {
 
    private String host;
    private String port;
    private SupportedDatabases serverType;
    private String database;
    private String user;
    private String pass;
    
}
