package org.momento.Features.Block.Component.Data;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.momento.Features.Block.Block;
import org.momento.Features.Block.BlockComponent;
import org.momento.Features.Item.Component.Data.ItemStackComponent;
import org.momento.Features.Item.Component.Data.StackableComponent;
import org.momento.Features.Item.Item;
import org.momento.Momento;

public class BlockItemComponent extends BlockComponent{
    
    private int modelData;
    private String name;

    


    @Override
    public Boolean loadAtStart() {
        return true;
    }
    @Override
    public org.bukkit.block.Block init(Block block, org.bukkit.block.Block blockData)
    {
        System.err.println("AAAAAAAAAAAAAAAAAAA");
        Item item = new Item(
            Arrays.asList(
                new ItemStackComponent(name, Material.KNOWLEDGE_BOOK, modelData),
                new StackableComponent(64, 1)
            )
        );

        Momento.factory_item.itemsList.put(block.identifier, item);
        return blockData;
    }

    @Override
    public void param(ConfigurationSection section) {
        this.name = section.getString("name");
        if(this.name == null) this.name = "object";

        modelData = section.getInt("model-data", Integer.MIN_VALUE);
        if(modelData == Integer.MIN_VALUE) throw new IllegalStateException("Invalid model data, PLEASE DEFINE MODEL DATA");
    }
    
}
