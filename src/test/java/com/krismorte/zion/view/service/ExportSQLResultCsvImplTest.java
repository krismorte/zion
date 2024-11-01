package com.krismorte.zion.view.service;

import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.service.ExportSQLResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ExportSQLResultCsvImplTest {

    String filePath = "localTest.csv";
    static SQLResult sqlResult = new SQLResult();
    static ExportSQLResult exportSQLResultCsv;

    @BeforeAll
    static void settup(){
        exportSQLResultCsv = new ExportSQLResultCsvImpl();
        String[] tempCol={"Teste","Teste"};
        sqlResult.columns =tempCol;
        sqlResult.rows = new Object[1][tempCol.length];
        for(int i = 0; i<sqlResult.rows.length; i++){
            sqlResult.rows[i][0] = "row"+i+",col0";
            sqlResult.rows[i][1] = "row"+i+",col1";
        }
    }

    @Test
    void export() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            exportSQLResultCsv.export("",sqlResult);;
        });
        assertNotNull(exception);

        File file = exportSQLResultCsv.export(filePath,sqlResult);;

        assertTrue(file.exists());
        file.delete();
    }
}