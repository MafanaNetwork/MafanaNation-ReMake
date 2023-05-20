package me.TahaCheji.events;

import me.TahaCheji.Main;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GamePlayerJoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        for (GamePlayer gamePlayer : Main.getInstance().getGamePlayers()){
            if(!gamePlayer.getName().equals(player.getDisplayName())){
                Main.getInstance().getGamePlayers().add(new GamePlayer(player));
            }
        }
    }
}
