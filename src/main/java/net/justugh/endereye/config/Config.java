package net.justugh.endereye.config;

import lombok.Getter;

@Getter
public class Config {

    // X-Ray Options
    private final long xraySaveTime = 30;
    private final String xrayCommandPermission = "endereye.xraycheck";

    // Vanish Options
    private final String vanishCommandPermission = "endereye.vanish";
    private final String seeVanishedPlayersPermission = "endereye.seevanished";

}
