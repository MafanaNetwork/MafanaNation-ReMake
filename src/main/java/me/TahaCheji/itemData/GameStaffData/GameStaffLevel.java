package me.TahaCheji.itemData.GameStaffData;

import me.TahaCheji.gameUtil.ItemStatUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItemLevelXPTo;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GameStaffLevel {

    private int level;
    private int xp;
    private ItemStack itemStack;


    public GameStaffLevel(int level, int xp) {
        this.level = level;
        this.xp = xp;
    }

    public GameStaffLevel() {
    }

    public void setItem(ItemStack item) {
        this.itemStack = item;
    }

    public List<String> getLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "[" + getLevel() + "] XP: " + getXp() +  " / " + GameItemLevelXPTo.getXpTo(this).getXp());
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return lore;
    }

    public ItemStack getLevelItemStack(ItemStack itemStack) {
        ItemStack item = itemStack;
        item = NBTUtils.setInt(item, "ItemWeaponLevel", getLevel());
        item = NBTUtils.setInt(item, "ItemWeaponXP", getXp());
        return item;
    }

    public ItemStack setXP(int xp, ItemStack itemStack) {
        ItemStack item = itemStack;
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = -1; // Initialize the index with an invalid value
        for (int j = 0; j < itemMeta.getLore().size(); j++) {
            String s = itemMeta.getLore().get(j);
            if (s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                i = j;
                break;
            }
        }
        this.xp = xp;
        // Ensure the index is within bounds
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        item = NBTUtils.setInt(item, "ItemWeaponLevel", getLevel());
        item = NBTUtils.setInt(item, "ItemWeaponXP", getXp());
        return item;
    }


    public ItemStack addXP(int x, GameStaff gameStaff) {
        ItemStack item = gameStaff.getGameStaff();
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = -1; // Initialize the index with an invalid value
        for (int j = 0; j < itemMeta.getLore().size(); j++) {
            String s = itemMeta.getLore().get(j);
            if (s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                i = j;
                break;
            }
        }
        this.xp = x + getXp();
        if (getXp() >= GameItemLevelXPTo.getXpTo(this).getXp()) {
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            setXP(0, itemStack);
            item = addLevel(1, gameStaff);
            return item;
        }
        // Ensure the index is within bounds
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        item = NBTUtils.setInt(item, "ItemWeaponLevel", getLevel());
        item = NBTUtils.setInt(item, "ItemWeaponXP", getXp());
        return item;
    }


    public @Nullable ItemStack setLevel(int level, GameStaff gameStaff) {
        ItemStack item = gameStaff.getGameStaff();
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = -1; // Initialize the index with an invalid value
        for (int j = 0; j < itemMeta.getLore().size(); j++) {
            String s = itemMeta.getLore().get(j);
            if (s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                i = j;
                break;
            }
        }
        this.level = level;
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "["+ getLevel() +"] " + gameStaff.getItemRarity().getColor() + gameStaff.getName());
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        for(int x=0;x<level;x++) {
            addLevel(1, gameStaff);
        }
        return item;
    }
    public @Nullable ItemStack setLevelAndXP(int level,int xp, GameStaff gameStaff) {
        ItemStack item = gameStaff.getGameStaff();
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = -1; // Initialize the index with an invalid value
        for (int j = 0; j < itemMeta.getLore().size(); j++) {
            String s = itemMeta.getLore().get(j);
            if (s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                i = j;
                break;
            }
        }
        this.level = level;
        this.xp = xp;
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "["+ getLevel() +"] " + gameStaff.getItemRarity().getColor() + gameStaff.getName());
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        for(int x=0;x<level;x++) {
            addLevel(1, gameStaff);
        }
        return item;
    }

    public ItemStack addLevel(int level, GameStaff gameStaff) {
        ItemStack item = gameStaff.getGameStaff();
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = -1; // Initialize the index with an invalid value
        for (int j = 0; j < itemMeta.getLore().size(); j++) {
            String s = itemMeta.getLore().get(j);
            if (s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                i = j;
                break;
            }
        }
        this.level = level + getLevel();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "["+ getLevel() +"] " + gameStaff.getItemRarity().getColor() + gameStaff.getName());
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        item = ItemStatUtil.setArmorStats(gameStaff, "Level", level, item);
        item = ItemStatUtil.setMagicStats(gameStaff, "Level", level, item);
        item = ItemStatUtil.setMobilityStats(gameStaff, "Level", level, item);
        item = NBTUtils.setInt(item, "ItemWeaponLevel", getLevel());
        item = NBTUtils.setInt(item, "ItemWeaponXP", getXp());
        return item;
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
