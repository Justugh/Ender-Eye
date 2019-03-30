package net.justugh.endereye.managers.xray.ore;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter @Setter
public class OreData {

    private OreType oreType;

    private int oreVeinsMined, amountMined;
    private Location lastMinedOre;
    private long lastVeinMined;

    public OreData(OreType oreType) {
        this.oreType = oreType;
    }

}
