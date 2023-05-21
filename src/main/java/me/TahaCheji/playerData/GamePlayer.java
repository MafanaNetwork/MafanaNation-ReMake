package me.TahaCheji.playerData;

import me.TahaCheji.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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
    private BukkitTask actionBar;


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

    public void setGameStats() {
        actionBar = new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack heldItem = player.getInventory().getItemInMainHand();
                ItemStack[] armor = player.getInventory().getArmorContents();
                gamePlayerStats.updateStats(heldItem, armor);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + " " + getGamePlayerStats().getStrength()));
            }
        }.runTaskTimer(Main.getInstance(), 0L, 10L);
    }

    public void onJoin() throws Exception {
        setInventory();
        setGamePlayerStats(new GamePlayerStats(this));
        setGamePlayerCoins(new GamePlayerCoins());
        setGameStats();
        setName();
        Main.getInstance().getGamePlayers().add(this);
    }

    public void onQuit() {
        actionBar.cancel();
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
