package com.shaneschulte.windoom.clans.commands;

/**
 * @author CoolGamrSms
 */
public class CommandErrorException extends CommandException {

    public CommandErrorException(BaseCommand cmd, String what) {
        super(cmd, what);
    }

}
