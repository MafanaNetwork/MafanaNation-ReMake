package me.TahaCheji.events;

import me.TahaCheji.Main;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Iterator;

public class GamePlayerLeaveEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Iterator<GamePlayer> iterator = Main.getInstance().getGamePlayers().iterator();
        while (iterator.hasNext()) {
            GamePlayer gamePlayer = iterator.next();
            if (gamePlayer == null) {
                continue;
            }
            if (gamePlayer.getName().equals(player.getDisplayName())) {
                iterator.remove();
            }
        }
    }
}
