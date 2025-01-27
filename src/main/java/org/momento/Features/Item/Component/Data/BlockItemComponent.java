package org.momento.Features.Item.Component.Data;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.momento.Features.Block.Block;
import org.momento.Features.Block.BlockFactory;
import org.momento.Features.Item.ItemComponent;

public class BlockItemComponent extends ItemComponent {

    public Block block;
    private int modelData;

    public BlockItemComponent() {}
    @Override
    public ItemStack init(ItemStack itemStack) {
        itemStack.setType(Material.KNOWLEDGE_BOOK);
        
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(modelData);

        meta.setDisplayName("§rAAAAAAAAAAAAAAAAAAAAA");
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    @Override
    public void param(ConfigurationSection section) {
        String identifier = section.getString("identifier"); 
        if (identifier == null) throw new IllegalStateException("Invalid identifier, missing identifier");

        Block gBlock = BlockFactory.blocksList.get(identifier);
        if (gBlock == null) throw new IllegalStateException("Block not found for identifier: " + identifier);
        block = gBlock;

        modelData = section.getInt("model-data", 0);
    }
    
}
