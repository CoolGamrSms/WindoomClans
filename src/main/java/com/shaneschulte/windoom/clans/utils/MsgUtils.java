package com.shaneschulte.windoom.clans.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author CoolGamrSms
 */
public class MsgUtils {

        private MsgUtils() { }

        //message colors
        private final static ChatColor info = ChatColor.GRAY, error = ChatColor.RED, success = ChatColor.GREEN;
        private final static String prefix = ChatColor.translateAlternateColorCodes('&', "&e<&6Clans&e> " + info);

        public static String getPrefix() {
            return prefix;
        }

        public static void info(CommandSender s, String msg) {
            msgColor(s, info, msg);
        }

        public static void error(CommandSender s, String msg) {
            msgColor(s, error, msg);
        }

        public static void success(CommandSender s, String msg) {
            msgColor(s, success, msg);
        }

        private static void msgColor(CommandSender s, ChatColor color, String msg) {
            s.sendMessage(prefix + color + ChatColor.translateAlternateColorCodes('&', msg));
        }

}
