package com.krismorte.zion.service;

import com.krismorte.zion.model.SQLResult;
import com.krismorte.zion.repository.ServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExportSQLResultTest {

    ExportSQLResult mockResult;
    SQLResult sqlResult;
    String path;
    File fileResult;

    @BeforeEach
    void setUp() throws Exception{
        mockResult= Mockito.mock(ExportSQLResult.class);
        sqlResult= Mockito.mock(SQLResult.class);
        path= "path";
        fileResult= Mockito.mock(File.class);

        when(mockResult.export(path,sqlResult))
                .thenReturn(fileResult);
        when(mockResult.export(path,null))
                .thenThrow(Exception.class);
    }

    @Test
    void export() throws Exception{
        Exception exception = assertThrows(Exception.class, () -> {
            mockResult.export(path,null);;
        });
        assertNotNull(exception);

        File newFile = mockResult.export(path,sqlResult);
        assertEquals(fileResult,newFile);
    }
}