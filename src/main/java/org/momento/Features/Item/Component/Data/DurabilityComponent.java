package org.momento.Features.Item.Component.Data;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.Item.ItemComponent;

@PrismClass
public class DurabilityComponent extends ItemComponent
{
    @PrismAnot(
        title="Durability", 
        description="Durability of the item"
    ) 
    public long durability;
    @PrismAnot(title="Max Durability", description="Max durability of the item")
    public long maxDurability;

    public DurabilityComponent() {}

    @PrismAnot(description="Debug $!s1")
    @Override
    public void param(ConfigurationSection section) 
    {
        this.durability = this.maxDurability = section.getLong("durability");   
    }

    @Override
    public ItemStack init(ItemStack itemStack)
    {
        return itemStack;
    }

    @Override
    public String toString() {
        return String.format("§cdurability: §6%s §fon §6%s§r", this.durability, this.maxDurability);
    }
}
