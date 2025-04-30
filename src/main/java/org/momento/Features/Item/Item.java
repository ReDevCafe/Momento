package org.momento.Features.Item;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Data.MomentoKeys;
import org.momento.Momento;

public class Item implements Serializable 
{
    public final String                         identifier; 
    private final List<? extends ItemComponent> objectComponent;


    private String                              uuid;
    private transient ItemStack                 itemStack;

    // FIXME: Probably should have his own component, but for now it's fine and reduce calls
    public  Map<String, String>                 tags = new HashMap<>();



    public Item(String identifier, List<? extends ItemComponent> objectComponent)
    {
        this.objectComponent = objectComponent;
        this.identifier = identifier;
        this.uuid = UUID.randomUUID().toString();

        this.itemStack = new ItemStack(Material.STONE);
        initComponents();

        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;

        meta.getPersistentDataContainer().set(
                MomentoKeys.SIGNATURE,
                PersistentDataType.STRING, uuid
        );

        itemStack.setItemMeta(meta);
    }

    public Item copy()
    {
        Item newItem = new Item(identifier, this.objectComponent);
        newItem.uuid = UUID.randomUUID().toString();
        newItem.itemStack = this.itemStack.clone();
        newItem.tags = this.tags;
    
        ItemMeta meta = newItem.itemStack.getItemMeta();
        assert meta != null;
    
        meta.getPersistentDataContainer().set(
                MomentoKeys.SIGNATURE,
                PersistentDataType.STRING, newItem.uuid
        );
    
        newItem.itemStack.setItemMeta(meta);
        Momento.items.items.put(newItem.uuid, newItem);
        return newItem;
    }
    
    
    private void initComponents()
    {
        for (ItemComponent itemComponent : objectComponent)
        {
            itemStack = itemComponent.init(itemStack);
        }
    }

    public <T extends ItemComponent> T findComponentByType(Class<T> type) {
        for (ItemComponent component : objectComponent) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        return null;
    }

    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    public String getUuid(){
        return uuid;
    }

    @Override
    public String toString() {
        String result = String.format("§d[§bidentifier: §6%s§f, §buuid: §6%s§d]§r\n", identifier, uuid); 

        if(!objectComponent.isEmpty()) 
        {
            result += "§a->>> Components:\n§r";
            for (ItemComponent itemComponent : objectComponent) 
            {
                result += String.format("§a >  %s\n", itemComponent.toString());
            }
        }

        if(!tags.isEmpty()) 
        {
            result += "§a->>> Tags:\n§r";
            for (Map.Entry<String, String> entry : tags.entrySet()) 
            {
                result += String.format("§a >  %s: %s\n", entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}
