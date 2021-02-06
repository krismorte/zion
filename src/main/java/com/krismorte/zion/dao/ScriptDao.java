/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.krismorte.zion.model.Script;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ScriptDao {

    public boolean insert(Script script, String dir) {
        try {

            FileWriter w = new FileWriter(dir + script.getNome(), false);

            w.write(script.getTexto());
            w.flush();
            w.close();
            return true;
        } catch (Exception e) {
           // Main.logApp.addMsgLog(ScriptDao.class.getCanonicalName(), "Erro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Script> listByGroup(String dir) {

        List<Script> listaArquivos = new ArrayList<Script>();
        File dirr = new File(dir);
        String tipo = dirr.getName();
        fullfillScripts(listaArquivos, dirr, tipo);
        if (listaArquivos.size() <= 0) {
            //Main.logApp.addMsgLog(ScriptDao.class.getCanonicalName(), "Error");
        }
        return listaArquivos;

    }

    private boolean fullfillScripts(List<Script> filesList, File dir, String type) {

        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                if (children[i].endsWith(".sql")) {
                    String group = "";
                    if (!dir.getName().toUpperCase().equals(type.toUpperCase())) {
                        group = dir.getName();
                    }
                    filesList.add(new Script(children[i], type, group, ""));
                } else if (new File(dir.getAbsolutePath() + "\\" + children[i]).isDirectory()) {
                    boolean success = fullfillScripts(filesList, new File(dir.getAbsolutePath() + "\\" + children[i]), type);
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
