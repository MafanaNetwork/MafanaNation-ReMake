package me.TahaCheji.playerData;

import me.TahaCheji.Inv;
import me.TahaCheji.Main;
import me.TahaCheji.gameEvent.GamePlayerUseGameItem;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.sectionsData.GameSections;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GamePlayer {

    private Player player;
    private OfflinePlayer offlinePlayer;

    private GamePlayerInventory inventory;
    private String name;
    private GamePlayerCoins gamePlayerCoins;
    public GamePlayerStats gamePlayerStats;
    private Location location;
    private BukkitTask playerStartUp;
    private BukkitTask itemRepeat;
    private GameSections gameSections = null;


    public GamePlayer(Player player) throws Exception {
        this.player = player;
        onJoin();
    }

    public GamePlayer(OfflinePlayer offlinePlayer) throws Exception {
        this.offlinePlayer = offlinePlayer;
        setOfflinePlayerInventory(offlinePlayer);
        setOfflinePlayerName();
    }

    public GamePlayer(Player player, GamePlayerCoins gamePlayerCoins, GamePlayerStats gamePlayerStats) {
        this.player = player;
        this.gamePlayerCoins = gamePlayerCoins;
        this.gamePlayerStats = gamePlayerStats;
    }

    boolean hasSynced = false;

    public void setPlayerStartUp() {
        new GamePlayerStartUp(this);
        gamePlayerStats.actionBar(GamePlayer.this);
    }

    public void onJoin() throws Exception {
        setInventory();
        player.teleport(new Location(Bukkit.getWorld("world"), 2353, 35, 3124));
        setGamePlayerStats(new GamePlayerStats(this));
        setGamePlayerCoins(new GamePlayerCoins());
        gamePlayerCoins.connect();
        playerStartUp = new BukkitRunnable() {
            @Override
            public void run() {
                setPlayerStartUp();
            }
        }.runTaskTimer(Main.getInstance(), 5L, 10L);
        itemRepeat = new BukkitRunnable() {
            @Override
            public void run() {
                GamePlayerUseGameItem.whilePlayerHolding(player);
            }
        }.runTaskTimer(Main.getInstance(), 20L, 10L);
        setName();
        Main.getInstance().getGamePlayers().add(this);
        Main.getInstance().getMainScoreboard().onJoin(this);
    }

    public void onQuit() {
        playerStartUp.cancel();
        itemRepeat.cancel();
        Main.getInstance().getGamePlayers().remove(this);
    }

    public void setGamePlayerCoins(GamePlayerCoins gamePlayerCoins) {
        this.gamePlayerCoins = gamePlayerCoins;
        Main.getInstance().getPlayerCoins().addPlayer(player);
    }

    public void setInventory() throws Exception {
        this.inventory = new GamePlayerInventory(this);
    }

    public void setOfflinePlayerInventory(OfflinePlayer offlinePlayer) throws Exception {
        this.inventory = new GamePlayerInventory(offlinePlayer);
    }

    public GamePlayerInventory getInventory() {
        return inventory;
    }

    public void setGameSections(GameSections gameSections) {
        this.gameSections = gameSections;
    }

    public GameSections getGameSections() {
        return gameSections;
    }

    public void setName() {
        this.name = player.getName();
    }

    public void setOfflinePlayerName() {
        this.name = offlinePlayer.getName();
    }

    public GamePlayerCoins getGamePlayerCoins() {
        return gamePlayerCoins;
    }

    public GamePlayerStats getGamePlayerStats() {
        return gamePlayerStats;
    }

    public void setGamePlayerStats(GamePlayerStats gamePlayerStats) {
        this.gamePlayerStats = gamePlayerStats;
    }

    public String getName() {
        return name;
    }

    public void setCoins(GamePlayerCoins gamePlayerCoins) {
        this.gamePlayerCoins = gamePlayerCoins;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public GamePlayerCoins getCoins() {
        return gamePlayerCoins;
    }

    public Location getLocation() {
        return location;
    }
}
