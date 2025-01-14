package org.momento.Features.Item;

import java.io.Serializable;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class ItemComponent implements Serializable 
{
    public abstract ItemStack init(ItemStack itemStack);
    public abstract void param(ConfigurationSection section);
}
