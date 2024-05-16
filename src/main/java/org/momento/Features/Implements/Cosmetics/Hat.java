package org.momento.Features.Implements.Cosmetics;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.momento.Features.BodyComestic;
import org.momento.Features.ItemFeature;

public class Hat extends BodyComestic {

    private final Vector offset = new Vector(0.5, 2, 0.5);
    private final ItemStack item = new ItemFeature(
            "",
            Material.STONE,
            1,
            1000
            ).createItem();

    public Hat(Player player) {
        super(player);
    }

    @Override
    public Vector getOffset() {
        return offset;
    }

    @Override
    public ItemStack getDisplayItem() {
        return item;
    }
}
