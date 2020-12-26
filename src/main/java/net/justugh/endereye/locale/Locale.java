package net.justugh.endereye.locale;

import lombok.Getter;

@Getter
public class Locale {

    // General
    private final String prefix = "&9&lEnder Eye &8»&9";

    // X-Ray
    private final String xrayCommandUsage = "%prefix% Usage: /xraycheck <player> (world)";
    private final String xrayNoWorldData = "%prefix% There is no data for %player% in the world %world%.";
    private final String xrayDataTitle = "&9X-Ray Data for &b%player%&8:";
    private final String xrayDataItem = "&b%ore_type% &8(&9Amount Mined&8: &b%amount_mined%&8, &9Veins Mined&8: &b%veins_mined%&8) (&b%percentage%%&8)";

}
