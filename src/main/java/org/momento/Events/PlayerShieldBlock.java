package org.momento.Events;

import org.bukkit.Material;
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
import org.momento.Data.MomentoKeys;

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
            shield = equipment.getItemInMainHand();
        else if (equipment.getItemInOffHand().getType() == Material.SHIELD)
            shield = equipment.getItemInOffHand();
        else return;

        ItemMeta meta = shield.getItemMeta();
        assert meta != null;

        PersistentDataContainer data = meta.getPersistentDataContainer();
        if (!data.has(MomentoKeys.DURABILITY, PersistentDataType.LONG)) return;

        long durability = data.get(MomentoKeys.DURABILITY, PersistentDataType.LONG);
        long maxDurability = data.get(MomentoKeys.MAX_DURABILITY, PersistentDataType.LONG);
        int duraEnch = meta.getEnchants().getOrDefault(Enchantment.DURABILITY, 0);

        if (duraEnch > 0) {
            if (Math.random() < (100.0 / (duraEnch + 1)) / 100.0)
                durability--;
        } else
            durability--;

        data.set(MomentoKeys.DURABILITY, PersistentDataType.LONG, durability);
        meta.setLore(Arrays.asList("", "§fDurability: "+durability + " / " + maxDurability));
        shield.setItemMeta(meta);

        event.setCancelled(true);

        if (durability > 0) return;
        player.getInventory().remove(shield);
    }
}
