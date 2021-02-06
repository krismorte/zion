/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.zion.view.threads;

import com.krismorte.zion.model.Server;
import com.krismorte.zion.view.operationPanel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krismorte <krisnamourt_ti@hotmail.com>
 */
public class ExecutionManagerThread extends Thread {

    public boolean running;
    private List<Server> lstSrv = new ArrayList<>();
    private String script;
    private int totalSrv;
    private int count;
    private int parallelism;
    private operationPanel operationPanel;

    public ExecutionManagerThread(List<Server> lstSrv, operationPanel panel, int parallelism, String script) {
        this.running = true;
        this.lstSrv = lstSrv;
        this.operationPanel = panel;
        totalSrv = lstSrv.size();
        count = 0;
        this.script = script;
        this.parallelism = parallelism;
    }

    @Override
    public void run() {
        operationPanel.disableButton();

        List<SingleExecutionThread> cmds = new ArrayList<>();
        List<SingleExecutionThread> threadsIniciadas = new ArrayList<>();
        operationPanel.startCounter();

        for (Server s : lstSrv) {
            cmds.add(new SingleExecutionThread(this, s, script));
        }

        try {
            for (SingleExecutionThread cmd : cmds) {
                cmd.start();
                threadsIniciadas.add(cmd);

                while (threadsIniciadas.size() == parallelism) {
                    int total = threadsIniciadas.size();
                    for (int i = 0; i < total; i++) {
                        if (threadsIniciadas.get(i).isAlive()) {
                            threadsIniciadas.remove(i);
                            break;
                        }
                    }
                }

                if (!running) {
                    break;
                }
            }
            while (count != totalSrv) {
                operationPanel.disableButton();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            operationPanel.enableButton();
        }
    }

    public void addResult(Server server) {
        count++;
        operationPanel.exibeResultado(server);
        if (count == totalSrv) {
            running = false;
            operationPanel.enableButton();
        }
    }

}
