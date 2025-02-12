package org.momento.Features.Block;

import java.io.Serializable;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.momento.Momento;
import org.momento.Features.Item.ItemComponent;

public class Block implements Serializable, Cloneable
{

    private List<? extends BlockComponent> blockComponent;
    private org.bukkit.block.Block block;
    private Location location;
    
    public ItemDisplay blockDisplay;

    public Block(List<? extends BlockComponent> blockComponent)
    {
        this.blockComponent = blockComponent;
    }
    
    public void push(Location location)
    {
        this.block = location.getBlock();
        this.location = location;

        initComponents();
    }

    public void clone(Location location) 
    {
        try {
            Block clone = (Block) this.clone();
            clone.push(location);

            // implement item (block) display entity 
            blockDisplay = location.getWorld().spawn(location, ItemDisplay.class);

            ItemStack displayBlock = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta meta = displayBlock.getItemMeta();
            meta.setCustomModelData(123);

            displayBlock.setItemMeta(meta);

            blockDisplay.setItemStack(displayBlock);
            blockDisplay.setTransformation(new Transformation(
                new Vector3f(.5f, .5f, .5f),
                new Quaternionf(0, 0, 0, 1),
                new Vector3f(1.001f, 1.001f, 1.001f),
                new Quaternionf(0,0,0,1)
            ));
            blockDisplay.setBrightness(new Brightness(4,15));
            blockDisplay.setBillboard(Billboard.FIXED);
            
            clone.blockDisplay = blockDisplay;
            Momento.blocks.blocks.put(location, clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents()
    {
        for (BlockComponent itemComponent : blockComponent)
        {
            block = itemComponent.init(block);
        }
    }

     public <T extends BlockComponent> T findComponentByType(Class<T> type) {
        for (BlockComponent component : blockComponent) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[L=\""+location+"\", CS=\""+blockComponent.size()+"\",]";
    }
}
