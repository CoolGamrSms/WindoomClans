package com.shaneschulte.windoom.clans.managers;

import com.shaneschulte.windoom.clans.commands.CallbackCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.util.UUID;

/**
 * @author CoolGamrSms
 */
public class PlayerLoader implements CallbackCommand {

    private static PlayerLoader singleton = new PlayerLoader();
    private static JavaPlugin plugin;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private PlayerLoader(){ }

    public static void setPlugin(JavaPlugin pluginP) {
        plugin = pluginP;
    }

    /* Static 'instance' method */
    public static PlayerLoader getInstance() {
        return singleton;
    }

    public static String buildQuery(UUID player) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM clan_players WHERE uuid = '");
        builder.append(player.toString());
        builder.append("';");
        return builder.toString();
    }

    public void callback(ResultSet res) {
        //TODO: Load in player data
    }
}