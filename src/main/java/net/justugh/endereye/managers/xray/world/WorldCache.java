package net.justugh.endereye.managers.xray.world;

import com.google.common.collect.Maps;
import lombok.Getter;
import net.justugh.endereye.managers.xray.XRayPlayer;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class WorldCache {

    private String world;
    private HashMap<UUID, XRayPlayer> playerCache = Maps.newHashMap();

    public WorldCache(String world) {
        this.world = world;
    }

}
