package org.momento.Features.Item.Component;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.momento.Features.Item.ItemComponent;

public class ItemStackComponent implements ItemComponent
{
    private final String name;
    private final Material material;
    private final int modelData;

    public ItemStackComponent(String name, Material material, int modelData)
    {
        this.name = name;
        this.material = material;
        this.modelData = modelData;
    }

    @Override
    public ItemStack init(ItemStack itemStack)
    {
        if(itemStack != null || name == null || material.isAir()) return itemStack;

        itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(modelData);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
