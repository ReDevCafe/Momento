package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.*;
import org.momento.Events.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Momento extends JavaPlugin {

    public static FileConfiguration config;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ChatSystem(), this);
        pluginManager.registerEvents(new AnvilRename(), this);
        pluginManager.registerEvents(new SignEvent(), this);
    }

    @Override
    public void onDisable() {

    }
}
