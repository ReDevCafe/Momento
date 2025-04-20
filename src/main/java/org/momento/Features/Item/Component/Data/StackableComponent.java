package org.momento.Features.Item.Component.Data;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.Item.ItemComponent;

public class StackableComponent extends ItemComponent {

    public int maxStackSize = 64;
    public int currentStackSize = 1;

    public StackableComponent() {}

    public StackableComponent(int maxStackSize, int currentStackSize) {
        this.maxStackSize = maxStackSize;
        this.currentStackSize = currentStackSize;
    }

    @Override
    public ItemStack init(ItemStack itemStack) {
        itemStack.setAmount(this.currentStackSize);

        return itemStack;
    }

    @Override
    public void param(ConfigurationSection section) {
        this.maxStackSize = section.getInt("max-stack-size", 64);
        this.currentStackSize = section.getInt("default-stack-size", 1);
    }

    

    @Override
    public String toString() 
    {
        return String.format("current stack size: %d, max stack size: %d", this.currentStackSize, this.maxStackSize);
    }
}
