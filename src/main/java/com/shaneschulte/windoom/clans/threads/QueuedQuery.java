package com.shaneschulte.windoom.clans.threads;

/**
 * @author CoolGamrSms
 */
public class QueuedQuery {

    private RunnableRowSet call;
    private String query;
    private boolean callback;

    public QueuedQuery(String query, RunnableRowSet call) {
        this.call = call;
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

    public RunnableRowSet getCallback() {
        return call;
    }

}
