package org.momento.Features.Block;

import java.io.Serializable;
import java.util.List;

import org.bukkit.Location;

public class Block implements Serializable 
{

    private final List<? extends BlockComponent> blockComponent;
    private org.bukkit.block.Block block;

    public Block(List<? extends BlockComponent> blockComponent)
    {
        block = // USE NMS 
        this.blockComponent = blockComponent;
        initComponents();
    }
    
    public void clone(Location location) 
    {
        
    }

    private void initComponents()
    {
        for (BlockComponent itemComponent : blockComponent)
        {
            block = itemComponent.init(block);
        }
    }

}
