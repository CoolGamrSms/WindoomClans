package com.shaneschulte.windoom.clans.threads;

import com.shaneschulte.windoom.clans.commands.CallbackCommand;

/**
 * @author CoolGamrSms
 */
public class QueuedQuery {

    private CallbackCommand cbcmd;
    private String query;
    private boolean callback;

    public QueuedQuery(String query, CallbackCommand cbcmd) {
        this.cbcmd = cbcmd;
        this.query = query;
        this.callback = true;
    }

    public QueuedQuery(String query) {
        this.query = query;
        this.callback = false;
    }

    public String getQuery() {
        return query;
    }

    public boolean isCallback() {
        return callback;
    }

    public CallbackCommand getCallback() {
        return cbcmd;
    }
}
