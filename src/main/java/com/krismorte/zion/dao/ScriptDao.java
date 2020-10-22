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
            /*String dirTipo = verificaExistenciaSubDiretorio(DIRETORIO, getTipo());
             String dirGrupo = "";
             if (!(getGrupo().equals(""))) {//grupo pode ser vazio
             dirGrupo = verificaExistenciaSubDiretorio(DIRETORIO + "\\" + getTipo(), getGrupo());
             }*/
            FileWriter w = new FileWriter(dir + script.getNome(), false);

            //System.out.println(xStream.toXML(pessoa));
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
        preencheListaDeArquivos(listaArquivos, dirr, tipo);
        if (listaArquivos.size() <= 0) {
            //Main.logApp.addMsgLog(ScriptDao.class.getCanonicalName(), "Error");
        }
        return listaArquivos;

    }

    private boolean preencheListaDeArquivos(List<Script> listaArquivos, File diretorio, String tipo) {
        //System.out.println("1 " + diretorio.getAbsolutePath());
        if (diretorio.isDirectory()) {
            String[] children = diretorio.list();
            for (int i = 0; i < children.length; i++) {
                if (children[i].endsWith(".sql")) {
                    //System.out.println("2 " + children[i]);
                    String grupo = "";//Identifica se pasta é de tipo ou grupo
                    if (!diretorio.getName().toUpperCase().equals(tipo.toUpperCase())) {
                        grupo = diretorio.getName();
                    }
                    listaArquivos.add(new Script(children[i], tipo, grupo, ""));
                } else if (new File(diretorio.getAbsolutePath() + "\\" + children[i]).isDirectory()) {
                    boolean success = preencheListaDeArquivos(listaArquivos, new File(diretorio.getAbsolutePath() + "\\" + children[i]), tipo);
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
