package org.momento.Features.Item;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.Map;

public class ItemComponent implements Serializable {
    public Map<String, Class<?>> configValues() { return null;};
    public ItemStack init(ItemStack itemStack) { return null; };
}
