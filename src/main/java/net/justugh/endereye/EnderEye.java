package net.justugh.endereye;

import org.bukkit.plugin.java.JavaPlugin;

public class EnderEye extends JavaPlugin {

    private static EnderEye instance;

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {

    }

    public static EnderEye getInstance() {
        return instance;
    }

}