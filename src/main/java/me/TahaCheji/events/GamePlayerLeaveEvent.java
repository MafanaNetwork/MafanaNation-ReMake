package me.TahaCheji.events;

import me.TahaCheji.Main;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GamePlayerLeaveEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerQuitEvent event){
        Player player = event.getPlayer();
        for (GamePlayer gamePlayer : Main.getInstance().getGamePlayers()) {
            if (gamePlayer.getName().equals(player.getDisplayName())) {
                Main.getInstance().getGamePlayers().remove(gamePlayer);
            }
        }
        }
}
