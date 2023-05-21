package me.TahaCheji;

import me.TahaCheji.adminCommand.PlayerCoinAdmin;
import me.TahaCheji.adminCommand.PlayerInventory;
import me.TahaCheji.itemData.*;
import me.TahaCheji.playerData.GamePlayerCoins;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    public List<GamePlayer> gamePlayers = new ArrayList<>();
    public GamePlayerCoins playerGamePlayerCoins = new GamePlayerCoins();
    public Map<String, GameItem> items = new HashMap();
    public Map<Integer, GameItem> itemIDs = new HashMap();
    public static Main instance;

    private List<GameItem> gameItems = new ArrayList<>();
    private List<GameWeapons> gameWeapons = new ArrayList<>();
    private List<GameArmor> gameArmors = new ArrayList<>();
    private List<GameBow> gameBow = new ArrayList<>();
    private List<GameSpell> gameSpells = new ArrayList<>();
    private List<GameStaff> gameStaffs = new ArrayList<>();

    @Override
    public void onEnable() {
       instance = this;
       playerGamePlayerCoins.connect();
        String packageName = getClass().getPackage().getName();
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        for (Class<?> clazz : new Reflections(packageName).getSubTypesOf(GameItem.class)) {
            try {
                GameItem gameItem = (GameItem) clazz.getDeclaredConstructor().newInstance();
                gameItems.add(gameItem);
                if (gameItem instanceof GameWeapons) {
                    gameWeapons.add((GameWeapons) gameItem);
                }
                if (gameItem instanceof GameArmor) {
                    gameArmors.add((GameArmor) gameItem);
                }
                if (gameItem instanceof GameBow) {
                    gameBow.add((GameBow) gameItem);
                }
                if (gameItem instanceof GameStaff) {
                    gameStaffs.add((GameStaff) gameItem);
                }
                if (gameItem instanceof GameSpell) {
                    gameSpells.add((GameSpell) gameItem);
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException
                     | NoSuchMethodException ignored) {
            }
        }

        getCommand("mfinv").setExecutor(new PlayerInventory());
        getCommand("mf").setExecutor(new PlayerCoinAdmin());
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


    public List<GameBow> getGameBow() {
        return gameBow;
    }

    public List<GameSpell> getGameSpells() {
        return gameSpells;
    }

    public List<GameStaff> getGameStaffs() {
        return gameStaffs;
    }

    public GamePlayerCoins getPlayerGamePlayerCoins() {
        return playerGamePlayerCoins;
    }

    public List<GameArmor> getGameArmors() {
        return gameArmors;
    }
    public List<GameItem> getGameItems() {
        return gameItems;
    }

    public List<GameWeapons> getGameWeapons() {
        return gameWeapons;
    }

    public Map<Integer, GameItem> getItemIDs() {
        return itemIDs;
    }

    public Map<String, GameItem> getItems() {
        return items;
    }
    public GamePlayerCoins getPlayerCoins() {
        return playerGamePlayerCoins;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public static Main getInstance() {
        return instance;
    }
}
