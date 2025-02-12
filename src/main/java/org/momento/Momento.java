package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.momento.Commands.Completer.MomentoCommandTabCompleter;
import org.momento.Commands.MomentoCommand;
import org.momento.Data.BlockComponentRegistry;
import org.momento.Data.ItemComponentRegistry;
import org.momento.Events.AnvilRename;
import org.momento.Events.BreakingBlock;
import org.momento.Events.ChatSystem;
import org.momento.Events.PlayerShieldBlock;
import org.momento.Events.SignEvent;
import org.momento.Features.Block.BlockFactory;
import org.momento.Features.Block.BlockFile;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Features.Item.ItemFile;

public final class Momento extends JavaPlugin {

    public static FileConfiguration config;
    public static ItemFile items;
    public static BlockFile blocks;
    public static Plugin plugin;

    public static ItemFactory factory_item;
    public static BlockFactory factory_block;

    //TODO: put pluginManager and getCommand somewhere else
    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        plugin = this;
        items = new ItemFile();
        blocks = new BlockFile();

        ItemComponentRegistry.init();
        factory_item = new ItemFactory();
        
        BlockComponentRegistry.init();
        factory_block = new BlockFactory();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatSystem(), this);
        pluginManager.registerEvents(new AnvilRename(), this);
        pluginManager.registerEvents(new SignEvent(), this);
        pluginManager.registerEvents(new PlayerShieldBlock(), this);
        pluginManager.registerEvents(new BreakingBlock(), this);

        PluginCommand momentoCommand = getCommand("momento");
        assert momentoCommand != null;
        momentoCommand.setExecutor(new MomentoCommand(this));
        momentoCommand.setTabCompleter(new MomentoCommandTabCompleter(this));
    }

    @Override
    public void onDisable() {
                                // <3 
        items.saveItems();      // if server crash I guess everything will be rollback (I hope not)
        blocks.saveBlocks();    // if server crash I guess everything will be rollback (I hope not)
    }
}
