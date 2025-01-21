package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.momento.Commands.Completer.GiveCommandTabCompleter;
import org.momento.Commands.GiveCommand;
import org.momento.Data.BlockComponentRegistry;
import org.momento.Data.ItemComponentRegistry;
import org.momento.Events.AnvilRename;
import org.momento.Events.ChatSystem;
import org.momento.Events.PlayerShieldBlock;
import org.momento.Events.SignEvent;
import org.momento.Features.Block.BlockFactory;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Features.Item.ItemFile;

public final class Momento extends JavaPlugin {

    public static FileConfiguration config;
    public static ItemFile items;
    public static Plugin plugin;

    //TODO: put pluginManager and getCommand somewhere else
    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        plugin = this;
        items = new ItemFile();

        BlockComponentRegistry.init();
        BlockFactory.populateBlocks();

        ItemComponentRegistry.init();
        ItemFactory.populateItems();

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
        items.saveItems(); // if server crash I guess everything will be rollback (I hope not)
    }
}
