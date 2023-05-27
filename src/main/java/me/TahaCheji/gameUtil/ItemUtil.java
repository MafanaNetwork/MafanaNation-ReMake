package me.TahaCheji.gameUtil;

import me.TahaCheji.Main;
import me.TahaCheji.itemData.*;
import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

    public static void registerWeaponsGameItems(Player player) throws InstantiationException, IllegalAccessException {
        List<ItemStack> modifiedItems = new ArrayList<>();

        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                modifiedItems.add(itemStack);
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID != null) {
                boolean modified = false; // Flag to track if the item has been modified

                for (GameItem gameItem : Main.getInstance().getGameItems()) {
                    if (gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                        if (gameItem instanceof GameWeapons) {
                            GameWeapons gameWeapons = ((GameWeapons) gameItem);
                            if (!Main.getInstance().getGameWeapons().contains(gameWeapons)) {
                                GameWeapons newGameWeapons = gameWeapons.createNewInstance();
                                ItemStack item;
                                item = newGameWeapons.getItemLevel().setLevelAndXP(NBTUtils.getInt(itemStack, "ItemWeaponLevel"), NBTUtils.getInt(itemStack, "ItemWeaponXP"), newGameWeapons);
                                modifiedItems.add(item);
                                modified = true; // Set the flag to indicate modification
                                break; // Break out of the inner loop since modification is done
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
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (modifiedItems.size() > i) {
                if (modifiedItems.get(i) == null || modifiedItems.get(i).getType() == Material.AIR) {
                    player.getInventory().setItem(i, null);
                    continue;
                }
                GameWeapons gameWeapons = ItemUtil.getGameWeapon(modifiedItems.get(i));
                if (gameWeapons != null) {
                    player.getInventory().setItem(i, gameWeapons.getItemLevel().setLevelAndXP(NBTUtils.getInt(modifiedItems.get(i), "ItemWeaponLevel"), NBTUtils.getInt(modifiedItems.get(i), "ItemWeaponXP"), gameWeapons));
                }
            }
        }
    }

    public static void registerSpellsGameItems(Player player) throws InstantiationException, IllegalAccessException {
        List<ItemStack> modifiedItems = new ArrayList<>();

        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                modifiedItems.add(itemStack);
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID != null) {
                boolean modified = false; // Flag to track if the item has been modified

                for (GameItem gameItem : Main.getInstance().getGameItems()) {
                    if (gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                        if (gameItem instanceof GameSpell) {
                            GameSpell gameWeapons = ((GameSpell) gameItem);
                            if (!Main.getInstance().getGameSpells().contains(gameWeapons)) {
                                GameSpell newGameWeapons = gameWeapons.createNewInstance();
                                modifiedItems.add(newGameWeapons.getGameSpell());
                                modified = true; // Set the flag to indicate modification
                                break; // Break out of the inner loop since modification is done
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
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (modifiedItems.size() > i) {
                if (modifiedItems.get(i) == null || modifiedItems.get(i).getType() == Material.AIR) {
                    player.getInventory().setItem(i, null);
                    continue;
                }
                GameSpell gameWeapons = ItemUtil.getGameSpell(modifiedItems.get(i));
                if (gameWeapons != null) {
                    player.getInventory().setItem(i, gameWeapons.getGameSpell());
                }
            }
        }
    }

    public static void registerStaffGameItems(Player player) throws InstantiationException, IllegalAccessException {
        List<ItemStack> modifiedItems = new ArrayList<>();

        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                modifiedItems.add(itemStack);
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID != null) {
                boolean modified = false; // Flag to track if the item has been modified

                for (GameItem gameItem : Main.getInstance().getGameItems()) {
                    if (gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                        if (gameItem instanceof GameStaff) {
                            GameStaff gameStaff = ((GameStaff) gameItem);
                            if (!Main.getInstance().getGameStaffs().contains(gameStaff)) {
                                GameStaff newGameWeapons = gameStaff.createNewInstance();
                                ItemStack item;
                                item = newGameWeapons.getItemLevel().setLevelAndXP(NBTUtils.getInt(itemStack, "ItemWeaponLevel"), NBTUtils.getInt(itemStack, "ItemWeaponXP"), newGameWeapons);
                                modifiedItems.add(item);
                                modified = true; // Set the flag to indicate modification
                                break; // Break out of the inner loop since modification is done
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
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (modifiedItems.size() > i) {
                if (modifiedItems.get(i) == null || modifiedItems.get(i).getType() == Material.AIR) {
                    player.getInventory().setItem(i, null);
                    continue;
                }
                GameStaff gameWeapons = ItemUtil.getGameStaff(modifiedItems.get(i));
                if (gameWeapons != null) {
                    player.getInventory().setItem(i, gameWeapons.getItemLevel().setLevelAndXP(NBTUtils.getInt(modifiedItems.get(i), "ItemWeaponLevel"), NBTUtils.getInt(modifiedItems.get(i), "ItemWeaponXP"), gameWeapons));
                }
            }
        }
    }

    public static void registerBowGameItems(Player player) throws InstantiationException, IllegalAccessException {
        List<ItemStack> modifiedItems = new ArrayList<>();

        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                modifiedItems.add(itemStack);
                continue;
            }
            String gameItemUUID = NBTUtils.getString(itemStack, "GameItemUUID");
            if (gameItemUUID != null) {
                boolean modified = false; // Flag to track if the item has been modified

                for (GameItem gameItem : Main.getInstance().getGameItems()) {
                    if (gameItem.getItemUUID().equalsIgnoreCase(gameItemUUID)) {
                        if (gameItem instanceof GameBow) {
                            GameBow gameWeapons = ((GameBow) gameItem);
                            if (!Main.getInstance().getGameBows().contains(gameWeapons)) {
                                GameBow newGameWeapons = gameWeapons.createNewInstance();
                                modifiedItems.add(newGameWeapons.getGameBow());
                                modified = true; // Set the flag to indicate modification
                                break; // Break out of the inner loop since modification is done
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
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (modifiedItems.size() > i) {
                if (modifiedItems.get(i) == null || modifiedItems.get(i).getType() == Material.AIR) {
                    player.getInventory().setItem(i, null);
                    continue;
                }
                GameBow gameWeapons = ItemUtil.getGameBow(modifiedItems.get(i));
                if (gameWeapons != null) {
                    player.getInventory().setItem(i, gameWeapons.getGameBow());
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
                                modifiedItems.add(newGameArmor.getGameArmor());

                                modified = true; // Set the flag to indicate modification
                                break; // Break out of the inner loop since modification is done
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
                    armorContents[i] = gameArmor.getGameArmor();
                }
            }
        }

        player.getInventory().setArmorContents(armorContents);
    }

}
