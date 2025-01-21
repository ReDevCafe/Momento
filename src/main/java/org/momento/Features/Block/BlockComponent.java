package org.momento.Features.Block;

import java.io.Serializable;

import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;

public abstract class BlockComponent implements Serializable 
{
    public abstract BlockData init(BlockData blockData);
    public abstract void param(ConfigurationSection section);
}
