package net.justugh.endereye.player;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class EyePlayer {

    private final UUID uuid;

    @Setter
    private boolean vanished = false;
    @Setter
    private boolean inStaffMode = false;

    public EyePlayer(UUID uuid) {
        this.uuid = uuid;
    }

}
