package com.shaneschulte.windoom.clans.commands.clan;

import com.shaneschulte.windoom.clans.ClanPlayer;
import com.shaneschulte.windoom.clans.WindoomClans;
import com.shaneschulte.windoom.clans.commands.BaseCommand;
import com.shaneschulte.windoom.clans.commands.CommandErrorException;
import com.shaneschulte.windoom.clans.commands.CommandException;
import com.shaneschulte.windoom.clans.commands.CommandHandler;
import com.shaneschulte.windoom.clans.managers.ClanManager;
import com.shaneschulte.windoom.clans.managers.SettingsManager;
import com.shaneschulte.windoom.clans.threads.QueuedQuery;
import com.shaneschulte.windoom.clans.threads.RunnableRowSet;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.sql.rowset.CachedRowSet;

/**
 * @author CoolGamrSms
 */
public class ClanCreateCommand extends BaseCommand implements RunnableRowSet {

    private CachedRowSet data;

    public ClanCreateCommand() {
        super("create", "[prefix] [name] - creates a new clan", 2);
    }

    public void onCommand(CommandSender sender, String[] args, CommandHandler handler) throws CommandException {
        ClanManager cm = ((WindoomClans)handler.getPlugin()).getClanManager();
        SettingsManager settings = ((WindoomClans)handler.getPlugin()).getSettingsManager();
        Player p = (Player)sender;

        ClanPlayer cp = cm.getClanPlayer(p.getUniqueId());
        if(cp.getClan() != null) throw new CommandErrorException(this, "You are already in a clan.");

        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < args.length; ++i) { if(i>1) builder.append(" "); builder.append(args[i]); }

        String name = builder.toString().replaceAll("'", "").replaceAll("`", "");
        String prefix = args[0].replaceAll("'", "").replaceAll("`", "");
        cm.createClan(args[0], name, cp);

        QueuedQuery qq = new QueuedQuery("INSERT INTO clans (name, prefix, elo) VALUES ('"+name+"', '"+prefix+"', "+settings.getStartingElo()+"); SELECT * FROM clans WHERE ID=LAST_INSERT_ID();", this);
        ((WindoomClans)handler.getPlugin()).queueQuery(qq);

        sender.sendMessage(ChatColor.GREEN + "You were charged " + settings.getClanCost());
        sender.sendMessage(ChatColor.GREEN + "Your clan '" + ChatColor.WHITE + name + ChatColor.GREEN + "' was successfully created!");
    }

    public void setData(CachedRowSet data) {
        this.data = data;
    }

    public void run() {

    }
}
