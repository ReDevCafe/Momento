package org.momento.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.momento.Features.Block.Block;
import org.momento.Features.Block.BlockLogic;
import org.momento.Features.Block.Component.Logic.BreakingLogic;
import org.momento.Momento;

public class BreakingBlock implements Listener {

    private final BlockLogic breakingSystem;

    public BreakingBlock() {
        this.breakingSystem = new BreakingLogic();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) 
    {
        org.bukkit.block.Block real_block = event.getBlock();
        Block block = Momento.blocks.blocks.get(real_block.getLocation());
        if(block == null) return;
        
        breakingSystem.run(block, real_block);
    }
    
}
