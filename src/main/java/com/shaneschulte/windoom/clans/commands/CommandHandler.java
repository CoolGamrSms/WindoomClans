package com.shaneschulte.windoom.clans.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * @author CoolGamrSms
 */
public class CommandHandler implements CommandExecutor
{

    //This is where we will store the commands
    private static HashMap<String, BaseCommand> commands;
    private JavaPlugin plugin;

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public CommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        commands = new HashMap<String, BaseCommand>();
    }

    public BaseCommand register(String name, BaseCommand cmd) {
        commands.put(name, cmd);
        return cmd;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }

        BaseCommand bcmd;
        String[] newArgs;
        int argc = args.length;

        //Root command
        if(args.length == 0) {
            bcmd = commands.get(cmd.getName());
            newArgs = args;
        }
        //Sub command
        else if(commands.containsKey(args[0])) {
            bcmd = commands.get(args[0]);
            newArgs = new String[--argc];
            System.arraycopy(args, 1, newArgs, 0, argc);

        }
        else {
            sender.sendMessage(ChatColor.RED + "Command not found. Type /clan ? for help.");
            return true;
        }

        //Execute command
        try {
            bcmd.checkArgs(argc);
            bcmd.onCommand(sender, newArgs, this);
        } catch(CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getWhat());
            if(e instanceof CommandUsageException) sender.sendMessage(ChatColor.RED + e.getUsage());
            return true;
        }
        return true;
    }

}