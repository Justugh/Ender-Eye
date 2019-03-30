package net.justugh.endereye.managers.xray;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.justugh.endereye.managers.xray.ore.OreData;
import net.justugh.endereye.managers.xray.ore.OreType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        if(getXRayPlayer(event.getPlayer().getUniqueId()) == null) {
            xRayPlayers.add(new XRayPlayer(event.getPlayer().getUniqueId()));
        }
    }

    /**
     * Get ore data from an X-Ray Player instance from the OreType
     *
     * @param player The player whose OreData instance is being collected.
     * @param type The OreType that we're searching for.
     * @return An instance of the OreData.
     */
    public OreData getOreData(XRayPlayer player, OreType type) {
        if(player.getOreData().stream().anyMatch(oreData -> oreData.getOreType().equals(type))) {
            return player.getOreData().stream().filter(oreData -> oreData.getOreType().equals(type)).findFirst().orElse(null);
        } else {
            OreData oreData = new OreData(type);
            player.getOreData().add(oreData);
            return oreData;
        }
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

}
