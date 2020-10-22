/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.repository;

import java.io.File;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class FileRepository {
    protected final File SERVER_DIR = new File("SERVERS");
    
     protected void checkServerDir() {
        if (!SERVER_DIR.exists()) {
            SERVER_DIR.mkdir();
        }
    }
    
}
