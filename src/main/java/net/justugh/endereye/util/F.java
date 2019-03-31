package net.justugh.endereye.util;

import net.justugh.endereye.EnderEye;
import org.bukkit.ChatColor;

public class F {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message.replace("%prefix%", EnderEye.getInstance().getConfig().getString("Prefix")));
    }

}
