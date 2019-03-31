package net.justugh.endereye;

import lombok.Getter;
import net.justugh.endereye.managers.xray.XRayManager;
import net.justugh.endereye.managers.xray.commands.XRayStatsCommand;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class EnderEye extends JavaPlugin {

    private static EnderEye instance;

    private XRayManager xRayManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        xRayManager = new XRayManager();
        getCommand("xraycheck").setExecutor(new XRayStatsCommand());
        getServer().getPluginManager().registerEvents(xRayManager, this);
    }

    @Override
    public void onDisable() {

    }

    public static EnderEye getInstance() {
        return instance;
    }

}