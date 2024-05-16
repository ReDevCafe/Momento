package org.momento.Features;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.momento.Momento;

import java.util.UUID;

public abstract class BodyComestic extends CraftItemDisplay implements Runnable {

    protected final UUID playerUUID;

    private boolean spawned = false;

    public BodyComestic(Player player) {
        super((CraftServer) Momento.plugin.getServer(), new Display.ItemDisplay(EntityTypes.af, ((CraftWorld) player.getWorld()).getHandle()));
        playerUUID = player.getUniqueId();
        setItemStack(getDisplayItem());
        setTeleportDuration(0);
        spawn();
    }

    public void spawn() {
        if (spawned) return;
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return;
        CraftWorld world = (CraftWorld) player.getWorld();
        world.addEntity(this);
        teleportToPlayer();
        spawned = true;
    }

    public UUID getPlayerUUID() { return playerUUID; }
    public boolean hasSpawned() { return spawned; }

    public void despawn() {
        if (!spawned) return;
        this.remove();
        spawned = false;
    }

    public void teleportToPlayer() {
        if (!spawned) return;
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return;
        teleport(player.getLocation().getBlock().getLocation().add(getOffset()));
    }

    @Override
    public void run() {
        if (!spawned) return;
        teleportToPlayer();
        tick();
    }

    protected void tick() {}

    public abstract Vector getOffset();
    public abstract ItemStack getDisplayItem();
}
