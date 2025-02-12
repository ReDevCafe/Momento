package org.momento.Features.Block;

import java.io.Serializable;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.momento.Momento;

public class Block implements Serializable, Cloneable
{

    private final List<? extends BlockComponent> blockComponent;
    private org.bukkit.block.Block block;
    private Location location;
    
    public ItemDisplay blockDisplay;
    public String identifier;

    @SuppressWarnings("LeakingThisInConstructor")
    public Block(String identifier, List<? extends BlockComponent> blockComponent)
    {
        this.identifier = identifier;
        this.blockComponent = blockComponent;

        for (BlockComponent startComponent : blockComponent)
            if (startComponent.loadAtStart())
                block = startComponent.init(this, block);
    }
    
    public void push(Location location)
    {
        this.block = location.getBlock();
        this.location = location;

        initComponents();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void clone(Location location) 
    {
        try {
            Block clone = (Block) this.clone();
            clone.push(location);

            // implement item (block) display entity 
            World world = location.getWorld();
            if(world == null) throw new IllegalStateException("World is null (???)");

            blockDisplay = world.spawn(location, ItemDisplay.class);

            ItemStack displayBlock = new ItemStack(Material.KNOWLEDGE_BOOK);
            ItemMeta meta = displayBlock.getItemMeta();
            assert meta != null;
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
        } catch (CloneNotSupportedException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void initComponents()
    {
        for (BlockComponent component : blockComponent)
            if(!component.loadAtStart())
                block = component.init(this, block);
    }

    public <T extends BlockComponent> T findComponentByType(Class<T> type)
    {
        for (BlockComponent component : blockComponent)
            if (type.isInstance(component))
                return type.cast(component);
        
        return null;
    }

    @Override
    public String toString() {
        return "[L=\""+location+"\", CS=\""+blockComponent.size()+"\",]";
    }
}
