/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ZionParameters {

    public static String[] columns = {"Chave", "Valor"};
    public static Object[][] rows;
    private static final String filename = "zion.prop";
    private static final String[] labels = {"MAXROW", "MAXPARALLELISM", "DEFAULTTABRESULT", "DEFAULTTAGRIDSTATUS", "DEFAULTTAGRIDCROSS"};
    private static final int maxRow = 5000;
    private static final int maxParallelism = 30;
    private static final int defaultTabResult = 0;
    private static final int defaultTabGridStatus = 1;
    private static final int defaultTabGridCross = 0;
    public static String errorMessageParallelism = "Valores válidos de paralelismo vão de 1 à " + ZionParameters.maxParallelism;
    public static int row;
    public static int parallelism;
    public static int tabResult;
    public static int tabGridStatus;
    public static int tabGridCross;

    private static void defaultValues() {
        row = maxRow;
        parallelism = maxParallelism;
        tabResult = defaultTabResult;
        tabGridStatus = defaultTabGridStatus;
        tabGridCross = defaultTabGridCross;
    }

    private static boolean propertiesFileExists() {
        return new File(filename).exists();
    }

    public static boolean isValidParallelismValue(int paral) {
        if (paral <= 0) {
            return false;
        } else if (paral > maxParallelism) {
            return false;
        } else {
            return true;
        }
    }

    private static void createDefaultPropertiesFile() {

        defaultValues();
        String txt = labels[0] + "=" + maxRow + "\r\n" + labels[1] + "=" + parallelism + "\r\n" + labels[2] + "=" + tabResult + "\r\n" + labels[3] + "=" + tabGridStatus + "\r\n" + labels[4] + "=" + tabGridCross + "\r\n";
        fullfilTable();
    }

    public static void getParameters() {
        if (propertiesFileExists()) {
            readPropertiesFile();
        } else {
            createDefaultPropertiesFile();
        }
    }

    private static void readPropertiesFile() {

        try {

            Properties properties = new Properties();
            FileInputStream arquivoDePropriedade = new FileInputStream(filename);
            properties.load(arquivoDePropriedade);
            row = Integer.parseInt(properties.getProperty(labels[0]));
            parallelism = Integer.parseInt(properties.getProperty(labels[1]));
            tabResult = Integer.parseInt(properties.getProperty(labels[2]));
            tabGridStatus = Integer.parseInt(properties.getProperty(labels[3]));
            tabGridCross = Integer.parseInt(properties.getProperty(labels[4]));
            fullfilTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fullfilTable() {
        Object[][] tmpRows = {{labels[0], maxRow}, {labels[1], parallelism}, {labels[2], tabResult}, {labels[3], tabGridStatus}, {labels[4], tabGridCross}};
        rows = tmpRows;
    }
}
