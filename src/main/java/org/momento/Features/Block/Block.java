package org.momento.Features.Block;

import java.io.Serializable;
import java.util.List;
import org.bukkit.Location;
import org.momento.Momento;

public class Block implements Serializable, Cloneable
{

    private List<? extends BlockComponent> blockComponent;
    private org.bukkit.block.Block block;
    private Location location;

    public Block(List<? extends BlockComponent> blockComponent)
    {
        this.blockComponent = blockComponent;
    }
    
    public void push(Location location)
    {
        this.block = location.getBlock();
        this.location = location;

        initComponents();
    }

    public void clone(Location location) 
    {
        try {
            Block clone = (Block) this.clone();
            clone.push(location);

            // implement item (block) display entity 
            
            Momento.blocks.blocks.put(location, clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents()
    {
        for (BlockComponent itemComponent : blockComponent)
        {
            block = itemComponent.init(block);
        }
    }

}
