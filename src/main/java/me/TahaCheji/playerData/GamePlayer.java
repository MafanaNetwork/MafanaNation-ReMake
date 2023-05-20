package me.TahaCheji.playerData;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class GamePlayer {

    private Player player;
    private OfflinePlayer offlinePlayer;

    private GamePlayerInventory inventory;
    private String name;
    private int coins;
    private int magic;
    private int health;
    private int armor;
    private int strength;
    private int mobility;
    private Location location;


    public GamePlayer(Player player) throws Exception {
        this.player = player;
        setInventory();
        setName();
    }

    public GamePlayer(OfflinePlayer offlinePlayer) throws Exception {
        this.offlinePlayer = offlinePlayer;
        setOfflinePlayerInventory(offlinePlayer);
        setOfflinePlayerName();
    }

    public GamePlayer(Player player, int coins, int magic, int health, int armor, int strength, int mobility) {
        this.player = player;
        this.coins = coins;
        this.magic = magic;
        this.health = health;
        this.armor = armor;
        this.strength = strength;
        this.mobility = mobility;
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

    public String getName() {
        return name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCoins() {
        return coins;
    }

    public int getMagic() {
        return magic;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getStrength() {
        return strength;
    }

    public int getMobility() {
        return mobility;
    }

    public Location getLocation() {
        return location;
    }
}
