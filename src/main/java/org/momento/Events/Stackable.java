package org.momento.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Data.MomentoKeys;
import org.momento.Features.Item.Component.Data.StackableComponent;
import org.momento.Features.Item.Item;
import org.momento.Momento;

public class Stackable implements Listener
{
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event)
    {
        ItemStack cursor  = event.getCursor();
        ItemStack clicked = event.getCurrentItem();

        System.out.println(String.format("CURSOR: %s CLICKED %s", cursor, clicked));

        if(cursor == null || cursor.getType().isAir() || clicked == null || clicked.getType().isAir()) return;

        ItemMeta cursorMeta = cursor.getItemMeta();
        assert cursorMeta  != null;

        ItemMeta clickedMeta = clicked.getItemMeta();
        assert clickedMeta  != null;
        
        PersistentDataContainer cursorContainer = cursorMeta.getPersistentDataContainer();
        String cursorSignature = cursorContainer.get(MomentoKeys.SIGNATURE, PersistentDataType.STRING);
        Item cursorItem = Momento.items.items.get(cursorSignature);
        if(cursorItem == null) throw new IllegalStateException();

        StackableComponent cursorStackable = cursorItem.findComponentByType(StackableComponent.class);
        if(cursorStackable == null) throw new IllegalStateException();

        PersistentDataContainer clickedContainer = clickedMeta.getPersistentDataContainer();
        Item clickedItem = Momento.items.items.get(clickedContainer.get(MomentoKeys.SIGNATURE, PersistentDataType.STRING));
        if(clickedItem == null) throw new IllegalStateException();

        if(!clickedItem.identifier.equals(cursorItem.identifier)) throw new IllegalStateException();

        StackableComponent clickedStackable = clickedItem.findComponentByType(StackableComponent.class);
        if(clickedStackable == null) throw new IllegalStateException();
        
        int maxStackSize = clickedStackable.maxStackSize;
        event.setCancelled(true);
        if(event.isLeftClick())
        {
            if(clicked.getAmount() >= maxStackSize) return;
            System.out.println(String.format("%d %d",clicked.getAmount(), cursor.getAmount()));

            int newAmount = clicked.getAmount() + cursor.getAmount();
            
            if(newAmount >= maxStackSize)
            {
                System.err.println("OUI");
                clicked.setAmount(maxStackSize);
                clickedStackable.currentStackSize = maxStackSize;
                
                newAmount = newAmount - maxStackSize;
                cursor.setAmount(newAmount);

                if(newAmount > 0)
                    cursorStackable.currentStackSize = newAmount;
                else
                    Momento.items.items.remove(cursorSignature);
            }
            else 
            {
                System.err.println("NON");

                cursor.setType(Material.AIR);
                ((Player) event.getWhoClicked()).updateInventory();
                Momento.items.items.remove(cursorSignature);

                clicked.setAmount(newAmount);
                clickedStackable.currentStackSize = newAmount;
            }
        }
        else if(event.isRightClick())
        {
            if (clicked.getAmount() >= maxStackSize) return;
            int minAmount = clicked.getAmount() + 1;
            int maxAmount = clicked.getAmount() + 1;

            clicked.setAmount(minAmount);
            clickedStackable.currentStackSize = minAmount;

            cursor.setAmount(maxAmount);
            cursorStackable.currentStackSize = maxAmount;
        }
    }
}
