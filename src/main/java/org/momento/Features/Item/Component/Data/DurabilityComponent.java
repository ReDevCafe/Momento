package org.momento.Features.Item.Component.Data;

import org.bukkit.inventory.ItemStack;
import org.momento.Features.Item.ItemComponent;

import java.util.Map;

public class DurabilityComponent extends ItemComponent
{
    public long durability;
    public long maxDurability;

    public DurabilityComponent(long maxDurability)
    {
        this.durability = this.maxDurability = maxDurability;
    }

    @Override
    public Map<String, Class<?>> configValues() {
        return Map.ofEntries(
            Map.entry("durability", long.class)
        );
    }

    @Override
    public ItemStack init(ItemStack itemStack) {
        return itemStack;
    }
}
