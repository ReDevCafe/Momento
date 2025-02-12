package org.momento.Features.Block;

import java.io.Serializable;

import org.bukkit.configuration.ConfigurationSection;

public abstract class BlockComponent implements Serializable 
{   
    // For compatibility with items or other shit like that
    public Boolean loadAtStart() { return false; };
    public abstract org.bukkit.block.Block init(Block block, org.bukkit.block.Block blockData);
    public abstract void param(ConfigurationSection section);
}
