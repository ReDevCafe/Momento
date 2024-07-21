package org.momento.Features.Item;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public abstract class ItemComponent implements Serializable {
    public abstract ItemStack init(ItemStack itemStack);
    public abstract void param(ConfigurationSection section);
}
