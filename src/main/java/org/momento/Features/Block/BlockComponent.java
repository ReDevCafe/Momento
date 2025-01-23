package org.momento.Features.Block;

import java.io.Serializable;
import org.bukkit.configuration.ConfigurationSection;

public abstract class BlockComponent implements Serializable 
{
    public abstract org.bukkit.block.Block init(org.bukkit.block.Block blockData);
    public abstract void param(ConfigurationSection section);
}
