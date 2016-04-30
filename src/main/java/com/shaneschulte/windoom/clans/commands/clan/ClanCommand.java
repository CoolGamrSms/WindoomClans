package com.shaneschulte.windoom.clans.commands.clan;

import com.shaneschulte.windoom.clans.commands.BaseCommand;
import com.shaneschulte.windoom.clans.commands.CommandHandler;
import com.shaneschulte.windoom.clans.commands.CommandUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author CoolGamrSms
 */
public class ClanCommand extends BaseCommand {
    public ClanCommand() {
        super("?", "[page] - gives help on all clan commands");
    }

    public void onCommand(CommandSender sender, String[] args, CommandHandler handler) throws CommandUsageException {
        throw new CommandUsageException(this, ChatColor.AQUA + "Welcome to WindoomClans by CoolGamrSms");
    }
}
