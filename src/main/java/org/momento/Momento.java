package org.momento;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.momento.Commands.Completer.MomCommandTabCompleter;
import org.momento.Commands.MomCommand;
import org.momento.Data.ComponentRegistry;
import org.momento.Events.AnvilRename;
import org.momento.Events.ChatSystem;
import org.momento.Events.PlayerShieldBlock;
import org.momento.Events.SignEvent;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Features.Item.ItemFile;
import org.momento.Mixin.CraftRecipe;

import com.dragoncommissions.mixbukkit.MixBukkit;
import com.dragoncommissions.mixbukkit.addons.AutoMapper;
import com.dragoncommissions.mixbukkit.api.MixinPlugin;
import com.dragoncommissions.mixbukkit.api.action.impl.MActionInsertShellCode;
import com.dragoncommissions.mixbukkit.api.action.impl.MActionMethodReplacer;
import com.dragoncommissions.mixbukkit.api.locator.impl.HLocatorHead;
import com.dragoncommissions.mixbukkit.api.shellcode.impl.api.CallbackInfo;
import com.dragoncommissions.mixbukkit.api.shellcode.impl.api.ShellCodeReflectionMixinPluginMethodCall;

import net.minecraft.world.item.crafting.RecipeItemStack;

public final class Momento extends JavaPlugin {

    public static FileConfiguration config;
    public static ItemFile items;
    public static Plugin plugin;
    public static MixinPlugin mixinPlugin; 

    //TODO: put pluginManager and getCommand somewhere else
    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        plugin = this;

        Class ntm = null;
        try {
            ntm = Class.forName("net.minecraft.world.item.crafting.RecipeItemStack");
        } catch (ClassNotFoundException e) {
            System.err.println("ntm spigot");
        }
        mixinPlugin = MixBukkit.registerMixinPlugin(this, AutoMapper.getMappingAsStream());
        try {
            mixinPlugin.registerMixin("Kkakakaakakkask",
                    new MActionInsertShellCode(
                        new ShellCodeReflectionMixinPluginMethodCall(CraftRecipe.class.getDeclaredMethod("toNMS", RecipeChoice.class, boolean.class, CallbackInfo.class)),
                        new HLocatorHead()
                    ),
                    org.bukkit.craftbukkit.v1_20_R3.inventory.CraftRecipe.class,
                    "toNMS",
                    RecipeItemStack.class,
                    RecipeChoice.class, boolean.class
            );
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }

        items = new ItemFile();

        ComponentRegistry.init();
        ItemFactory.populateItems();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatSystem(), this);
        pluginManager.registerEvents(new AnvilRename(), this);
        pluginManager.registerEvents(new SignEvent(), this);
        pluginManager.registerEvents(new PlayerShieldBlock(), this);

        PluginCommand momentoCommand = getCommand("momento");
        momentoCommand.setExecutor(new MomCommand(this));
        momentoCommand.setTabCompleter(new MomCommandTabCompleter(this));
    }

    @Override
    public void onDisable() {
        items.saveItems(); // if server crash I guess everything will be rollback (I hope not)
    }
}
