package org.momento.Events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Momento;

import java.util.Arrays;
import java.util.Map;

public class PlayerShieldBlock implements Listener {

    @EventHandler
    public void onPlayerBlock(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        if (player.isBlocking() && player.getEquipment() != null && player.getEquipment().getItemInMainHand().getType() == Material.SHIELD) {
            ItemStack shield = player.getEquipment().getItemInMainHand();

            ItemMeta meta = shield.getItemMeta();
            if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(Momento.plugin, "momento_item"), PersistentDataType.BOOLEAN)) {
                if (meta.getPersistentDataContainer().has(new NamespacedKey(Momento.plugin, "momento_durability"), PersistentDataType.LONG)) {
                    long durability = meta.getPersistentDataContainer().get(new NamespacedKey(Momento.plugin, "momento_durability"), PersistentDataType.LONG);
                    long maxdurability = meta.getPersistentDataContainer().get(new NamespacedKey(Momento.plugin, "momento_maxdurability"), PersistentDataType.LONG);

                    int duraEnch = meta.getEnchants().getOrDefault(Enchantment.DURABILITY, 0);
                    durability -= duraEnch > 0 ? Math.max(1, (int) (Math.random() * duraEnch)) : 1;

                    if (durability <= 0) {
                        player.getInventory().remove(shield);
                        return;
                    }

                    meta.setLore(Arrays.asList("", "§fDurability "+durability + " / " + maxdurability));

                    meta.getPersistentDataContainer().set(new NamespacedKey(Momento.plugin, "momento_durability"), PersistentDataType.LONG, durability);

                    shield.setItemMeta(meta);
                    event.setCancelled(true);
                }
            }
        }
    }
}
