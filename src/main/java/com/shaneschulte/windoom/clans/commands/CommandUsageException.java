package com.shaneschulte.windoom.clans.commands;

/**
 * @author CoolGamrSms
 */
public class CommandUsageException extends CommandException {

    public CommandUsageException(BaseCommand cmd, String what) {
        super(cmd, what);
    }

}
