package me.TahaCheji;

import me.TahaCheji.adminCommand.PlayerInventorySee;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public List<GamePlayer> gamePlayers = new ArrayList<>();
    public static Main instance;

    @Override
    public void onEnable() {
       instance = this;
        String packageName = getClass().getPackage().getName();
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        getCommand("mf").setExecutor(new PlayerInventorySee());
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

    public GamePlayer getGamePlayer(OfflinePlayer player){
        for (GamePlayer gamePlayer : getGamePlayers()){
            if(gamePlayer.getName().equals(player.getName())){
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
