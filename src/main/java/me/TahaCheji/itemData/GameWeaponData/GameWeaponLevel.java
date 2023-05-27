package me.TahaCheji.itemData.GameWeaponData;

import me.TahaCheji.gameUtil.ItemStatUtil;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItemLevelXPTo;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GameWeaponLevel {

    private int level;
    private int xp;
    private ItemStack itemStack;


    public GameWeaponLevel(int level, int xp) {
        this.level = level;
        this.xp = xp;
    }

    public GameWeaponLevel() {
    }

    public void setItem(ItemStack item) {
        this.itemStack = item;
    }

    public List<String> getLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "[" + getLevel() + "] XP: " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
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


    public ItemStack addXP(int x, GameWeapons gameWeapons) {
        ItemStack item = gameWeapons.getGameWeapon();
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
        System.out.println(getXp() >= GameItemLevelXPTo.getXpTo(this).getXp());
        if (getXp() >= GameItemLevelXPTo.getXpTo(this).getXp()) {
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            setXP(0, itemStack);
            item = addLevel(1, gameWeapons);
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


    public @Nullable ItemStack setLevel(int level, GameWeapons gameWeapons) {
        ItemStack item = gameWeapons.getGameWeapon();
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
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "[" + getLevel() + "] " + gameWeapons.getItemRarity().getColor() + gameWeapons.getName());
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        for (int x = 0; x < level; x++) {
            addLevel(1, gameWeapons);
        }
        return item;
    }

    public @Nullable ItemStack setLevelAndXP(int level, int xp, GameWeapons gameWeapons) {
        ItemStack item = gameWeapons.getGameWeapon();
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
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "[" + getLevel() + "] " + gameWeapons.getItemRarity().getColor() + gameWeapons.getName());
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        for (int x = 0; x < level; x++) {
            addLevel(1, gameWeapons);
        }
        return item;
    }

    public ItemStack addLevel(int level, GameWeapons gameWeapons) {
        ItemStack item = gameWeapons.getGameWeapon();
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
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "[" + getLevel() + "] " + gameWeapons.getItemRarity().getColor() + gameWeapons.getName());
        if (i >= 0 && i < lore.size()) {
            lore.set(i, ChatColor.GRAY + "[" + getLevel() + "] XP " + getXp() + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            item = getLevelItemStack(item);
        }
        item = ItemStatUtil.setStrengthStats(gameWeapons, "Level", level, item);
        item = ItemStatUtil.setHealthStats(gameWeapons, "Level", level, item);
        item = ItemStatUtil.setArmorStats(gameWeapons, "Level", level, item);
        item = ItemStatUtil.setMagicStats(gameWeapons, "Level", level, item);
        item = ItemStatUtil.setMobilityStats(gameWeapons, "Level", level, item);
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
