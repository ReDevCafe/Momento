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
import org.momento.Features.Item.Component.Data.DurabilityComponent;
import org.momento.Features.Item.Component.Logical.DurabilityUpdate;
import org.momento.Features.Item.Item;
import org.momento.Features.Item.ItemLogic;
import org.momento.Momento;

import java.util.Arrays;

public class PlayerShieldBlock implements Listener {

    private final ItemLogic durabilitySystem;

    public PlayerShieldBlock() {
        this.durabilitySystem = new DurabilityUpdate();
    }

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

        ItemMeta shieldMeta = shield.getItemMeta();
        assert shieldMeta != null;
        PersistentDataContainer dataContainer = shieldMeta.getPersistentDataContainer();

        Item momentoItem = Momento.items.items.get(dataContainer.get(MomentoKeys.SIGNATURE, PersistentDataType.STRING));
        if(momentoItem == null) return;

        Boolean broken = durabilitySystem.run(momentoItem, shield);
        event.setCancelled(true);

        if (!Boolean.TRUE.equals(broken)) return;
        player.getInventory().remove(shield);
        Momento.items.items.remove(momentoItem.getUuid());
    }
}
