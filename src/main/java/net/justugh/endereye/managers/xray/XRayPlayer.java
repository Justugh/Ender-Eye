package net.justugh.endereye.managers.xray;

import com.google.common.collect.Maps;
import lombok.Getter;
import net.justugh.endereye.managers.xray.ore.OreData;
import net.justugh.endereye.managers.xray.ore.OreType;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class XRayPlayer {

    private UUID uuid;

    private HashMap<OreType, OreData> oreData = Maps.newHashMap();

}
