package org.momento.Features;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Momento;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShieldFeature {
    public static Map<String, ItemStack> shieldList = new HashMap<>();

    public static ItemStack createShield(String name, long durability, int modelDataId) {
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemMeta meta = shield.getItemMeta();

        assert meta != null;
        meta.setDisplayName("§r§f"+name);
        meta.setCustomModelData(modelDataId);
        meta.setLore(Arrays.asList("", "§fDurability "+durability + " / " + durability));

        // Probably should do an Item Constructor
        NamespacedKey Momento_Item = new NamespacedKey(Momento.plugin, "momento_item");
        NamespacedKey M_Durability = new NamespacedKey(Momento.plugin, "momento_durability");
        NamespacedKey M_MaxDurability = new NamespacedKey(Momento.plugin, "momento_maxdurability");

        meta.getPersistentDataContainer().set(M_Durability, PersistentDataType.LONG, durability);
        meta.getPersistentDataContainer().set(M_MaxDurability, PersistentDataType.LONG, durability);
        meta.getPersistentDataContainer().set(Momento_Item, PersistentDataType.BOOLEAN, true); // <- WOAAAA

        shield.setItemMeta(meta);
        return shield;
    }

    // use for test but it's kind of useless ngl
    public static ItemStack getShieldFromConfig(String shieldName, FileConfiguration config) {
        ConfigurationSection shieldsConfig = config.getConfigurationSection("shields");
        if (shieldsConfig != null) {
            ConfigurationSection shieldData = shieldsConfig.getConfigurationSection(shieldName.replace(" ", "_").toLowerCase());
            if (shieldData != null) {
                String displayName = shieldData.getString("displayName");
                long durability = shieldData.getLong("durability");
                int modelDataId = shieldData.getInt("modelData");
                return createShield(displayName, durability, modelDataId);
            }
        }
        return null;
    }

    public static void populateShields() {
        shieldList = new HashMap<>();
        ConfigurationSection shieldsConfig = Momento.config.getConfigurationSection("shields");
        if (shieldsConfig != null) {
            for (String shieldName : shieldsConfig.getKeys(false)) {
                ItemStack shield = ShieldFeature.getShieldFromConfig(shieldName, Momento.config);
                if (shield != null) {
                    shieldList.put(shieldName, shield);
                    System.out.println(shieldName);
                }
            }
        }
    }
}
