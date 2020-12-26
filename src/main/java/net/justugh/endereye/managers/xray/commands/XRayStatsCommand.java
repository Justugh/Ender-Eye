package net.justugh.endereye.managers.xray.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import net.justugh.endereye.EnderEye;
import net.justugh.endereye.managers.xray.XRayPlayer;
import net.justugh.endereye.managers.xray.ore.OreData;
import net.justugh.endereye.util.Format;
import net.justugh.endereye.util.UUIDUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

@CommandAlias("xraycheck|xcheck")
public class XRayStatsCommand extends BaseCommand {

    @Default
    @Syntax("<player> (world)")
    @CommandPermission("eye.command.xraycheck")
    public void checkPlayer(CommandSender sender, String target, @Default("world") String world) {
        displayCheck(sender, target, world);
    }

    private void displayCheck(CommandSender sender, String player, String world) {
        EnderEye enderEye = EnderEye.getInstance();
        OfflinePlayer bukkitPlayer = Bukkit.getOfflinePlayer(UUIDUtil.getUUID(player));

        if(bukkitPlayer != null && bukkitPlayer.hasPlayedBefore()) {
            XRayPlayer xRayPlayer = enderEye.getXRayManager().getXRayPlayer(bukkitPlayer.getUniqueId(), world);

            if(xRayPlayer == null) {
                sender.sendMessage(Format.format(enderEye.getLocale().getXrayNoWorldData().replace("%player%", player).replace("%world%", world)));
                return;
            }

            sender.sendMessage(Format.format(enderEye.getLocale().getXrayDataTitle().replace("%player%", bukkitPlayer.getName())));

            for (OreData oreData : xRayPlayer.getOreData()) {
                sender.sendMessage(Format.format(enderEye.getLocale().getXrayDataItem()
                        .replace("%ore_type%", oreData.getOreType().getFriendlyName())
                        .replace("%amount_mined%", oreData.getAmountMined() + "")
                        .replace("%veins_mined%", oreData.getOreVeinsMined() + "")
                        .replace("%percentage%", enderEye.getXRayManager().getPercentage(xRayPlayer, oreData.getOreType()) + "")));
            }
        } else {
            sender.sendMessage(Format.format(enderEye.getLocale().getXrayNoWorldData().replace("%player%", player).replace("%world%", world)));
        }
    }

}
