package net.justugh.endereye;

import co.aikar.commands.PaperCommandManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.justugh.endereye.config.Config;
import net.justugh.endereye.locale.Locale;
import net.justugh.endereye.managers.xray.XRayManager;
import net.justugh.endereye.managers.xray.commands.XRayStatsCommand;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Getter
public class EnderEye extends JavaPlugin {

    private static EnderEye instance;
    private Config configuration;
    private Locale locale;

    private PaperCommandManager commandManager;

    private XRayManager xRayManager;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        loadLocale();

        commandManager = new PaperCommandManager(this);

        xRayManager = new XRayManager();

        loadCommands();

        getServer().getPluginManager().registerEvents(xRayManager, this);
    }

    @Override
    public void onDisable() {

    }

    private void loadCommands() {
        commandManager.registerCommand(new XRayStatsCommand());
    }

    public static EnderEye getInstance() {
        return instance;
    }

    /**
     * Load the Config file to memory.
     */
    private void loadConfig() {
        File configFile = new File(getDataFolder() + File.separator + "config.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if(configFile.exists()) {
            try {
                Config config = gson.fromJson(new FileReader(configFile), Config.class);

                FileUtils.writeStringToFile(configFile, gson.toJson(config)); // Make sure the Config has all the latest variables.

                this.configuration = gson.fromJson(new FileReader(configFile), Config.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.writeStringToFile(configFile, gson.toJson(new Config()));

                this.configuration = gson.fromJson(new FileReader(configFile), Config.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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