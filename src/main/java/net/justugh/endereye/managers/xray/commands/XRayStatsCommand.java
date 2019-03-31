package net.justugh.endereye.managers.xray.commands;

import net.justugh.endereye.EnderEye;
import net.justugh.endereye.managers.xray.XRayPlayer;
import net.justugh.endereye.managers.xray.ore.OreData;
import net.justugh.endereye.util.F;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XRayStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("xraycheck")) {
            if(args.length >= 1) {
                displayCheck(sender, args[0]);
            } else {

            }
        }
        return true;
    }

    private void displayCheck(CommandSender sender, String player) {
        EnderEye enderEye = EnderEye.getInstance();
        Player bukkitPlayer = Bukkit.getPlayer(player);

        if(bukkitPlayer != null) {
            XRayPlayer xRayPlayer = enderEye.getXRayManager().getXRayPlayer(bukkitPlayer.getUniqueId());

            sender.sendMessage(F.format(enderEye.getConfig().getString("Check-Title").replace("%player%", bukkitPlayer.getName())));

            for (OreData oreData : xRayPlayer.getOreData()) {
                sender.sendMessage(F.format(enderEye.getConfig().getString("Check-Item")
                        .replace("%ore_type%", oreData.getOreType().getFriendlyName())
                        .replace("%amount_mined%", oreData.getAmountMined() + "")
                        .replace("%veins_mined%", oreData.getOreVeinsMined() + "")
                        .replace("%percentage%", enderEye.getXRayManager().getPercentage(xRayPlayer, oreData.getOreType()) + "")));
            }
        } else {
            sender.sendMessage(F.format(enderEye.getConfig().getString("No-Data")));
        }
    }

}
