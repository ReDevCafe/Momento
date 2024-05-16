package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.*;
import org.momento.Commands.Completer.GiveCommandTabCompleter;
import org.momento.Commands.GiveCommand;
import org.momento.Events.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.momento.Features.Implements.Cosmetics.Hat;
import org.momento.Features.Implements.ShieldFeature;

import java.util.LinkedList;

public final class Momento extends JavaPlugin {

    public static FileConfiguration config;
    public static Plugin plugin;

    public static LinkedList<Hat> hats = new LinkedList<>();

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
        pluginManager.registerEvents(new PlayerConnection(), this);

        PluginCommand momentoCommand = getCommand("momento");
        momentoCommand.setExecutor(new GiveCommand(this));
        momentoCommand.setTabCompleter(new GiveCommandTabCompleter(this));
        //Bukkit.getScheduler().runTask()
    }

    @Override
    public void onDisable() {

    }
}
