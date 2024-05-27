package org.momento.Features.Item.Implements;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.Item.Component.DurabilityComponent;
import org.momento.Features.Item.Component.ItemStackComponent;
import org.momento.Features.Item.Item;
import org.momento.Momento;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//TODO: Rework the way we handle items in the config to have a all in one function for this shit
public class ShieldFeature {
    public static Map<String, ItemStack> shieldList = new HashMap<>();

    // use for test but it's kind of useless ngl
    public static ItemStack getShieldFromConfig(String shieldName, FileConfiguration config) {
        ConfigurationSection shieldsConfig = config.getConfigurationSection("shields");
        if (shieldsConfig == null) return null;

        ConfigurationSection shieldData = shieldsConfig.getConfigurationSection(shieldName.replace(" ", "_").toLowerCase());
        if (shieldData == null) return null;

        String displayName = shieldData.getString("displayName");
        long durability = shieldData.getLong("durability");
        int modelDataId = shieldData.getInt("modelData");

        return new Item(Arrays.asList(
                new ItemStackComponent(displayName, Material.SHIELD, modelDataId),
                new DurabilityComponent(durability)
        )).getItemStack();
    }

    public static void populateShields() {
        shieldList = new HashMap<>();
        ConfigurationSection shieldsConfig = Momento.config.getConfigurationSection("shields");
        if (shieldsConfig == null) return;

        for (String shieldName : shieldsConfig.getKeys(false)) {
            ItemStack shield = ShieldFeature.getShieldFromConfig(shieldName, Momento.config);
            if (shield != null) {
                shieldList.put(shieldName, shield);
            }
        }
    }
}
