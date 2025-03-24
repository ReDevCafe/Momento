package org.momento.Features.Block;

public interface BlockLogic 
{
    <T> T run(Block block, org.bukkit.block.Block blockData);
    <T, E> T run(Block block, org.bukkit.block.Block blockData, E additional);
}
