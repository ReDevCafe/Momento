package org.momento.Features.Item;

import org.bukkit.inventory.ItemStack;

public interface ItemLogic
{
    <T> T run(Item item, ItemStack stack);
}
