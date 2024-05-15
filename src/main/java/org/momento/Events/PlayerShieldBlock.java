package org.momento.Events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Features.ItemFeature;
import org.momento.Momento;

import java.util.Arrays;
public class PlayerShieldBlock implements Listener {

    @EventHandler
    public void onPlayerBlock(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        EntityEquipment equipment = player.getEquipment();
        if (!player.isBlocking() || equipment == null || (equipment.getItemInMainHand().getType() != Material.SHIELD &&  equipment.getItemInOffHand().getType() != Material.SHIELD)) return;

        ItemStack shield;
        if(equipment.getItemInMainHand().getType() == Material.SHIELD)
            shield = equipment.getItemInOffHand();
        else if (equipment.getItemInOffHand().getType() == Material.SHIELD)
            shield = equipment.getItemInOffHand();
        else return;

        ItemMeta meta = shield.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        if (!data.has(new NamespacedKey(Momento.plugin, "momento_durability"), PersistentDataType.LONG)) return;

        long durability = data.get(new NamespacedKey(Momento.plugin, "momento_durability"), PersistentDataType.LONG);
        long maxDurability = data.get(new NamespacedKey(Momento.plugin, "momento_maxdurability"), PersistentDataType.LONG);

        durability = ItemFeature.calculateDurabilityWithUnbreaking(
                durability, meta.getEnchants().getOrDefault(Enchantment.DURABILITY, 0)
        );

        data.set(new NamespacedKey(Momento.plugin, "momento_durability"), PersistentDataType.LONG, durability);
        meta.setLore(Arrays.asList("", "§fDurability: "+durability + " / " + maxDurability));
        shield.setItemMeta(meta);

        event.setCancelled(true);

        if (durability > 0) return;
        player.getInventory().remove(shield);
    }
}
