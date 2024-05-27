package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.*;
import org.momento.Commands.Completer.GiveCommandTabCompleter;
import org.momento.Commands.GiveCommand;
import org.momento.Events.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.momento.Features.Item.Implements.ShieldFeature;
import org.momento.Features.Item.Component.ItemStackComponent;
import org.momento.Features.Item.Item;

import java.util.Arrays;

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

        new Item(Arrays.asList(new ItemStackComponent("sex", Material.COAL, 1)));
    }

    @Override
    public void onDisable() {

    }
}
