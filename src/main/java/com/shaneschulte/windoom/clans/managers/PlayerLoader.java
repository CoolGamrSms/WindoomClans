package com.shaneschulte.windoom.clans.managers;

import com.shaneschulte.windoom.clans.WindoomClans;
import com.shaneschulte.windoom.clans.threads.RunnableRowSet;
import com.shaneschulte.windoom.clans.threads.QueuedQuery;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.rowset.CachedRowSet;
import java.util.UUID;

/**
 * @author CoolGamrSms
 */
public class PlayerLoader implements RunnableRowSet {

    private static PlayerLoader singleton = new PlayerLoader();
    private static JavaPlugin plugin;
    private CachedRowSet data;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private PlayerLoader(){ }

    public static void setPlugin(JavaPlugin pluginP) {
        plugin = pluginP;
    }

    /* Static 'instance' method */
    public static PlayerLoader get() {
        return singleton;
    }

    private static String build(UUID player) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM clan_players WHERE uuid = '");
        builder.append(player.toString());
        builder.append("';");
        return builder.toString();
    }


    public static void load(UUID uniqueId) {
        QueuedQuery qq = new QueuedQuery(PlayerLoader.build(uniqueId), PlayerLoader.get());
        ((WindoomClans)plugin).queueQuery(qq);
    }

    public void run() {
        return;
    }

    public void setData(CachedRowSet data) {
        this.data = data;
    }
}