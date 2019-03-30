package net.justugh.endereye.managers.xray.ore;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OreType {

    COAL("Coal Ore"),
    IRON("Iron Ore"),
    GOLD("Gold Ore"),
    LAPIS("Lapis Lazuli Ore"),
    REDSTONE("Redstone Ore"),
    EMERALD("Emerald Ore"),
    DIAMOND("Diamond Ore");

    String friendlyName;

}
