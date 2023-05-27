package me.TahaCheji.gameEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class GamePlayerEatEvent implements Listener {

    @EventHandler
    public void playerEat(PlayerItemConsumeEvent event) {
        event.setCancelled(true);
    }
}
