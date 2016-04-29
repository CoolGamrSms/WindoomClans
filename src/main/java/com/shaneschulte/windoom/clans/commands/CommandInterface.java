package com.shaneschulte.windoom.clans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author CoolGamrSms
 */
public interface CommandInterface {

    //Every time I make a command, I will use this same method.
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);

}