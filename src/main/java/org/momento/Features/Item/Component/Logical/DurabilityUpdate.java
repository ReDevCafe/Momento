package org.momento.Features.Item.Component.Logical;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.momento.Features.Item.Component.Data.DurabilityComponent;
import org.momento.Features.Item.Item;
import org.momento.Features.Item.ItemLogic;

import java.util.Arrays;

public class DurabilityUpdate implements ItemLogic {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T run(Item item, ItemStack stack) {
        DurabilityComponent durabilityComponent = item.findComponentByType(DurabilityComponent.class);
        if (durabilityComponent == null) return null;

        ItemMeta shieldMeta = stack.getItemMeta();
        if (shieldMeta == null) return null;

        int duraEnch = shieldMeta.getEnchants().getOrDefault(Enchantment.DURABILITY, 0);
        if (duraEnch > 0) 
            if (Math.random() < (100.0 / (duraEnch + 1)) / 100.0)
                durabilityComponent.durability--;
        else
            durabilityComponent.durability--;

        shieldMeta.setLore(Arrays.asList("", "§fDurability: " + durabilityComponent.durability + " / " + durabilityComponent.maxDurability));
        stack.setItemMeta(shieldMeta);

        if (durabilityComponent.durability <= 0) return (T) Boolean.TRUE;

        return (T) Boolean.FALSE;
    }
}
