package org.momento.Features.Implements;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.ItemFeature;
import org.momento.Momento;

import java.util.HashMap;
import java.util.Map;

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

        return new ItemFeature(displayName,Material.SHIELD , durability, modelDataId).createItem();
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
