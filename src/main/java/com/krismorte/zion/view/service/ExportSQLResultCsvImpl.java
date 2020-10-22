/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.service;

import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.service.ExportSQLResult;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ExportSQLResultCsvImpl implements ExportSQLResult {

    @Override
    public File export(String filePath, SQLResult ssqlResult) throws Exception {
        String content = String.join(";", ssqlResult.coluna).concat("\r\n");
        for (Object[] r : ssqlResult.linhas) {
            String[] rowsArray = Arrays.copyOf(r, r.length, String[].class);
            content += String.join(";", rowsArray).concat("\r\n");
        }
        File resultFile = new File(filePath);
        FileWriter writer = new FileWriter(resultFile);
        writer.write(content);
        writer.close();
        return resultFile;
    }

}
