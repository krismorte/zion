/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.threads;

import com.krismorte.zion.model.Server;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class SingleExecutionThread extends Thread {

    private ExecutionManagerThread threadTela;
    private Server server = null;
    private String script;

    public SingleExecutionThread(ExecutionManagerThread threadTela, Server server, String script) {
        this.threadTela = threadTela;
        this.server = server;
        this.script = script;
    }

    @Override
    public void run() {
        try {
            server.runCommand(script);                    
        } catch (Exception ex) {            
            Logger.getLogger(SingleExecutionThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            threadTela.addResult(server);
        }
    }
}
