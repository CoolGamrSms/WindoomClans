package com.shaneschulte.windoom.clans.threads;

import javax.sql.rowset.CachedRowSet;

/**
 * @author CoolGamrSms
 */
public interface RunnableRowSet extends Runnable {
    void setData(CachedRowSet rows);
}
