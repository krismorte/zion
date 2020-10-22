/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.service;

import com.krismorte.zion.model.SQLResult;
import java.io.File;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public interface ExportSQLResult {

    public File export(String filePath, SQLResult ssqlResult) throws Exception;

}
