package net.justugh.endereye.managers.xray.commands;

import net.justugh.endereye.EnderEye;
import net.justugh.endereye.managers.xray.XRayPlayer;
import net.justugh.endereye.managers.xray.ore.OreData;
import net.justugh.endereye.util.F;
import net.justugh.endereye.util.UUIDUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XRayStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("xraycheck")) {
            if(sender instanceof Player) {
                if(args.length >= 1) {
                    displayCheck((Player) sender, args[0]);
                } else {

                }
            } else {
                sender.sendMessage(F.format(EnderEye.getInstance().getLocale().getSenderMustBePlayer()));
            }
        }
        return true;
    }

    private void displayCheck(Player sender, String player) {
        EnderEye enderEye = EnderEye.getInstance();
        OfflinePlayer bukkitPlayer = Bukkit.getOfflinePlayer(UUIDUtil.getUUID(player));

        if(bukkitPlayer != null) {
            XRayPlayer xRayPlayer = enderEye.getXRayManager().getXRayPlayer(bukkitPlayer.getUniqueId());

            sender.sendMessage(F.format(enderEye.getLocale().getXrayDataTitle().replace("%player%", bukkitPlayer.getName())));

            for (OreData oreData : xRayPlayer.getOreData()) {
                sender.sendMessage(F.format(enderEye.getLocale().getXrayDataItem()
                        .replace("%ore_type%", oreData.getOreType().getFriendlyName())
                        .replace("%amount_mined%", oreData.getAmountMined() + "")
                        .replace("%veins_mined%", oreData.getOreVeinsMined() + "")
                        .replace("%percentage%", enderEye.getXRayManager().getPercentage(xRayPlayer, oreData.getOreType()) + "")));
            }
        } else {
            sender.sendMessage(F.format(enderEye.getLocale().getXrayNoWorldData().replace("%player%", player).replace("%world%", sender.getWorld().getName())));
        }
    }

}
