package com.shaneschulte.windoom.clans.managers;

import com.shaneschulte.windoom.clans.Clan;
import com.shaneschulte.windoom.clans.ClanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author CoolGamrSms
 */
public class ClanManager {
    private HashMap<String, Clan> clans;
    private HashMap<UUID, ClanPlayer> online;
    private HashMap<UUID, Clan> invites;
    private JavaPlugin plugin;

    public ClanManager(JavaPlugin plugin) {
        this.plugin = plugin;
        clans = new HashMap<String, Clan>();
        online = new HashMap<UUID, ClanPlayer>();
        invites = new HashMap<UUID, Clan>();
    }

    public void loadOnlinePlayerData() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            ClanPlayer cp = new ClanPlayer(p.getUniqueId());
            online.put(p.getUniqueId(), cp);
        }
    }

    public ClanPlayer getClanPlayer(UUID uniqueId) throws NullPointerException {
        if(online.get(uniqueId) == null) {
            //TODO: Load ClanPlayer from SQL
            throw new NullPointerException();
        }
        return online.get(uniqueId);
    }

    public void setPlayerClan(ClanPlayer player, Clan clan) {
        player.setClan(clan);
        //TODO: write-through to SQL
    }

    public Clan getClan(String prefix) {
        return clans.get(prefix.toLowerCase());
    }

    public boolean createClan(String prefix, String name, ClanPlayer leader) {
        if(clans.containsKey(prefix.toLowerCase())) return false;
        Clan newClan = new Clan(name, prefix, leader);
        //TODO: write-through to SQL
        clans.put(prefix.toLowerCase(), newClan);
        setPlayerClan(leader, newClan);
        leader.setRank(ClanPlayer.Rank.LEADER);
        return true;
    }

    public HashMap<UUID, Clan> getInvites() {
        return invites;
    }
}
