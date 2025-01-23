package org.momento.Features.Block.Component.Data;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.momento.Features.Block.BlockComponent;

public class BlockDataComponent extends BlockComponent {

    private Material material;

    @Override
    public Block init(Block blockData) {
        blockData.setType(material);

        return blockData;
    }

    @Override
    public void param(ConfigurationSection section) {
        this.material = Material.getMaterial(section.getString("material"));
        if(this.material == null) this.material = Material.STONE;

    }
    
}
