package org.momento.Features.Item;

import org.bukkit.inventory.ItemStack;

public interface ItemLogic
{
    
    <T> T run(Item item, ItemStack stack);

    <T, E> T run(Item item, ItemStack stack, E additional);
}
