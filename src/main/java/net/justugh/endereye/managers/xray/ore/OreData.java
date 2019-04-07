package net.justugh.endereye.managers.xray.ore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OreData {

    private OreType oreType;

    private int oreVeinsMined, amountMined;

    public OreData(OreType oreType) {
        this.oreType = oreType;
    }

}
