package org.momento.Events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.momento.Data.MomentoKeys;
import org.momento.Features.Block.Block;
import org.momento.Features.Item.Component.Data.StackableComponent;
import org.momento.Features.Item.Item;
import org.momento.Momento;

// this is probably breaking ECS rules  
public class BlockPlacing implements Listener 
{
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onClickingWithBlock(PlayerInteractEvent event)
    {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if(item.getType().isAir()) item = event.getPlayer().getInventory().getItemInOffHand();
        if(item.getType().isAir()) return;

        PersistentDataContainer dataContainer = item.getItemMeta().getPersistentDataContainer();
        String signature = dataContainer.get(MomentoKeys.SIGNATURE, PersistentDataType.STRING);
        if(signature == null) return;

        Item momentoItem = Momento.items.items.get(signature);
        if(momentoItem == null) return;

        if(!momentoItem.tags.containsKey("is-placeable")) return;
        event.setCancelled(true);

        Block block = Momento.factory_block.blocksList.get(momentoItem.identifier);
        if(block == null) return;

        Location possiblePlaceLocation = event.getClickedBlock().getLocation();
        switch(event.getBlockFace())
        {
            case NORTH:
                possiblePlaceLocation = possiblePlaceLocation.add(new Vector(0,0,1));
                break;
            case SOUTH:
                possiblePlaceLocation = possiblePlaceLocation.add(new Vector(0,0,-1));
                break;
            case WEST:
                possiblePlaceLocation = possiblePlaceLocation.add(new Vector(-1,0,0));
                break;
            case EAST:
                possiblePlaceLocation = possiblePlaceLocation.add(new Vector(1,0,0));
                break;
            case UP:
                possiblePlaceLocation = possiblePlaceLocation.add(new Vector(0,1,0));
                break;
            case DOWN:
                possiblePlaceLocation = possiblePlaceLocation.add(new Vector(0,-1,0));
                break;
            default:
                event.getPlayer().sendMessage("How? HOW??????");
                break;
        }

        // Stupid math end

        block.clone(possiblePlaceLocation);
        StackableComponent stackableComponent = momentoItem.findComponentByType(StackableComponent.class);
        if(stackableComponent == null) return;

        item.setAmount(item.getAmount() - 1);
        stackableComponent.currentStackSize--;

        if(stackableComponent.currentStackSize == 0) Momento.items.items.remove(signature);
        event.getPlayer().updateInventory();
    }
}
