package com.shaneschulte.windoom.clans.listeners;

import com.shaneschulte.windoom.clans.ClanPlayer;
import com.shaneschulte.windoom.clans.WindoomClans;
import com.shaneschulte.windoom.clans.managers.ClanManager;
import com.shaneschulte.windoom.clans.managers.SettingsManager;
import com.shaneschulte.windoom.clans.utils.MsgUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author CoolGamrSms
 */
public class ChatListener implements Listener {

    JavaPlugin plugin;

    public ChatListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        ClanManager cm = ((WindoomClans)plugin).getClanManager();
        SettingsManager settings = ((WindoomClans)plugin).getSettingsManager();
        ClanPlayer cp = cm.getClanPlayer(e.getPlayer().getUniqueId());
        if(cp == null) return;
        if(cp.getClan() == null) return;
        String tag = MsgUtils.colorize(settings.getTagFormat().replace("{clan}", cp.getClan().getPrefix()));
        e.setFormat(tag+e.getFormat());
    }

}
