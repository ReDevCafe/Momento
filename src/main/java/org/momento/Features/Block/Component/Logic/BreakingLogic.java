package org.momento.Features.Block.Component.Logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;
import org.momento.Features.Block.Block;
import org.momento.Features.Block.BlockLogic;
import org.momento.Features.Block.Component.Data.BlockDropComponent;
import org.momento.Features.Item.Implements.ItemFactory;

public class BreakingLogic implements BlockLogic {

    @Override
    public <T> T run(Block block, org.bukkit.block.Block blockData) {
        ItemDisplay display;
        if(block.blockDisplay == null) 
        {
            Collection<Entity> entity = blockData.getWorld().getNearbyEntities(BoundingBox.of(blockData.getLocation(), 2, 2, 2), Predicate.isEqual(ItemDisplay.class));
            if(entity.isEmpty())
                throw new IllegalStateException("No entities");

            display = (ItemDisplay) entity.iterator().next();
        }
        else display = block.blockDisplay;

        // With lycoris:
        // blockData.breakNaturally();
        // Without lycoris:
        breakNaturally(block, blockData);

        display.remove();
        // Happy end :) (no jk)

        return null;
    }

    private void breakNaturally(Block block, org.bukkit.block.Block blockData)
    {
        Location loc = blockData.getLocation();
        World world = loc.getWorld();
        if (world == null) return;


        Collection<ItemStack> drops = new ArrayList<ItemStack>();
        Material blockType = blockData.getType();
        
        BlockDropComponent blockDrop = block.findComponentByType(BlockDropComponent.class);
        if(blockDrop != null) 
        {
            for(String object : blockDrop.drop)
                drops.add(ItemFactory.itemsList.get(object).copy().getItemStack());
        }
        blockData.setType(Material.AIR);

        for (ItemStack drop : drops) 
            world.dropItemNaturally(loc, drop);

        world.spawnParticle(Particle.BLOCK_CRACK, loc.add(0.5, 0.5, 0.5), 30, 0.3, 0.3, 0.3, blockType.createBlockData());
        world.playSound(loc, blockType.createBlockData().getSoundGroup().getBreakSound(), 1.0f, 1.0f);
    }
    
}
