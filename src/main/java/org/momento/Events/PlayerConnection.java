package org.momento.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.momento.Features.Implements.Cosmetics.Hat;
import org.momento.Momento;

import java.util.Optional;

public class PlayerConnection implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Momento.hats.add(new Hat(player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Optional<Hat> possibleHat = Momento.hats
                .stream()
                .parallel()
                .filter(actualHat -> actualHat.getPlayerUUID() == player.getUniqueId())
                .findFirst();
        if (!possibleHat.isPresent()) return;
        Hat hat = possibleHat.get();
        hat.despawn();
        Momento.hats.remove(hat);
    }
}
