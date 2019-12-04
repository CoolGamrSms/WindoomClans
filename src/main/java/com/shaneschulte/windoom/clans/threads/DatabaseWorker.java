package com.shaneschulte.windoom.clans.threads;

import com.huskehhh.mysql.Database;
import com.huskehhh.mysql.mysql.MySQL;
import com.huskehhh.mysql.sqlite.SQLite;
import com.shaneschulte.windoom.clans.WindoomClans;
import com.shaneschulte.windoom.clans.managers.SettingsManager;
import com.sun.rowset.CachedRowSetImpl;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

/**
 * @author CoolGamrSms
 */
public class DatabaseWorker implements Runnable {

    private JavaPlugin plugin;
    private Database database;
    private SettingsManager settings;
    private BlockingQueue<QueuedQuery> queue;
    private Connection c;

    public DatabaseWorker(JavaPlugin plugin, BlockingQueue<QueuedQuery> queue) {
        this.plugin = plugin;
        this.queue = queue;
        settings = ((WindoomClans)plugin).getSettingsManager();


        if(settings.isMySQL()) {
            //database = new MySQL();
        }
        else {
            database = new SQLite("clans.db");
        }

        try {
            c = database.openConnection();
            System.out.println("[Clans] Database connected successfully");
            databaseInit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void databaseInit() throws SQLException, ClassNotFoundException {
        database.updateSQL("CREATE TABLE IF NOT EXISTS clans (" +
                "id INTEGER PRIMARY KEY ASC, " +
                "name varchar(60) NOT NULL, " +
                "prefix varchar(30) NOT NULL," +
                "elo INTEGER NOT NULL" +
                ");");
        database.updateSQL("CREATE TABLE IF NOT EXISTS clan_players (" +
                "id INTEGER PRIMARY KEY ASC, " +
                "uuid char(36) NOT NULL, " +
                "clan_id INTEGER NOT NULL, " +
                "rank INTEGER NOT NULL, " +
                "o_kills INTEGER NOT NULL, " +
                "o_deaths INTEGER NOT NULL, " +
                "a_kills INTEGER NOT NULL, " +
                "a_deaths INTEGER NOT NULL, " +
                "elo INTEGER NOT NULL" +
                ");");
    }

    public void run() {
        while(true) {
            QueuedQuery qq;
            try {
                qq = queue.take();
            } catch(InterruptedException e) {
                continue;
            }
            try {
                if(qq.isCallback()) {
                    ResultSet res = database.querySQL(qq.getQuery());
                    CachedRowSet rows = new CachedRowSetImpl();
                    rows.populate(res);
                    qq.getCallback().setData(rows);
                    BukkitScheduler scheduler = plugin.getServer().getScheduler();
                    scheduler.runTask(plugin, qq.getCallback());
                }
                else {
                    database.querySQL(qq.getQuery());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
