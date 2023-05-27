package me.TahaCheji.playerData;

import me.TahaCheji.Inv;
import me.TahaCheji.Main;
import me.TahaCheji.gameEvent.GamePlayerUseGameItem;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.sectionsData.GameSections;
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
    public int coins;


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
        if (Inv.getInstance().getInventoryDataHandler().isSyncComplete(player) && !hasSynced) {
            try {
                Main.getInstance().getMainScoreboard().updateScoreboard();
                ItemUtil.registerWeaponsGameItems(player);
                ItemUtil.registerBowGameItems(player);
                ItemUtil.registerArmorGameItems(player);
                ItemUtil.registerSpellsGameItems(player);
                ItemUtil.registerStaffGameItems(player);
                hasSynced = true;
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        for (GameSections gameSection : Main.getInstance().getGameSections()) {
            Location sectionX = gameSection.getX();
            Location sectionY = gameSection.getY();
            Location playerLocation = player.getLocation();
            if (playerLocation.getX() >= sectionX.getX() && playerLocation.getX() <= sectionY.getX()
                    && playerLocation.getY() >= sectionX.getY() && playerLocation.getY() <= sectionY.getY()
                    && playerLocation.getZ() >= sectionX.getZ() && playerLocation.getZ() <= sectionY.getZ()) {
                setGameSections(gameSection);
                gameSection.whileThere(player);
            }
        }

        player.setHealthScaled(true);
        player.setHealthScale(40);
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        ItemStack[] armor = player.getInventory().getArmorContents();
        gamePlayerStats.updateStats(heldItem, armor);
        getPlayer().setSaturation(20);
        gamePlayerStats.regenerateHealth();
        gamePlayerStats.regenerateMana();

        double maxHealth = gamePlayerStats.getMaxHealth();
        double health = gamePlayerStats.getHealth();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        player.setHealth(health);

        getGamePlayerStats().setMobilityStats();

        gamePlayerStats.actionBar(GamePlayer.this);
    }

    public void onJoin() throws Exception {
        setInventory();
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
