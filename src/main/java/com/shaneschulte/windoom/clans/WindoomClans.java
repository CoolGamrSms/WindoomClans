package com.shaneschulte.windoom.clans;

import com.shaneschulte.windoom.clans.commands.*;
import com.shaneschulte.windoom.clans.commands.clan.*;
import com.shaneschulte.windoom.clans.listeners.ChatListener;
import com.shaneschulte.windoom.clans.managers.ClanManager;
import com.shaneschulte.windoom.clans.managers.PlayerLoader;
import com.shaneschulte.windoom.clans.managers.SettingsManager;
import com.shaneschulte.windoom.clans.threads.DatabaseWorker;
import com.shaneschulte.windoom.clans.threads.QueuedQuery;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author CoolGamrSms
 */
public class WindoomClans extends JavaPlugin {

    private SettingsManager settingsManager;
    private ClanManager clanManager;
    private DatabaseWorker worker;
    private BlockingQueue<QueuedQuery> queue;

    public void registerCommands() {

        CommandHandler handler = new CommandHandler(this);

        handler.register("clan", new ClanCommand());
        handler.register("?", handler.register("help", new ClanHelpCommand()));
        handler.register("invite", new ClanInviteCommand());
        handler.register("accept", new ClanAcceptCommand());
        handler.register("decline", new ClanAcceptCommand());
        handler.register("create", new ClanCreateCommand());

        getCommand("clan").setExecutor(handler);
    }

    public ClanManager getClanManager() {
        return clanManager;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public void queueQuery(QueuedQuery qq) {
        queue.add(qq);
    }

    @Override
    public void onEnable() {
        this.registerCommands();

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

        settingsManager = new SettingsManager(this);
        clanManager     = new ClanManager(this);

        clanManager.loadOnlinePlayerData();
        settingsManager.loadConfiguration();

        PlayerLoader.setPlugin(this);
        queue = new LinkedBlockingQueue<QueuedQuery>();
        worker = new DatabaseWorker(this, queue);
        new Thread(worker).start();

        System.out.print("[Clans] WindoomClans Enabled!");
    }

    @Override
    public void onDisable() {
    }

}

