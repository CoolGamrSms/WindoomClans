package com.shaneschulte.windoom.clans.managers;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author CoolGamrSms
 */
public class SettingsManager {

    private JavaPlugin plugin;
    private int clanCost;
    private String tagFormat;
    private boolean mySQL;
    private int startingElo;

    public SettingsManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public int getClanCost() {
        return clanCost;
    }

    public String getTagFormat() {
        return tagFormat;
    }

    public boolean isMySQL() {
        return mySQL;
    }

    public void loadConfiguration() {
        plugin.getConfig().addDefault("clans.costs.creation", 1000000);
        plugin.getConfig().addDefault("clans.tag.format", "&8<{clan}&8>");
        plugin.getConfig().addDefault("clans.elo.starting", 1000);
        plugin.getConfig().addDefault("database.useMySQL", false);
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();

        //Load values
        clanCost = plugin.getConfig().getInt("clans.costs.creation");
        tagFormat = plugin.getConfig().getString("clans.tag.format");
        mySQL = plugin.getConfig().getBoolean("database.useMySQL");
        startingElo = plugin.getConfig().getInt("clans.elo.starting");
    }

    public int getStartingElo() {
        return startingElo;
    }
}
