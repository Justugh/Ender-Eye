package net.justugh.endereye.managers.xray;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.justugh.endereye.managers.xray.ore.OreData;

import java.util.List;
import java.util.UUID;

@Getter
public class XRayPlayer {

    private UUID uuid;

    private List<OreData> oreData = Lists.newArrayList();

    public XRayPlayer(UUID uuid) {
        this.uuid = uuid;
    }

}
