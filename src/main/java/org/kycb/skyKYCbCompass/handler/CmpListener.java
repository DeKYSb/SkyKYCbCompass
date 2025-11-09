package org.kycb.skyKYCbCompass.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kycb.skyKYCbCompass.Compass;

import java.util.HashMap;
import java.util.Map;

public class CmpListener implements Listener {

    private final Map<Player, Compass> playerCompasses = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerCompasses.put(player, new Compass(player));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Compass compass = playerCompasses.get(player);
        if (compass != null && event.getFrom().getYaw() != event.getTo().getYaw()) {
            compass.updateCompass();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerCompasses.remove(event.getPlayer());
    }
}
