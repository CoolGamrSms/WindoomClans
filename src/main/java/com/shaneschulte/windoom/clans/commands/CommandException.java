package com.shaneschulte.windoom.clans.commands;

/**
 * @author CoolGamrSms
 */
public abstract class CommandException extends Exception {
    private String usage;
    private String what;

    public CommandException(BaseCommand cmd, String what) {
        usage = cmd.getUsage();
        this.what = what;
    }

    public String getUsage() {
        return usage;
    }

    public String getWhat() {
        return what;
    }
}
