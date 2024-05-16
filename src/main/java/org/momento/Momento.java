package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.*;
import org.momento.Commands.Completer.GiveCommandTabCompleter;
import org.momento.Commands.GiveCommand;
import org.momento.Events.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.momento.Features.Implements.ShieldFeature;

public final class Momento extends JavaPlugin {

    public static FileConfiguration config;
    public static Plugin plugin;

    //TODO: put pluginManager and getCommand somewhere else
    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        plugin = this;

        ShieldFeature.populateShields();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatSystem(), this);
        pluginManager.registerEvents(new AnvilRename(), this);
        pluginManager.registerEvents(new SignEvent(), this);
        pluginManager.registerEvents(new PlayerShieldBlock(), this);

        PluginCommand momentoCommand = getCommand("momento");
        momentoCommand.setExecutor(new GiveCommand(this));
        momentoCommand.setTabCompleter(new GiveCommandTabCompleter(this));
    }

    @Override
    public void onDisable() {

    }
}
