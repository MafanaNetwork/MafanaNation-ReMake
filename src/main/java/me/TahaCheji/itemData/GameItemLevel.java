package me.TahaCheji.itemData;

import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GameItemLevel {

    public int level;
    public int xp;

    public GameItemLevel(int level, int xp) {
        this.level = level;
        this.xp = xp;
    }

    public List<String> getLore(ItemStack itemStack) {
        ItemStack item = itemStack;
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "[" + NBTUtils.getInt(item, "ItemLevel") + "] XP " + NBTUtils.getInt(item, "ItemXP") +  " / " + GameItemLevelXPTo.getXpTo(this).getXp());
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return lore;
    }

    public ItemStack getLevelItemStack(ItemStack itemStack) {
        ItemStack item = itemStack;
        item = NBTUtils.setInt(item, "ItemLevel", getLevel());
        item = NBTUtils.setInt(item, "ItemXP", getXp());
        return item;
    }

    public ItemStack setXP(int xp, ItemStack itemStack) {
        ItemStack item = itemStack;
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = 0;
        for(String s : itemMeta.getLore()) {
            if(s.contains(ChatColor.GRAY + "[" + level + "]")) {
                break;
            }
            i = i + 1;
        }
        this.xp = xp;
        lore.set(i,ChatColor.GRAY + "[" + level + "] XP " + xp + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        item = getLevelItemStack(item);
        return item;
    }

    public ItemStack addXP(int x, ItemStack itemStack) {
        ItemStack item = itemStack;
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = 0;
        for(String s : itemMeta.getLore()) {
            if(s.contains(ChatColor.GRAY + "[" + level + "]")) {
                break;
            }
            i = i + 1;
        }
        int newX = x + NBTUtils.getInt(item, "ItemXP");
        this.xp = x + getXp();
        if(newX >= GameItemLevelXPTo.getXpTo(this).getXp()) {
            addLevel(1, item);
            setXP(0, item);
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            return item;
        }
        System.out.println(2);
        lore.set(i,ChatColor.GRAY + "[" + level + "] XP " + xp + " / " + GameItemLevelXPTo.getXpTo(this).getXp());
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        item = getLevelItemStack(item);
        return item;
    }

    public @Nullable ItemStack setLevel(int level, ItemStack itemStack) {
        ItemStack item = itemStack;
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = 0;
        for(String s : itemMeta.getLore()) {
            if(s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                break;
            }
            i = i + 1;
        }
        this.level = level;
        lore.set(i,ChatColor.GRAY + "[" + level + "] XP " + xp +  " / " + GameItemLevelXPTo.getXpTo(this).getXp());
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        item = getLevelItemStack(item);
        return item;
    }

    public @Nullable ItemStack addLevel(int level, ItemStack itemStack) {
        ItemStack item = itemStack;
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        int i = 0;
        for(String s : itemMeta.getLore()) {
            if(s.contains(ChatColor.GRAY + "[" + getLevel() + "]")) {
                break;
            }
            i = i + 1;
        }
        this.level = level + getLevel();
        lore.set(i,ChatColor.GRAY + "[" + level + "] XP " + xp +  " / " + GameItemLevelXPTo.getXpTo(this).getXp());
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        item = getLevelItemStack(item);
        return item;
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
