package net.justugh.endereye.player;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EyePlayer {

    private UUID uuid;

    private boolean vanished = false;
    private boolean inStaffMode = false;

}
