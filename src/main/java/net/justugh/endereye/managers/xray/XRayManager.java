package net.justugh.endereye.managers.xray;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.justugh.endereye.managers.xray.ore.OreData;
import net.justugh.endereye.managers.xray.ore.OreType;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.UUID;

@Getter
public class XRayManager implements Listener {

    private List<XRayPlayer> xRayPlayers = Lists.newArrayList();

    public XRayManager() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (getXRayPlayer(event.getPlayer().getUniqueId()) == null) {
            xRayPlayers.add(new XRayPlayer(event.getPlayer().getUniqueId()));
        }
    }

    @EventHandler
    public void onOreMine(BlockBreakEvent event) {
        if (OreType.match(event.getBlock().getType()) == null) {
            return;
        }

        XRayPlayer player = getXRayPlayer(event.getPlayer().getUniqueId());
        OreType oreType = OreType.match(event.getBlock().getType());
        OreData oreData = getOreData(player, oreType);

        if (!isInVein(event.getBlock())) {
            oreData.setOreVeinsMined(oreData.getOreVeinsMined() + 1);
            oreData.setLastVeinMined(System.currentTimeMillis());
        }

        oreData.setAmountMined(oreData.getAmountMined() + 1);
    }

    /**
     * Get the percentage of ore mined.
     *
     * @param player The player who we're retrieving the data from.
     * @param type   The OreType that we're searching for.
     * @return The percentage of this mined OreType.
     */
    public float getPercentage(XRayPlayer player, OreType type) {
        int total = 0;

        for (OreData oreData : player.getOreData()) {
            total += oreData.getAmountMined();
        }

        return (getOreData(player, type).getAmountMined() * 100) / total;
    }

    /**
     * Get ore data from an X-Ray Player instance from the OreType
     *
     * @param player The player whose OreData instance is being collected.
     * @param type   The OreType that we're searching for.
     * @return An instance of the OreData.
     */
    public OreData getOreData(XRayPlayer player, OreType type) {
        return player.getOreData().stream().filter(oreData -> oreData.getOreType().equals(type)).findFirst().orElse(null);
    }

    /**
     * Get X-Ray Player instance from UUID.
     *
     * @param uuid The player's UUID.
     * @return The X-Ray Player instance.
     */
    public XRayPlayer getXRayPlayer(UUID uuid) {
        return xRayPlayers.stream().filter(xRayPlayer -> xRayPlayer.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    /**
     * Check if the current block is part of an ore vein.
     *
     * @param block The block being checked.
     * @return Whether or not the location is part of an ore vein.
     */
    public boolean isInVein(Block block) {
        if (block == null) {
            return false;
        }

        for (BlockFace blockFace : BlockFace.values()) {
            if (blockFace.equals(BlockFace.SELF)) {
                continue;
            }

            if (block.getRelative(blockFace).getType().equals(block.getType())) {
                return true;
            }

            Block topBlock = block.getRelative(blockFace.getModX(), 1, blockFace.getModZ());
            Block bottomBlock = block.getRelative(blockFace.getModX(), -1, blockFace.getModZ());

            if (topBlock != block && topBlock.getType().equals(block.getType())) {
                return true;
            }

            if (bottomBlock != block && bottomBlock.getType().equals(block.getType())) {
                return true;
            }
        }

        return false;
    }

}
