/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import com.krismorte.zion.dao.ScriptDao;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
@Getter 
@Setter
public class Script {

    public static String[] colunas = {"Script", "Tipo", "Grupo"};
    public static Object[][] linhas;
    public static final String DIRETORIO = "Scripts";
    private String nome;
    private String tipo;
    private String grupo;
    private String texto;
    private static List<Script> listaArquivos = new ArrayList<Script>();
    private static List<String> listaTipos = new ArrayList<String>();
    public boolean criouTipo = false;

    public Script() {
    }

    public Script(String nome, String tipo, String grupo, String texto) {
        this.nome = nome;
        this.tipo = tipo;
        this.grupo = grupo;
        this.texto = texto;
    }

    public boolean add() {

        ScriptDao dao = new ScriptDao();
        String dirTipo = verificaExistenciaSubDiretorio(DIRETORIO, getTipo());
        String dirGrupo = "";
        if (!(getGrupo().equals(""))) {//grupo pode ser vazio
            dirGrupo = verificaExistenciaSubDiretorio(DIRETORIO + "\\" + getTipo(), getGrupo());
        }
        return dao.insert(this, DIRETORIO + "\\" + dirTipo + dirGrupo);
        /*FileWriter w = new FileWriter(DIRETORIO + "\\" + dirTipo + dirGrupo + this.getNome(), false);

         //System.out.println(xStream.toXML(pessoa));
         w.write(this.getTexto());
         w.flush();
         w.close();*/
        //return true;

    }

    private String verificaExistenciaSubDiretorio(String dir, String subDir) {
        String dirReturn = "";
        if (!subDir.equals("")) {
            if (new File(subDir).exists()) {
                dirReturn = subDir + "\\";
                criouTipo = false;
            } else {
                //System.out.println("Não existe");
                File d = new File(dir + "\\" + subDir);
                d.mkdir();
                dirReturn = subDir + "\\";
                criouTipo = true;
            }
        }
        return dirReturn;
    }

    public static String getScriptPorNome(String tipo, String nome) {
//        try {
//            String grupo = "";
//            if (nome.contains(":")) {
//                int ind = nome.indexOf(":");
//                grupo = nome.substring(0, ind) + "\\";
//                nome = nome.substring(ind + 1).trim();
//            }
//            return CopiaArquivos.readFile(DIRETORIO + "\\" + tipo + "\\" + grupo + nome);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
return "";
    }

    public static List<String> getListaDeGruposPorTipo(String tipo) {
        listaTipos.clear();
        for (File f : new File(DIRETORIO + "\\" + tipo).listFiles()) {
            if (f.isDirectory()) {
                listaTipos.add(f.getName());
            }
        }
        return listaTipos;
    }

    public static List<Script> getListaDeScripts() {
        listaArquivos.clear();
        ScriptDao dao = new ScriptDao();
        //preencheListaDeArquivos(new File(DIRETORIO), "");
        listaArquivos = dao.listByGroup(DIRETORIO);
        return listaArquivos;
    }

    public static List<Script> getListaDeScripts(String tipo) {
        listaArquivos.clear();
        ScriptDao dao = new ScriptDao();
        //preencheListaDeArquivos(new File(DIRETORIO + "\\" + tipo), tipo);
        listaArquivos = dao.listByGroup(DIRETORIO + "\\" + tipo);
        return listaArquivos;
    }

    /*private static boolean preencheListaDeArquivos(File diretorio, String tipo) {
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
     boolean success = preencheListaDeArquivos(new File(diretorio.getAbsolutePath() + "\\" + children[i]), tipo);
     if (!success) {
     return false;
     }
     }
     }
     }
     return true;
     }*/
    public static void preencheMatrizLinhas(List<Script> lista) {
        linhas = new Object[lista.size()][colunas.length];
        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                for (int z = 0; z < colunas.length; z++) {
                    Object[][] obj = {{lista.get(i).getNome(), lista.get(i).getTipo(), lista.get(i).getGrupo()}};
                    linhas[i][z] = obj[0][z];//Rows are dynamics
                }
            }
        }
    }
    /*
     private static boolean preencheListaDeArquivos(File diretorio) {
     if (diretorio.isDirectory()) {
     String[] children = diretorio.list();
     for (int i = 0; i < children.length; i++) {
     if (children[i].endsWith(".sql")) {
     String tipo = "";//ADICIONA TIPO AO NOME
     if (!diretorio.getName().toUpperCase().equals(DIRETORIO.toUpperCase())) {
     tipo = diretorio.getName() + ": ";
     }
     //System.out.println("2 " + children[i]);
     listaArquivos.add(tipo + children[i]);
     } else if (new File(diretorio.getName() + "\\" + children[i]).isDirectory()) {
     boolean success = preencheListaDeArquivos(new File(diretorio.getName() + "\\" + children[i]));
     if (!success) {
     return false;
     }
     }
     }
     }
     return true;
     }*/

    @Override
    public String toString() {
        return getGrupo() + ": " + getNome();
    }

}
