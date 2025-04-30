package org.momento.Features.Block.Component;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;

public class Block implements Serializable 
{

    private final List<? extends BlockComponent> blockComponent;
    private BlockData block;
    private String uuid;
    private static Location location;

    public Block(List<? extends BlockComponent> blockComponent)
    {
        this.blockComponent = blockComponent;
        this.uuid = UUID.randomUUID().toString();

        initComponents();
    }

    private void initComponents()
    {
        for (BlockComponent itemComponent : blockComponent)
        {
            block = itemComponent.init(block);
        }
    }

}
