package net.justugh.endereye;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.justugh.endereye.locale.Locale;
import net.justugh.endereye.managers.xray.XRayManager;
import net.justugh.endereye.managers.xray.commands.XRayStatsCommand;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Getter
public class EnderEye extends JavaPlugin {

    private static EnderEye instance;
    private Locale locale;

    private XRayManager xRayManager;

    @Override
    public void onEnable() {
        instance = this;
        loadLocale();
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

    /**
     * Load the Locale file to memory.
     */
    private void loadLocale() {
        File localeFile = new File(getDataFolder() + File.separator + "locale.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if(localeFile.exists()) {
            try {
                Locale locale = gson.fromJson(new FileReader(localeFile), Locale.class);

                FileUtils.writeStringToFile(localeFile, gson.toJson(locale)); // Make sure the Locale has all the latest variables.

                this.locale = gson.fromJson(new FileReader(localeFile), Locale.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.writeStringToFile(localeFile, gson.toJson(new Locale()));

                this.locale = gson.fromJson(new FileReader(localeFile), Locale.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}