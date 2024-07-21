package org.momento.Features.Item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Data.MomentoKeys;
import org.momento.Momento;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Item implements Serializable {
    private final List<? extends ItemComponent> objectComponent;
    private final String uuid;
    private transient ItemStack itemStack;

    public Item(List<? extends ItemComponent> objectComponent)
    {
        this.objectComponent = objectComponent;
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

        Momento.items.items.put(uuid, this);
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
}
