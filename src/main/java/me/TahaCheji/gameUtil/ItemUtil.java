package me.TahaCheji.gameUtil;

import me.TahaCheji.Main;
import me.TahaCheji.itemData.*;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemUtil {

    public static ItemStack storeIntInItem(ItemStack host, Integer i, String key) {
        NamespacedKey k = new NamespacedKey(Main.getInstance(), key);
        if (host != null) {
            if (host.hasItemMeta()) {
                ItemMeta itemMeta = host.getItemMeta();
                itemMeta.getPersistentDataContainer().set(k, new StoredInt(), i);
                host.setItemMeta(itemMeta);
            }
        }
        return host;
    }
    public static Integer getIntFromItem(ItemStack host, String key) {
        NamespacedKey k = new NamespacedKey(Main.getInstance(), key);
        if (host == null) {
            return 0;
        } else if (!host.hasItemMeta()) {
            return 0;
        } else {
            ItemMeta itemMeta = host.getItemMeta();
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            return container.has(k, new CompactInventory()) ? (Integer)container.get(k, new StoredInt()) : 0;
        }
    }

    public static GameWeapons getGameWeapon(ItemStack itemStack) {
        for(GameWeapons gameWeapons : Main.getInstance().getGameWeapons()) {
            if(Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.SWORD.getLore())) {
                return gameWeapons;
            }
        }
        return null;
    }

    public static GameBow getGameBow(ItemStack itemStack) {
        for(GameBow gameBow : Main.getInstance().getGameBow()) {
            if(Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.BOW.getLore())) {
                return gameBow;
            }
        }
        return null;
    }

    public static GameStaff getGameStaff(ItemStack itemStack) {
        for(GameStaff gameStaff : Main.getInstance().getGameStaffs()) {
            if(Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.STAFF.getLore())) {
                return gameStaff;
            }
        }
        return null;
    }

    public static GameSpell getGameSpell(ItemStack itemStack) {
        for(GameSpell gameSpell : Main.getInstance().getGameSpells()) {
            if(Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.SPELL.getLore())) {
                return gameSpell;
            }
        }
        return null;
    }

    public static GameArmor getGameArmor(ItemStack itemStack) {
        for(GameArmor gameArmor : Main.getInstance().getGameArmors()) {
            if(Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.CHESTPLATE.getLore()) ||
                    Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.LEGGGINGS.getLore()) ||
                    Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.BOOTS.getLore()) ||
                    Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.HELMET.getLore())) {
                return gameArmor;
            }
        }
        return null;
    }

    public static boolean isGameItem(ItemStack item) {
        return getIntFromItem(item, "GameItemUUID") != 0;
    }

    public static GameItem getGameItem(ItemStack item) {
        int UUID = getIntFromItem(item, "GameItemUUID");
        return UUID == 0 ? null : getItemFromID(UUID);
    }

    public static GameItem getItemFromID(int id) {
        GameItem item = (GameItem) Main.getInstance().getItemIDs().get(id);

        return item == null ? (GameItem) Main.getInstance().getItems().get("null") : item;
    }

    public static void putItem(String name, GameItem item) {
        Main.getInstance().getItems().put(name, item);
        Main.getInstance().getItemIDs().put(item.getUUID(), item);
    }


    public static int stringToSeed(String s) {
        if (s == null) {
            return 0;
        } else {
            int hash = 0;
            char[] var2 = s.toCharArray();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                char c = var2[var4];
                hash = 31 * hash + c;
            }

            return hash;
        }
    }

    public static List<String> stringToLore(String string, int characterLimit, ChatColor prefixColor) {
        String[] words = string.split(" ");
        List<String> lines = new ArrayList();
        StringBuilder currentLine = new StringBuilder();
        String[] var6 = words;
        int var7 = words.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String word = var6[var8];
            if (!word.equals("/newline")) {
                if (currentLine.toString().equals("")) {
                    currentLine = new StringBuilder(word);
                } else {
                    currentLine.append(" ").append(word);
                }
            }

            if (word.equals("/newline") || currentLine.length() + word.length() >= characterLimit) {
                String newLine = currentLine.toString();
                lines.add("" + prefixColor + newLine);
                currentLine = new StringBuilder();
            }
        }

        if (currentLine.length() > 0) {
            lines.add("" + prefixColor + currentLine);
        }

        return lines;
    }
}
