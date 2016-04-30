package com.shaneschulte.windoom.clans.commands;

import org.bukkit.command.CommandSender;

/**
 * @author CoolGamrSms
 */
public abstract class BaseCommand {

    protected int min, max;
    protected String cmd, usage;
    public abstract void onCommand(CommandSender sender, String[] args, CommandHandler handler) throws CommandException;

    private void init(String cmd, String usage, int min, int max) {
        this.cmd   = cmd.toLowerCase();
        this.usage = usage;
        this.min   = min;
        this.max   = max;
    }

    public BaseCommand(String cmd, String usage, int min, int max) {
        init(cmd, usage, min, max);
    }
    public BaseCommand(String cmd, String usage, int min) {
        init(cmd, usage, min, -1);
    }
    public BaseCommand(String cmd, String usage) {
        init(cmd, usage, 0, -1);
    }

    public void checkArgs(int args) throws CommandException {
        if(args < min) throw new CommandUsageException(this, "Too few arguments.");
        if(max < 0)    return;
        if(args > max) throw new CommandUsageException(this, "Too many arguments.");
    }

    public String getUsage() {
        return "Usage: /clan " + cmd + " " + usage;
    }

}