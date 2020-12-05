/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class EditorSQL extends JTextPane {

    private static final long serialVersionUID = 1L;
    private String[] biblioteca = new String[]{"algoritmo", "caracter", "caso", "de", "dec1", "enquanto", "entao", "escolha", "escreva", "escrevaln", "faca", "fim", "fimcaso", "fimenquanto", "fimescolha", "fimfuncao", "fimpara", "fimprocedimento", "fimse", "funcao", "inicio", "inteiro", "leia", "literal", "para", "procedimento", "real", "repita", "se", "senao", "variaveis"};
    private List<SQLLibrary> library = new ArrayList<SQLLibrary>();

    public EditorSQL() {
        setDocument(doc);
        /*String[] tes = {"select", "insert", "update"};
        library.add(new Biblioteca(biblioteca, Color.red));
        library.add(new Biblioteca(tes, Color.BLUE));*/
    }

    public EditorSQL(List<SQLLibrary> library) {
        this.library = library;
        setDocument(doc);
    }
    DefaultStyledDocument doc = new DefaultStyledDocument() {

        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
            super.insertString(offset, str, a);

            String text = getText(0, getLength());
            int before = findLastNonWordChar(text, offset);
            if (before < 0) {
                before = 0;
            }
            int after = findFirstNonWordChar(text, offset + str.length());
            int wordL = before;
            int wordR = before;

            while (wordR <= after) {
                if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                    for (SQLLibrary b : library) {
                        // if (text.substring(wordL, wordR).matches("(\\W)*(private|public|protected)")) {
                        if (text.substring(wordL, wordR).toUpperCase().matches("(\\W)*(" + b.toStringRegexSearch() + ")")) {
                            setCharacterAttributes(wordL+1, wordR - wordL, getAtrib(b.getColor()), false);
                            break;
                        } else {
                            setCharacterAttributes(wordL+1, wordR - wordL, getAtrib(Color.black), false);
                        }                        
                    }
                    wordL = wordR;
                }
                wordR++;
            }
        }

        public void remove(int offs, int len) throws BadLocationException {
            super.remove(offs, len);

            String text = getText(0, getLength());
            int before = findLastNonWordChar(text, offs);
            if (before < 0) {
                before = 0;
            }
            int after = findFirstNonWordChar(text, offs);
            for (SQLLibrary b : library) {
                if (text.substring(before, after).toUpperCase().matches("(\\W)*(" + b.toStringRegexSearch() + ")")) {
                    setCharacterAttributes(before+1, after - before, getAtrib(b.getColor()), false);
                } else {
                    setCharacterAttributes(before+1, after - before, getAtrib(Color.black), false);
                }
            }
        }
    };

    private AttributeSet getAtrib(Color c) {
        StyleContext cont = StyleContext.getDefaultStyleContext();
        AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, c);
        return attr;
    }

    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    /**
     * @return the library
     */
    public List<SQLLibrary> getLibrary() {
        return library;
    }

    /**
     * @param library the library to set
     */
    public void setLibrary(List<SQLLibrary> library) {
        this.library = library;
    }

    /**
     * @param library the library to set
     */
    public void setLibrary(SQLLibrary library) {
        if (this.library == null) {
            this.library = new ArrayList<SQLLibrary>();
        }
        this.library.add(library);
    }
}
