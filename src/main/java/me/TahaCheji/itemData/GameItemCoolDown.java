package me.TahaCheji.itemData;

import me.TahaCheji.Main;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class GameItemCoolDown {

    private final GameItem gameItem;
    private final GamePlayer gamePlayer;

    public GameItemCoolDown(GameItem gameItem, GamePlayer gamePlayer) {
        this.gameItem = gameItem;
        this.gamePlayer = gamePlayer;
    }

    public boolean ifCanUse(GameItem gameItem) {
        if(Main.getInstance().getCoolDownHashMap().containsKey(gameItem)) {
            gamePlayer.getPlayer().sendMessage(ChatColor.RED + "Cant use that");
            gamePlayer.getPlayer().playSound(gamePlayer.getPlayer(), Sound.BLOCK_GLASS_BREAK, 10, 10);
            return true;
        }
        return false;
    }

    public void addPlayerToCoolDownWeapon() {
        Main.getInstance().getCoolDownHashMap().put(gameItem, gamePlayer);
        GameWeapons gameWeapons = (GameWeapons) gameItem;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                removePlayerFromCoolDown();
            }
        }, 20L * (int) gameWeapons.getGameAbility().getCoolDown()); //20 Tick (1 Second) delay before run() is called
    }

    public void addPlayerToCoolDownSpell() {
        Main.getInstance().getCoolDownHashMap().put(gameItem, gamePlayer);
        GameSpell gameWeapons = (GameSpell) gameItem;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                removePlayerFromCoolDown();
            }
        }, 20L * (int) gameWeapons.getGameItemAbility().getCoolDown()); //20 Tick (1 Second) delay before run() is called
    }

    public void addPlayerToCoolDownStaff() {
        Main.getInstance().getCoolDownHashMap().put(gameItem, gamePlayer);
        GameStaff gameWeapons = (GameStaff) gameItem;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                removePlayerFromCoolDown();
            }
        }, 20L * (int) gameWeapons.getGameItemAbility().getCoolDown()); //20 Tick (1 Second) delay before run() is called
    }

    public void removePlayerFromCoolDown() {
        Main.getInstance().getCoolDownHashMap().remove(gameItem, gamePlayer);
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public GameItem getGameItem() {
        return gameItem;
    }
}
