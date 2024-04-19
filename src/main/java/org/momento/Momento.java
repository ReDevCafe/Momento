package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.momento.Events.AnvilRename;
import org.momento.Events.ChatSystem;

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
    }

    @Override
    public void onDisable() {

    }
}
