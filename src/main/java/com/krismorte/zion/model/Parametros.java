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
public class Parametros {

    public static String[] colunas = {"Chave", "Valor"};
    public static Object[][] linhas;
    private static final String filename = "zion.prop";
    private static final String[] labels = {"MAXROW", "MAXPARALLELISM", "DEFAULTTABRESULT", "DEFAULTTAGRIDSTATUS", "DEFAULTTAGRIDCROSS"};
    private static final int maxRow = 5000;
    private static final int maxParallelism = 30;
    private static final int defaultTabResult = 0;
    private static final int defaultTabGridStatus = 1;
    private static final int defaultTabGridCross = 0;
    public static String errorMessageParallelism = "Valores válidos de paralelismo vão de 1 à " + Parametros.maxParallelism;
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
        /* try {
        defaultValues();
        Properties properties = new Properties();
        properties.setProperty(labels[0], "" + maxRow);
        properties.setProperty(labels[1], "" + parallelism);
        properties.setProperty(labels[2], "" + tabResult);
        properties.setProperty(labels[3], "" + tabGridStatus);
        properties.setProperty(labels[4], "" + tabGridCross);
        FileOutputStream fos;

        fos = new FileOutputStream("/zion.prop");
        properties.store(fos, "Gerado pela Aplicação");
        fos.close();
        preencheMatriz();
        } catch (Exception e) {
        e.printStackTrace();
        }*/
 /*
        public void exportarValores(){

        try {
        Properties propSaida = new Properties();
        propSaida.setProperty("comprimento", "2.000");
        propSaida.setProperty("largura", "1.000");
        propSaida.setProperty("altura", "1.500");
        FileOutputStream fos;

        fos = new FileOutputStream("legenda.properties");
        propSaida.store(fos, "Gerado pela Aplicação");
        fos.close();
        } catch (FileNotFoundException ex) {
        Logger.getLogger(TesteReaterro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(TesteReaterro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        }*/
        defaultValues();
        String txt = labels[0] + "=" + maxRow + "\r\n" + labels[1] + "=" + parallelism + "\r\n" + labels[2] + "=" + tabResult + "\r\n" + labels[3] + "=" + tabGridStatus + "\r\n" + labels[4] + "=" + tabGridCross + "\r\n";
        //CopiaArquivos.writeFile(filename, false, txt);
        preencheMatriz();
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
            //properties.load(Parametros.class.getClass().getResourceAsStream("/arquivos/zion.prop"));
            row = Integer.parseInt(properties.getProperty(labels[0]));
            parallelism = Integer.parseInt(properties.getProperty(labels[1]));
            tabResult = Integer.parseInt(properties.getProperty(labels[2]));
            tabGridStatus = Integer.parseInt(properties.getProperty(labels[3]));
            tabGridCross = Integer.parseInt(properties.getProperty(labels[4]));
            preencheMatriz();
        } catch (Exception e) {
            e.printStackTrace();
            //Main.logApp.addMsgLog(Parametros.class.getCanonicalName(), "Erro: " + e.getMessage());
        }
    }

    private static void preencheMatriz() {
        Object[][] linhasTmp = {{labels[0], maxRow}, {labels[1], parallelism}, {labels[2], tabResult}, {labels[3], tabGridStatus}, {labels[4], tabGridCross}};
        linhas = linhasTmp;
    }
    /*

    public Parametros() {
    getParametros("propriedades//parametros.properties");
    }

    public Parametros(String caminho) {
    getParametros(caminho);
    }

    public void criaArquivo() {
    try {
    File arq = new File("propriedades//parametros.properties");
    arq.delete();
    FileWriter fw = new FileWriter("propriedades//parametros.properties", false);
    fw.write("Servidor=" + getServidor() + "\r\n");
    fw.write("Banco=" + getBanco() + "\r\n");
    fw.write("Usuario=" + getUsuario() + "\r\n");
    fw.write("Senha=" + getSenha() + "\r\n");
    fw.write("Servico=" + getServico() + "\r\n");
    fw.write("Agendamento=" + getAgendamento() + "\r\n");
    fw.write("proximaExecucao=" + getProximaExecucao() + "\r\n");
    fw.flush();
    fw.close();
    } catch (Exception e) {
    LogModel.escrever("log.txt", "Exception: Parametros: " + e.getMessage());
    }
    }

    public void criaArquivo(String caminho) {
    try {
    File arq = new File(caminho);
    arq.delete();
    FileWriter fw = new FileWriter(caminho, false);
    fw.write("Servidor=" + getServidor() + "\r\n");
    fw.write("Banco=" + getBanco() + "\r\n");
    fw.write("Usuario=" + getUsuario() + "\r\n");
    fw.write("Senha=" + getSenha() + "\r\n");
    fw.write("Servico=" + getServico() + "\r\n");
    fw.write("Agendamento=" + getAgendamento() + "\r\n");
    fw.write("proximaExecucao=" + getProximaExecucao() + "\r\n");
    fw.flush();
    fw.close();
    } catch (Exception e) {
    LogModel.escrever("log.txt", "Exception: Parametros: " + e.getMessage());
    }
    }

    public void getParametros(String caminho) {
    try {
    Properties properties = new Properties();
    FileInputStream arquivoDePropriedade = new FileInputStream(caminho);
    properties.load(arquivoDePropriedade);
    setServidor(properties.getProperty("Servidor"));
    setBanco(properties.getProperty("Banco"));
    setUsuario(properties.getProperty("Usuario"));
    setSenha(properties.getProperty("Senha"));
    setAgendamento(properties.getProperty("Agendamento"));
    setServico(properties.getProperty("Servico"));
    setProximaExecucao(properties.getProperty("proximaExecucao"));
    } catch (Exception e) {
    LogModel.escrever("log.txt", "Exception: Parametros: " + e.getMessage());
    }
    }

     */
}
