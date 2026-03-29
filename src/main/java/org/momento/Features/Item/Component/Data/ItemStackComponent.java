package org.momento.Features.Item.Component.Data;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.momento.Features.Item.ItemComponent;


public class ItemStackComponent extends ItemComponent
{
    private String name;
    private Material material;
    private int modelData;

    public ItemStackComponent() {}

    @Override
    public void param(ConfigurationSection section) {
        this.name = section.getString("name");
        if(this.name == null) this.name = "object";

        this.material = Material.getMaterial(section.getString("material"));
        if(this.material == null) this.material = Material.STONE;

        this.modelData = section.getInt("model-data", -1);
    }

    @Override
    public ItemStack init(ItemStack itemStack)
    {
        if(name == null || material.isAir()) return itemStack;
        
        itemStack.setType(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.setDisplayName(ChatColor.RESET+name);
        itemMeta.setCustomModelData(modelData);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public String toString() {
        return "§dItemStack §6{" +
                "\n§b   name§6: §c" + name +
                "\n§b   material§6: §c" + material.toString() +
                "\n§b   modelData§6: §c" + modelData +
                "\n§6}";
    }
}
