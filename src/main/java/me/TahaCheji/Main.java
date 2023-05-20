package me.TahaCheji;

import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private List<GamePlayer> gamePlayers = new ArrayList<>();
    public static Main instance;

    @Override
    public void onEnable() {
       instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GamePlayer getGamePlayer(Player player){
        for (GamePlayer gamePlayer : getGamePlayers()){
            if(gamePlayer.getName().equals(player.getDisplayName())){
                return gamePlayer;
            }
        }
        return null;
    }
    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public static Main getInstance() {
        return instance;
    }
}
