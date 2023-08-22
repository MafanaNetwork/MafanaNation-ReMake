package me.TahaCheji.gameUtil;

import de.tr7zw.nbtapi.NBTItem;
import me.TahaCheji.Main;
import me.TahaCheji.itemData.*;
import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameItemLevel;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemUtil {

    public static GameWeapons getGameWeapon(ItemStack itemStack) {
        for (GameWeapons gameWeapons : Main.getInstance().getGameWeapons()) {
            if (Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.SWORD.getLore())) {
                if (Objects.equals(gameWeapons.getWeaponUUID(), NBTUtils.getString(itemStack, "GameWeaponUUID"))) {
                    return gameWeapons;
                }
            }
        }
        return null;
    }

    public static GameBow getGameBow(ItemStack itemStack) {
        for (GameBow gameBow : Main.getInstance().getGameBows()) {
            if (Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.BOW.getLore())) {
                if (Objects.equals(gameBow.getBowUUID(), NBTUtils.getString(itemStack, "GameBowUUID"))) {
                    return gameBow;
                }
            }
        }
        return null;
    }

    public static GameStaff getGameStaff(ItemStack itemStack) {
        for (GameStaff gameStaff : Main.getInstance().getGameStaffs()) {
            if (Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.STAFF.getLore())) {
                if (Objects.equals(gameStaff.getStaffUUID(), NBTUtils.getString(itemStack, "GameStaffUUID"))) {
                    return gameStaff;
                }
            }
        }
        return null;
    }

    public static GameSpell getGameSpell(ItemStack itemStack) {
        for (GameSpell gameSpell : Main.getInstance().getGameSpells()) {
            if (Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.SPELL.getLore())) {
                if (Objects.equals(gameSpell.getSpellUUID(), NBTUtils.getString(itemStack, "GameSpellUUID"))) {
                    return gameSpell;
                }
            }
        }
        return null;
    }

    public static GameArmor getGameArmor(ItemStack itemStack) {
        for (GameArmor gameArmor : Main.getInstance().getGameArmors()) {
            if (Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.CHESTPLATE.getLore()) ||
                    Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.LEGGGINGS.getLore()) ||
                    Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.BOOTS.getLore()) ||
                    Objects.equals(NBTUtils.getString(itemStack, "ItemType"), ItemType.HELMET.getLore())) {
                if (Objects.equals(gameArmor.getArmorUUID(), NBTUtils.getString(itemStack, "GameArmorUUID"))) {
                    return gameArmor;
                }
            }
        }
        return null;
    }

    public static boolean isGameItem(ItemStack item) {
        return NBTUtils.getString(item, "GameItemUUID") != null;
    }

    public static GameItem getGameItem(ItemStack item) {
        for (GameItem gameItem : Main.getInstance().getGameItems()) {
            if (Objects.equals(gameItem.getItemUUID(), NBTUtils.getString(item, "GameItemUUID"))) {
                return gameItem;
            }
        }
        return null;
    }

    public static String itemLoreDash(String s) {
        int length = s.length();
        String dash = "";
        for (int i = 0; i < length; i++) {
            dash = dash + "-";
        }
        return dash;
    }

    public static void registerGameWeapons(Player player) throws InstantiationException, IllegalAccessException {
        int i = -1;
        for(ItemStack itemStack : player.getInventory()) {
            i += 1;
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID == null) {
                continue;
            }
            for (GameItem gameItem : Main.getInstance().getGameItems()) {
                if (!gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                    continue;
                }
                if (gameItem instanceof GameWeapons) {
                    GameWeapons gameWeapons = ((GameWeapons) gameItem);
                    if (Main.getInstance().getGameWeapons().contains(gameWeapons)) {
                        continue;
                    }
                    GameWeapons newInstance = gameWeapons.createNewInstance();
                    if(newInstance.getGameItemLevel() != null) {
                        GameItemLevel gameWeaponLevel = new GameItemLevel(newInstance, NBTUtils.getInt(itemStack, "ItemWeaponLevel"), NBTUtils.getInt(itemStack, "ItemWeaponXP"));
                        newInstance.setGameItemLevel(gameWeaponLevel);
                    }
                    if(NBTUtils.getBoolean(itemStack, "RENTED")) {
                        ItemStack item = newInstance.getGameWeapon();
                        ItemMeta itemMeta = item.getItemMeta();
                        List<String> lore = itemMeta.getLore();
                        lore.add("");
                        lore.add(ChatColor.DARK_RED + "*MafanaMarket Rentables*");
                        itemMeta.setLore(lore);
                        item.setItemMeta(itemMeta);
                        item = NBTUtils.setBoolean(itemStack, "RENTED", true);
                        player.getInventory().setItem(i, item);
                        continue;
                    }
                    player.getInventory().setItem(i, newInstance.getGameWeapon());
                }
            }
        }
    }

    public static void registerGameSpells(Player player) throws InstantiationException, IllegalAccessException {
        int i = -1;
        for(ItemStack itemStack : player.getInventory()) {
            i += 1;
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID == null) {
                continue;
            }
            for (GameItem gameItem : Main.getInstance().getGameItems()) {
                if (!gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                    continue;
                }
                if (gameItem instanceof GameSpell) {
                    GameSpell game = ((GameSpell) gameItem);
                    if (Main.getInstance().getGameSpells().contains(game)) {
                        continue;
                    }
                    GameSpell newInstance = game.createNewInstance();
                    player.getInventory().setItem(i, newInstance.getGameSpell());
                }
            }
        }
    }

    public static void registerGameStaffs(Player player) throws InstantiationException, IllegalAccessException {
        int i = -1;
        for(ItemStack itemStack : player.getInventory()) {
            i += 1;
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID == null) {
                continue;
            }
            for (GameItem gameItem : Main.getInstance().getGameItems()) {
                if (!gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                    continue;
                }
                if (gameItem instanceof GameStaff) {
                    GameStaff game = ((GameStaff) gameItem);
                    if (Main.getInstance().getGameStaffs().contains(game)) {
                        continue;
                    }
                    GameStaff newInstance = game.createNewInstance();
                    if(newInstance.getGameItemLevel() != null) {
                        GameItemLevel gameWeaponLevel = new GameItemLevel(newInstance, NBTUtils.getInt(itemStack, "ItemWeaponLevel"), NBTUtils.getInt(itemStack, "ItemWeaponXP"));
                        newInstance.setGameItemLevel(gameWeaponLevel);
                    }
                    if(NBTUtils.getBoolean(itemStack, "RENTED")) {
                        ItemStack item = newInstance.getGameStaff();
                        ItemMeta itemMeta = item.getItemMeta();
                        List<String> lore = itemMeta.getLore();
                        lore.add("");
                        lore.add(ChatColor.DARK_RED + "*MafanaMarket Rentables*");
                        itemMeta.setLore(lore);
                        item.setItemMeta(itemMeta);
                        item = NBTUtils.setBoolean(itemStack, "RENTED", true);
                        player.getInventory().setItem(i, item);
                        continue;
                    }
                    player.getInventory().setItem(i, newInstance.getGameStaff());
                }
            }
        }
    }

    public static void registerGameBows(Player player) throws InstantiationException, IllegalAccessException {
        int i = -1;
        for(ItemStack itemStack : player.getInventory()) {
            i += 1;
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID == null) {
                continue;
            }
            for (GameItem gameItem : Main.getInstance().getGameItems()) {
                if (!gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                    continue;
                }
                if (gameItem instanceof GameBow) {
                    GameBow game = ((GameBow) gameItem);
                    if (Main.getInstance().getGameBows().contains(game)) {
                        continue;
                    }
                    GameBow newInstance = game.createNewInstance();
                    if(NBTUtils.getBoolean(itemStack, "RENTED")) {
                        ItemStack item = newInstance.getGameBow();
                        ItemMeta itemMeta = item.getItemMeta();
                        List<String> lore = itemMeta.getLore();
                        lore.add("");
                        lore.add(ChatColor.DARK_RED + "*MafanaMarket Rentables*");
                        itemMeta.setLore(lore);
                        item.setItemMeta(itemMeta);
                        item = NBTUtils.setBoolean(itemStack, "RENTED", true);
                        player.getInventory().setItem(i, item);
                        continue;
                    }
                    player.getInventory().setItem(i, newInstance.getGameBow());
                }
            }
        }
    }

    public static void registerArmorGameItems(Player player) throws InstantiationException, IllegalAccessException {
        List<ItemStack> modifiedItems = new ArrayList<>();

        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                modifiedItems.add(itemStack);
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID != null) {
                boolean modified = false; // Flag to track if the item has been modified

                for (GameItem gameItem : Main.getInstance().getGameItems()) {
                    if (gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                        if (gameItem instanceof GameArmor) {
                            GameArmor gameArmor = ((GameArmor) gameItem);
                            if (!Main.getInstance().getGameArmors().contains(gameArmor)) {
                                GameArmor newGameArmor = gameArmor.createNewInstance();
                                if(NBTUtils.getBoolean(itemStack, "RENTED")) {
                                    ItemStack item = newGameArmor.getGameArmor();
                                    ItemMeta itemMeta = item.getItemMeta();
                                    List<String> lore = itemMeta.getLore();
                                    lore.add("");
                                    lore.add(ChatColor.DARK_RED + "*MafanaMarket Rentables*");
                                    itemMeta.setLore(lore);
                                    item.setItemMeta(itemMeta);
                                    item = NBTUtils.setBoolean(itemStack, "RENTED", true);
                                    modifiedItems.add(item);
                                } else {
                                    modifiedItems.add(newGameArmor.getGameArmor());
                                }

                                modified = true;
                                break;
                            }
                        }
                    }
                }

                if (!modified) {
                    modifiedItems.add(itemStack); // Add the original item if no modification occurred
                }
            } else {
                modifiedItems.add(itemStack); // Add the original item if it doesn't have a GameItemUUID
            }
        }

        // Update the modified items in the player's inventory
        ItemStack[] armorContents = player.getInventory().getArmorContents();
        for (int i = 0; i < armorContents.length; i++) {
            if (modifiedItems.size() > i) {
                if (modifiedItems.get(i) == null || modifiedItems.get(i).getType() == Material.AIR) {
                    armorContents[i] = null;
                    continue;
                }
                GameArmor gameArmor = ItemUtil.getGameArmor(modifiedItems.get(i));
                if (gameArmor != null) {
                    if(NBTUtils.getBoolean(armorContents[i], "RENTED")) {
                        ItemStack item = gameArmor.getGameArmor();
                        ItemMeta itemMeta = item.getItemMeta();
                        List<String> lore = itemMeta.getLore();
                        lore.add("");
                        lore.add(ChatColor.DARK_RED + "*MafanaMarket Rentables*");
                        itemMeta.setLore(lore);
                        item.setItemMeta(itemMeta);
                        item = NBTUtils.setBoolean(item, "RENTED", true);
                        armorContents[i] = item;
                    } else {
                        armorContents[i] = gameArmor.getGameArmor();
                    }
                }
            }
        }

        player.getInventory().setArmorContents(armorContents);
    }

}
