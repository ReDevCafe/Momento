package org.momento.Features.Item;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.momento.Momento;

import java.util.List;

public class Item {
    private final List<? extends ItemComponent> objectComponent;
    private ItemStack itemStack;

    public Item(List<? extends ItemComponent> objectComponent)
    {
        this.objectComponent = objectComponent;
        initComponents();
    }

    private void initComponents()
    {
        for (ItemComponent itemComponent : objectComponent)
        {
            itemStack = itemComponent.init(itemStack);
            Bukkit.getPluginManager().registerEvents(itemComponent, Momento.plugin);
        }
    }

    public ItemStack getItemStack() {
        return itemStack.clone();
    }
}
