package me.TahaCheji.itemData;

import de.tr7zw.nbtapi.NBTItem;
import me.TahaCheji.gameUtil.ItemStatUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.GameItemLevelXPTo;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GameItemLevel {

    private final GameItem gameItem;
    private int level;
    private int xp;

    public GameItemLevel(GameItem gameItem, int level, int xp) {
        this.gameItem = gameItem;
        this.level = level;
        this.xp = xp;
    }

    public ItemStack setItemToLevel(ItemStack itemStack) {
        if(gameItem instanceof GameWeapons) {
            GameWeapons gameWeapons = (GameWeapons) gameItem;
            ItemStack item = itemStack;
            ItemMeta itemMeta = item.getItemMeta();
            if(gameWeapons.getGameItemLevel() != null) {
                List<String> lore = new ArrayList<>(itemMeta.getLore());
                int i = -1;
                for (int j = 0; j < itemMeta.getLore().size(); j++) {
                    String s = itemMeta.getLore().get(j);
                    if (s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                        i = j;
                        break;
                    }
                }
                itemMeta.setDisplayName(ChatColor.DARK_GRAY + "[" + getLevel() + "] " + gameWeapons.getItemRarity().getColor() + gameWeapons.getName());
                if (i >= 0 && i < lore.size()) {
                    lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());

                    itemMeta.setLore(lore);
                    item.setItemMeta(itemMeta);
                }
                if(getLevel() >= 1) {
                    item = ItemStatUtil.setStrengthStats(gameWeapons, "Level", level, item);
                    item = ItemStatUtil.setHealthStats(gameWeapons, "Level", level, item);
                    item = ItemStatUtil.setArmorStats(gameWeapons, "Level", level, item);
                    item = ItemStatUtil.setMagicStats(gameWeapons, "Level", level, item);
                    item = ItemStatUtil.setMobilityStats(gameWeapons, "Level", level, item);
                    item = NBTUtils.setInt(item, "ItemWeaponLevel", getLevel());
                    item = NBTUtils.setInt(item, "ItemWeaponXP", getXp());
                }
                return item;
            }
        }
        if(gameItem instanceof GameStaff) {
            GameStaff gameStaff = (GameStaff) gameItem;
            ItemStack item = itemStack;
            ItemMeta itemMeta = item.getItemMeta();
            if(gameStaff.getGameItemLevel() != null) {
                List<String> lore = new ArrayList<>(itemMeta.getLore());
                int i = -1;
                for (int j = 0; j < itemMeta.getLore().size(); j++) {
                    String s = itemMeta.getLore().get(j);
                    if (s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                        i = j;
                        break;
                    }
                }
                itemMeta.setDisplayName(ChatColor.DARK_GRAY + "[" + getLevel() + "] " + gameStaff.getItemRarity().getColor() + gameStaff.getName());
                if (i >= 0 && i < lore.size()) {
                    lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
                    itemMeta.setLore(lore);
                    item.setItemMeta(itemMeta);
                }
                if(getLevel() >= 1) {
                    item = ItemStatUtil.setArmorStats(gameStaff, "Level", level, item);
                    item = ItemStatUtil.setMagicStats(gameStaff, "Level", level, item);
                    item = ItemStatUtil.setMobilityStats(gameStaff, "Level", level, item);
                    item = NBTUtils.setInt(item, "ItemWeaponLevel", getLevel());
                    item = NBTUtils.setInt(item, "ItemWeaponXP", getXp());
                }
                return item;
            }
        }

        return null;
    }

    public List<String> getLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "[" + getLevel() + "] XP: " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return lore;
    }

    public GameItem getGameItem() {
        return gameItem;
    }

    public void addXP(Player player, int i, ItemStack getRaw) {
        this.xp = getXp() + i;
        if (this.xp >= GameItemLevelXPTo.getXpTo(this).getXp()) {
            addLevel(player, 1, getRaw);
        }
        GameItemXpAddEvent event = new GameItemXpAddEvent(player, this, getRaw, i);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void addLevel(Player player, int i, ItemStack getRaw) {
        this.level = getLevel() + i;
        GameItemLevelUpEvent event = new GameItemLevelUpEvent(player, this, getRaw, i);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }
}

