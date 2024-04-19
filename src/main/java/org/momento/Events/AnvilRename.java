package org.momento.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.momento.Features.EmojiSystem;

public class AnvilRename implements Listener{
    @EventHandler
    public void onAnvilRename(InventoryClickEvent event) {
        if(event.getRawSlot() != 2) return;

        ItemStack renamedItem = event.getCurrentItem();
        if( renamedItem == null || !renamedItem.hasItemMeta()) return;

        ItemMeta meta = renamedItem.getItemMeta();
        meta.setDisplayName(EmojiSystem.EmojiStringReplacer(meta.getDisplayName()));
        renamedItem.setItemMeta(meta);
    }
}
