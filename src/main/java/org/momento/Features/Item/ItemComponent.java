package org.momento.Features.Item;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

                                       // not sure about the idea of extending Listener; need a second check in review maybe
public interface ItemComponent extends Listener {
    public abstract ItemStack init(ItemStack itemStack);
}
