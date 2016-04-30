package com.shaneschulte.windoom.clans.commands.clan;

import com.shaneschulte.windoom.clans.Clan;
import com.shaneschulte.windoom.clans.ClanPlayer;
import com.shaneschulte.windoom.clans.WindoomClans;
import com.shaneschulte.windoom.clans.commands.BaseCommand;
import com.shaneschulte.windoom.clans.commands.CommandErrorException;
import com.shaneschulte.windoom.clans.commands.CommandException;
import com.shaneschulte.windoom.clans.commands.CommandHandler;
import com.shaneschulte.windoom.clans.managers.ClanManager;
import com.shaneschulte.windoom.clans.managers.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author CoolGamrSms
 */
public class ClanAcceptCommand extends BaseCommand {
    public ClanAcceptCommand() {
        super("accept", "- Accept a clan invitation", 0, 0);
    }

    public void onCommand(CommandSender sender, String[] args, CommandHandler handler) throws CommandException {
        ClanManager cm = ((WindoomClans)handler.getPlugin()).getClanManager();
        SettingsManager settings = ((WindoomClans)handler.getPlugin()).getSettingsManager();
        Player p = (Player)sender;

        ClanPlayer cp = cm.getClanPlayer(p.getUniqueId());

        if(cp.getClan() != null) throw new CommandErrorException(this, "You are already in a clan.");
        HashMap<UUID, Clan> invites = cm.getInvites();

        if(!invites.containsKey(p.getUniqueId())) throw new CommandErrorException(this, "You have no invites.");
        Clan c = invites.get(p.getUniqueId());
        cm.setPlayerClan(cp, c);
        invites.remove(p.getUniqueId());
        Bukkit.broadcastMessage(ChatColor.AQUA+"[Clans] "+ChatColor.WHITE+p.getName()+ChatColor.AQUA+" has joined "+ChatColor.WHITE+c.getName());
    }
}
