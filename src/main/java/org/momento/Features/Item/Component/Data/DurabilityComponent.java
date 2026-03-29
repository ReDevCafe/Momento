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
        this.durability = this.maxDurability = section.getLong("value");   
    }

    @Override
    public ItemStack init(ItemStack itemStack) {
        return itemStack;
    }

    @Override
    public String toString() {
        return "§dDurability §6{" +
                "\n§b   durability§6: §c" + durability +
                "\n§b   maxDurability§6: §c" + maxDurability +
                "\n§6}";
    }
}
