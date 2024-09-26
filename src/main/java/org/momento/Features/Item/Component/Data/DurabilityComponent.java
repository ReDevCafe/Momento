package org.momento.Features.Item.Component.Data;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.Item.ItemComponent;

public class DurabilityComponent extends ItemComponent
{
    public long durability;
    public long maxDurability;

    public DurabilityComponent() {}

    @Override
    public void param(ConfigurationSection section) {
        this.durability = this.maxDurability = section.getLong("durability");   
    }

    @Override
    public ItemStack init(ItemStack itemStack) {
        return itemStack;
    }
}
