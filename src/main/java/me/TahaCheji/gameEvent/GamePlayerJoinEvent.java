package me.TahaCheji.gameEvent;

import me.TahaCheji.Main;
import me.TahaCheji.gameItems.Weapons.Sword;
import me.TahaCheji.gameItems.test;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GamePlayerJoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws Exception {
        Player player = event.getPlayer();

        Main.getInstance().getGamePlayers().add(new GamePlayer(player));
    }
}
