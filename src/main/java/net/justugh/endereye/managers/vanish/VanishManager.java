package net.justugh.endereye.managers.vanish;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

@Getter
public class VanishManager implements Listener {

    private final List<UUID> vanishCache;

    public VanishManager() {
        vanishCache = Lists.newArrayList();
    }

    
    public boolean toggleVanish(Player player) {
        throw new NotImplementedException();
    }

    public boolean isVanished(UUID uuid) {
        return vanishCache.contains(uuid);
    }

}
