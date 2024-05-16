package org.momento.Features;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Data.MomentoKeys;

public class ItemFeature
{
    // Configs
    private final String displayName;
    private final Material material;
    private final long durability;
    private final int modelData;

    public ItemFeature(String name, Material material, long durability, int modelData)
    {
        this.displayName = name;
        this.material = material;
        this.durability = durability;
        this.modelData = modelData;
    }

    public ItemStack createItem() {
        if(durability <= 0 || material == Material.AIR || displayName == null) return null;

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName("§r§f" + displayName);
        meta.setCustomModelData(modelData);
        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(MomentoKeys.DURABILITY, PersistentDataType.LONG, durability);
        data.set(MomentoKeys.MAX_DURABILITY, PersistentDataType.LONG, durability);

        item.setItemMeta(meta);
        return item;
    }

    public static long calculateDurabilityWithUnbreaking(long durability, int duraEnch)
    {
        if (duraEnch > 0) {
            double reductionChance = 100.0 / (duraEnch + 1);
            if (Math.random() < reductionChance / 100.0)
                durability--;

        } else
            durability--;

        return durability;
    }
}
