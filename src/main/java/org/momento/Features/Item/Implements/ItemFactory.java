package org.momento.Features.Item.Implements;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.momento.Data.ItemComponentRegistry;
import org.momento.Features.Item.Item;
import org.momento.Features.Item.ItemComponent;
import org.momento.Momento;

public class ItemFactory {
    public Map<String, Item> itemsList;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ItemFactory() 
    {
        populateItems();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    protected Item getItemFromConfig(String itemName, ConfigurationSection config) {
        ConfigurationSection itemData = config.getConfigurationSection(itemName.replace(" ", "_").toLowerCase());
        if (itemData == null) return null;

        List<ItemComponent> cmpList = new ArrayList<>(); 

        for (String key : itemData.getKeys(false)) {
            ConfigurationSection componentSection = itemData.getConfigurationSection(key);
            if (componentSection == null) continue;

            Class<? extends ItemComponent> componentClass = ItemComponentRegistry.registry().get(key);
            if (componentClass == null) continue;

            try {
                ItemComponent component = componentClass.getDeclaredConstructor().newInstance();
                component.param(componentSection);
                cmpList.add(component);         
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return new Item(itemName, cmpList);
    }

    protected void populateItems() {
        itemsList = new HashMap<>();
        ConfigurationSection itConfig = Momento.config.getConfigurationSection("items");
        if (itConfig == null) return;
        
        for (String itemName : itConfig.getKeys(false)) {
            Item item = getItemFromConfig(itemName, itConfig);

            if (item != null)
                itemsList.put(itemName, item);
        }
    }
}