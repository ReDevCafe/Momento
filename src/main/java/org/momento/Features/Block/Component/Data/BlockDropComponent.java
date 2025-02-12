package org.momento.Features.Block.Component.Data;

import java.util.Collection;

import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.momento.Features.Block.BlockComponent;

public class BlockDropComponent extends BlockComponent {

    public Collection<String> drop;

    @Override
    public Block init(Block blockData) {
        System.out.println("LALALALALALALAL");
        return blockData;
    }

    @Override
    public void param(ConfigurationSection section)
    {
        drop = section.getStringList("drop");
    }
    
}
