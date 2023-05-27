package me.TahaCheji.gameUtil;

import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStatUtil {

    public static ItemStack setStrengthStats(GameItem gameItem, String name, int x, ItemStack item) {
        if (gameItem instanceof GameWeapons) {
            GameWeapons gameWeapons = (GameWeapons) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasStrength(lore)) {
                return item; // Strength already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.DARK_RED + "⚔Strength")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponStrength";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameWeapons.setStrength(gameWeapons.getStrength() + newValue);
                String value = ChatColor.DARK_RED + "⚔Strength: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameWeapons.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameWeapons.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
        }
        return item;
    }

    public static boolean hasStrength(List<String> lore) {
        return lore.contains(ChatColor.DARK_RED + "Strength");
    }

    public static ItemStack setHealthStats(GameItem gameItem, String name, int x, ItemStack item) {
        if (gameItem instanceof GameWeapons) {
            GameWeapons gameWeapons = (GameWeapons) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasHealth(lore)) {
                return item; // Health already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.RED + "❤Health")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponHealth";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameWeapons.setHealth(gameWeapons.getHealth() + newValue);
                String value = ChatColor.RED + "❤Health: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameWeapons.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameWeapons.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
        }
        return item;
    }

    public static boolean hasHealth(List<String> lore) {
        return lore.contains(ChatColor.RED + "Health");
    }

    public static ItemStack setArmorStats(GameItem gameItem, String name, int x, ItemStack item) {
        if (gameItem instanceof GameWeapons) {
            GameWeapons gameWeapons = (GameWeapons) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasArmor(lore)) {
                return item; // Strength already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.YELLOW + "⛨Armor")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponArmor";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameWeapons.setArmor(gameWeapons.getArmor() + newValue);
                String Value = ChatColor.YELLOW + "⛨Armor: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameWeapons.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameWeapons.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, Value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
            return item;
        }
        if (gameItem instanceof GameStaff) {
            GameStaff gameStaff = (GameStaff) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasArmor(lore)) {
                return item; // Strength already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.YELLOW + "⛨Armor")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponArmor";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameStaff.setArmor(gameStaff.getArmor() + newValue);
                String Value = ChatColor.YELLOW + "⛨Armor: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameStaff.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameStaff.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, Value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
            return item;
        }
        return null;
    }

    public static boolean hasArmor(List<String> lore) {
        return lore.contains(ChatColor.YELLOW + "Armor");
    }

    public static ItemStack setMagicStats(GameItem gameItem, String name, int x, ItemStack item) {
        if (gameItem instanceof GameWeapons) {
            GameWeapons gameWeapons = (GameWeapons) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasMagic(lore)) {
                return item; // Strength already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.BLUE + "[M]Magic")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponMagic";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameWeapons.setMagic(gameWeapons.getMagic() + newValue);
                String Value = ChatColor.BLUE + "[M]Magic: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameWeapons.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameWeapons.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, Value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
        }
        if (gameItem instanceof GameStaff) {
            GameStaff gameStaff = (GameStaff) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasMagic(lore)) {
                return item; // Strength already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.BLUE + "[M]Magic")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponMagic";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameStaff.setMagic(gameStaff.getMagic() + newValue);
                String Value = ChatColor.BLUE + "[M]Magic: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameStaff.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameStaff.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, Value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
        }
        return item;
    }

    public static boolean hasMagic(List<String> lore) {
        return lore.contains(ChatColor.BLUE + "Magic");
    }

    public static ItemStack setMobilityStats(GameItem gameItem, String name, int x, ItemStack item) {
        if (gameItem instanceof GameWeapons) {
            GameWeapons gameWeapons = (GameWeapons) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasMobility(lore)) {
                return item; // Strength already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.DARK_GREEN + "〰Mobility")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponMobility";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameWeapons.setMobility(gameWeapons.getMobility() + newValue);
                String Value = ChatColor.DARK_GREEN + "〰Mobility: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameWeapons.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameWeapons.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, Value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
        }
        if (gameItem instanceof GameStaff) {
            GameStaff gameStaff = (GameStaff) gameItem;
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = new ArrayList<>(itemMeta.getLore());

            if (hasMobility(lore)) {
                return item; // Strength already exists, no need to modify
            }

            int m = -1; // Initialize the index with an invalid value

            for (int j = 0; j < lore.size(); j++) {
                String s = lore.get(j);
                if (s.contains(ChatColor.DARK_GREEN + "〰Mobility")) {
                    m = j;
                    break;
                }
            }

            if (m >= 0 && m < lore.size()) {
                String key = "ItemWeaponMobility";
                int newValue = ((NBTUtils.getInt(item, key) * 5) / 100) * x;
                gameStaff.setMobility(gameStaff.getMobility() + newValue);
                String Value = ChatColor.DARK_GREEN + "〰Mobility: " + ChatColor.DARK_GRAY + "+" + ((NBTUtils.getInt(item, key) - (newValue * gameStaff.getItemLevel().getLevel())) + 5) + " [+" + (newValue * gameStaff.getItemLevel().getLevel()) + " " + name + "]";
                lore.set(m, Value);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                item = NBTUtils.setInt(item, key, (NBTUtils.getInt(item, key) + newValue));
            }
        }
        return item;
    }

    public static boolean hasMobility(List<String> lore) {
        return lore.contains(ChatColor.DARK_GREEN + "Mobility");
    }
}
