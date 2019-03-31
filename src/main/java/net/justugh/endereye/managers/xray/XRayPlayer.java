package net.justugh.endereye.managers.xray;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.justugh.endereye.managers.xray.ore.OreData;
import net.justugh.endereye.managers.xray.ore.OreType;

import java.util.List;
import java.util.UUID;

@Getter
public class XRayPlayer {

    private UUID uuid;

    private List<OreData> oreData = Lists.newArrayList();

    public XRayPlayer(UUID uuid) {
        this.uuid = uuid;

        for (OreType oreType : OreType.values()) {
            oreData.add(new OreData(oreType));
        }
    }

}
