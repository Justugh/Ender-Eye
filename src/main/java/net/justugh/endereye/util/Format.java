package net.justugh.endereye.util;

import net.justugh.endereye.EnderEye;
import org.bukkit.ChatColor;

public class Format {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message.replace("%prefix%", EnderEye.getInstance().getLocale().getPrefix()));
    }

}
