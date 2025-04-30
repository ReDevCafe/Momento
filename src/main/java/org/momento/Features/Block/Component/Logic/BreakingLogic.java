package org.momento.Features.Block.Component.Logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
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
import org.momento.Features.Block.Component.Data.BlockDataComponent;
import org.momento.Features.Item.Item;
import org.momento.Momento;

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

        /**  With lycoris:
         *   blockData.breakNaturally();
         *   Without lycoris:
         */
        breakNaturally(block, blockData);

        display.remove();
        // Happy end :) (no jk)

        return (T) Boolean.FALSE;
    }

    private void breakNaturally(Block block, org.bukkit.block.Block blockData)
    {
        Location location = blockData.getLocation();
        World world = location.getWorld();
        if (world == null) return;

        Collection<ItemStack> drops = new ArrayList<>();
        Material blockType = blockData.getType();
        
        BlockDataComponent bData = block.findComponentByType(BlockDataComponent.class);
        if(bData != null) 
        {
            Map<String, Item> itemsList = Momento.factory_item.itemsList;
            
            for(String object : bData.drop)
                drops.add(itemsList.get(object).copy().getItemStack());
        }

        blockData.setType(Material.AIR);
        for (ItemStack drop : drops) 
            world.dropItemNaturally(location, drop);

        world.spawnParticle(Particle.BLOCK_CRACK, location.add(0.5, 0.5, 0.5), 30, 0.3, 0.3, 0.3, blockType.createBlockData());
        world.playSound(location, blockType.createBlockData().getSoundGroup().getBreakSound(), 1.0f, 1.0f);

        Momento.blocks.blocks.remove(location);
    }
    
}
