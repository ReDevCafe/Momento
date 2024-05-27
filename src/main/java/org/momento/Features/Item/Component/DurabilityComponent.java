package org.momento.Features.Item.Component;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Data.MomentoKeys;
import org.momento.Features.Item.ItemComponent;

public class DurabilityComponent implements ItemComponent
{
    private final long durability;

    public DurabilityComponent(long durability)
    {
        this.durability = durability;
    }

    @Override
    public ItemStack init(ItemStack itemStack) {
        if (itemStack == null || durability <= 0) return itemStack;
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;

        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        dataContainer.set(MomentoKeys.DURABILITY, PersistentDataType.LONG, durability);
        dataContainer.set(MomentoKeys.MAX_DURABILITY, PersistentDataType.LONG, durability);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
