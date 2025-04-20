package org.momento.Features.Block;

public interface BlockLogic 
{
    <T> T run(Block block, org.bukkit.block.Block blockData);

    // Avoid to redefine this method in every implementation class
    default <T, E> T run(Block block, org.bukkit.block.Block blockData, E additional) 
    {
            return run(block, blockData);
    }
}
