package com.shaneschulte.windoom.clans.commands.clan;

import com.shaneschulte.windoom.clans.commands.BaseCommand;
import com.shaneschulte.windoom.clans.commands.CommandException;
import com.shaneschulte.windoom.clans.commands.CommandHandler;
import org.bukkit.command.CommandSender;

/**
 * @author CoolGamrSms
 */
public class ClanHelpCommand extends BaseCommand {
    public ClanHelpCommand() {
        super("?", "[page] - gives help on all clan commands", 0, 1);
    }

    public void onCommand(CommandSender sender, String[] args, CommandHandler handler) throws CommandException {
        sender.sendMessage("Gamr is lazy, there is no help :D");
    }
}
