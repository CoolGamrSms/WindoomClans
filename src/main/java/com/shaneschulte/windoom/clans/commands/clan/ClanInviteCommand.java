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
public class ClanInviteCommand extends BaseCommand {
    public ClanInviteCommand() {
        super("invite", "[player] - invites a player to your clan", 1, 1);
    }

    public void onCommand(CommandSender sender, String[] args, CommandHandler handler) throws CommandException {
        ClanManager cm = ((WindoomClans)handler.getPlugin()).getClanManager();
        SettingsManager settings = ((WindoomClans)handler.getPlugin()).getSettingsManager();
        Player p = (Player)sender;

        ClanPlayer cp = cm.getClanPlayer(p.getUniqueId());
        if(cp.getClan() == null) throw new CommandErrorException(this, "You are not in a clan.");
        if(!cp.isLeader()) throw new CommandErrorException(this, "You must be a leader to do this.");

        Player invitee = Bukkit.getPlayer(args[0]);
        if(invitee == null) throw new CommandErrorException(this, "Player not found.");

        ClanPlayer cp2 = cm.getClanPlayer(invitee.getUniqueId());
        if(cp2 == null) throw new CommandErrorException(this, "Something weird went wrong, tell gamr");

        if(cp2.getClan() != null) throw new CommandErrorException(this, "This player is already in a clan.");

        HashMap<UUID, Clan> invites = cm.getInvites();
        if(invites.get(invitee.getUniqueId()) != null) throw new CommandErrorException(this, "Player already has a pending invite.");

        invites.put(invitee.getUniqueId(), cp.getClan());
        sender.sendMessage(ChatColor.GREEN + "Invite sent.");
        invitee.sendMessage(ChatColor.AQUA+"You have been invited to join "+ChatColor.RESET+cp.getClan().getName());
        invitee.sendMessage(ChatColor.AQUA+"Please type either "+ChatColor.GREEN+"/clan accept "+ChatColor.AQUA+" or "+ChatColor.RED+"/clan decline");
    }
}
