package org.momento.Features.Item.Implements;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.momento.Momento;
import org.momento.Data.ComponentRegistry;
import org.momento.Features.Item.Item;
import org.momento.Features.Item.ItemComponent;

public class ItemFactory {
    public static Map<String, ItemStack> itemsList = new HashMap<>();

    public static ItemStack getItemFromConfig(String shieldName, ConfigurationSection config) {
        ConfigurationSection itemData = config.getConfigurationSection(shieldName.replace(" ", "_").toLowerCase());
        if (itemData == null) return null;

        List<ItemComponent> cmpList = new ArrayList<>(); 

        for (String key : itemData.getKeys(false)) {
            ConfigurationSection componentSection = itemData.getConfigurationSection(key);
            if (componentSection == null) continue;

            Class<? extends ItemComponent> componentClass = ComponentRegistry.registry().get(key);
            if (componentClass == null) continue;

            try {
                ItemComponent component = componentClass.getDeclaredConstructor().newInstance();
                component.param(componentSection);
                cmpList.add(component);         
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new Item(cmpList).getItemStack();
    }

    public static void populateItems() {
        itemsList = new HashMap<>();
        ConfigurationSection itConfig = Momento.config.getConfigurationSection("items");
        if (itConfig == null) return;
        
        for (String shieldName : itConfig.getKeys(false)) {
            ItemStack shield = getItemFromConfig(shieldName, itConfig);

            if (shield != null) {
                itemsList.put(shieldName, shield);
            }
        }
    }
}