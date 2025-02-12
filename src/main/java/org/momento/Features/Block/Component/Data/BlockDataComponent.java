package org.momento.Features.Block.Component.Data;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.momento.Features.Block.Block;
import org.momento.Features.Block.BlockComponent;

public class BlockDataComponent extends BlockComponent {

    private Material material;
    public Collection<String> drop;

    @Override
    public org.bukkit.block.Block init(Block block, org.bukkit.block.Block blockData) {
        blockData.setType(material);

        return blockData;
    }

    @Override
    public void param(ConfigurationSection section) {
        this.material = Material.getMaterial(section.getString("material"));
        if(this.material == null) this.material = Material.STONE;

        //!!!! CAN BE NULL, THAT TOTALY NORMAL
        this.drop = section.getStringList("drop");
    }
    
}
