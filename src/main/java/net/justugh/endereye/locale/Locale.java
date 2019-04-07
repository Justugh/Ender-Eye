package net.justugh.endereye.locale;

import lombok.Getter;

@Getter
public class Locale {

    // General
    private String prefix = "&9&lEnder Eye &8»&9";
    private String senderMustBePlayer = "%prefix% Only players can use this command!";

    // X-Ray
    private String xrayCommandUsage = "%prefix% Usage: /xraycheck <player> (world)";
    private String xrayNoWorldData = "%prefix% There is no data for %player% in the world %world%.";
    private String xrayDataTitle = "&9X-Ray Data for &b%player%&8:";
    private String xrayDataItem = "&b%ore_type% &8(&9Amount Mined&8: &b%amount_mined%&8, &9Veins Mined&8: &b%veins_mined%&8) (&b%percentage%%&8)";

}
