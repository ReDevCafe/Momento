package org.momento.Features;


import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Momento;

public class ItemFeature
{
    // Configs
    private final String displayName;
    private final Material material;
    private final long durability;
    private final int modeleData;

    public ItemFeature(String name, Material material, long durability, int modeleData)
    {
        this.displayName = name;
        this.material = material;
        this.durability = durability;
        this.modeleData = modeleData;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName("§r§f" + displayName);
        meta.setCustomModelData(modeleData);

        NamespacedKey momentoItemKey = new NamespacedKey(Momento.plugin, "momento_item");
        NamespacedKey durabilityKey = new NamespacedKey(Momento.plugin, "momento_durability");
        NamespacedKey maxDurabilityKey = new NamespacedKey(Momento.plugin, "momento_maxdurability");

        meta.getPersistentDataContainer().set(durabilityKey, PersistentDataType.LONG, durability);
        meta.getPersistentDataContainer().set(maxDurabilityKey, PersistentDataType.LONG, durability);
        meta.getPersistentDataContainer().set(momentoItemKey, PersistentDataType.BOOLEAN, true);

        item.setItemMeta(meta);
        return item;
    }
}
