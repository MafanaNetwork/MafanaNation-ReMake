package me.TahaCheji.itemData.GameWeaponData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class GameWeapons extends GameItem {

    public int strength;
    public int health;
    public int armor;
    public int magic;
    public int mobility;
    public GameWeaponLevel itemLevel = null;
    public GameItemAbility gameItemAbility;
    public List<String> lore;
    private String weaponUUID;

    public GameWeapons(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
        this.weaponUUID = UUID.randomUUID().toString();
    }

    public ItemStack getGameWeapon() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "[" + getItemLevel().getLevel() + "] " + getItemRarity().getColor() + getName());
        List<String> lore = new ArrayList<>();
        lore.add(getItemRarity().getColor() + getItemRarity().getLore() + ChatColor.DARK_GRAY + "| " + getItemType().getLore() + " | " + getMaterial().name());
        lore.addAll(getItemLevel().getLore(itemStack));
        lore.add(ChatColor.DARK_GRAY + ItemUtil.itemLoreDash(lore.get(0)));
        lore.add("");
        if (strength != 0) {
            lore.add(ChatColor.DARK_RED + "⚔Strength: " + ChatColor.DARK_GRAY + "+" + getStrength());
        }
        if (health != 0) {
            lore.add(ChatColor.RED + "❤Health: " + ChatColor.DARK_GRAY + "+" + getHealth());
        }
        if (armor != 0) {
            lore.add(ChatColor.YELLOW + "⛨Armor: " + ChatColor.DARK_GRAY + "+" + getArmor());
        }
        if (magic != 0) {
            lore.add(ChatColor.BLUE + "[M]Magic: " + ChatColor.DARK_GRAY + "+" + getMagic());
        }
        if (mobility != 0) {
            lore.add(ChatColor.DARK_GREEN + "〰Mobility: " + ChatColor.DARK_GRAY + "+" + getMobility());
        }
        lore.add("");
        if (gameItemAbility != null) {
            lore.addAll(getGameAbility().toLore());
        }
        lore.add("");
        lore.addAll(getLore());
        meta.setLore(lore);
        itemStack.setItemMeta(meta);


        this.weaponUUID = UUID.randomUUID().toString();
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponStrength", strength);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponHealth", health);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponArmor", armor);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMagic", magic);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMobility", mobility);
        itemStack = NBTUtils.setString(itemStack, "GameWeaponUUID", getWeaponUUID());
        itemStack = itemLevel.getLevelItemStack(itemStack);
        return itemStack.clone();
    }


    public GameWeapons createNewInstance() throws InstantiationException, IllegalAccessException {
        GameWeapons gameWeapons = this.getClass().newInstance();
        Main.getInstance().getGameWeapons().add(gameWeapons);
        gameWeapons.setWeaponUUID(getWeaponUUID());
        return gameWeapons;
    }

    public ItemStack setItemClone(boolean x, ItemStack itemStack) {
        return NBTUtils.setBoolean(itemStack, "ItemClone", x);
    }


    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public void setWeaponUUID(String weaponUUID) {
        this.weaponUUID = weaponUUID;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getWeaponUUID() {
        return weaponUUID;
    }

    public void setGameAbility(GameItemAbility gameItemAbility) {
        this.gameItemAbility = gameItemAbility;
    }

    public GameWeaponLevel getItemLevel() {
        return itemLevel;
    }

    public GameItemAbility getGameAbility() {
        return gameItemAbility;
    }

    public void setItemLevel(GameWeaponLevel itemLevel) {
        this.itemLevel = itemLevel;
    }

    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getMagic() {
        return magic;
    }

    public int getMobility() {
        return mobility;
    }
}
