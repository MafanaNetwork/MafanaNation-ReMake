package me.TahaCheji.gameEvent;

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
        Main.getInstance().getGamePlayer(player).onQuit();
    }
}
