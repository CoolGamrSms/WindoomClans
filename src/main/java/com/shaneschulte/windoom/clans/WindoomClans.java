package com.shaneschulte.windoom.clans;

import com.shaneschulte.windoom.clans.commands.ClanCommand;
import com.shaneschulte.windoom.clans.commands.CommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author CoolGamrSms
 */
public class WindoomClans extends JavaPlugin {

    public void registerCommands() {

        CommandHandler handler = new CommandHandler();

        handler.register("clan", new ClanCommand());

        getCommand("clan").setExecutor(handler);
    }

    @Override
    public void onEnable() {
        this.registerCommands();
    }

    @Override
    public void onDisable() {
    }

}

