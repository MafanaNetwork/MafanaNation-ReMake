package me.TahaCheji.playerData;

import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GamePlayerStats {

    private final GamePlayer gamePlayer;
    private int magic = 0;
    private int health = 0;
    private int armor = 0;
    private int strength = 0;
    private int mobility = 0;


    public GamePlayerStats(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void updateStats(ItemStack heldItem, ItemStack[] armor) {
        int armorMagic = 0;
        int armorHealth = 0;
        int armorArmor = 0;
        int armorStrength = 0;
        int armorMobility = 0;

        // Calculate the stats provided by the equipped armor
        for (ItemStack armorPiece : armor) {
            if (armorPiece != null && !armorPiece.getType().isAir()) {
                armorMagic += NBTUtils.getInt(armorPiece, "ItemArmorMagic");
                armorHealth += NBTUtils.getInt(armorPiece, "ItemArmorHealth");
                armorArmor += NBTUtils.getInt(armorPiece, "ItemArmorArmor");
                armorStrength += NBTUtils.getInt(armorPiece, "ItemArmorStrength");
                armorMobility += NBTUtils.getInt(armorPiece, "ItemArmorMobility");
            }
        }

        if (heldItem != null && !heldItem.getType().isAir()) {
            setMagic(NBTUtils.getInt(heldItem, "ItemWeaponMagic") + armorMagic);
            setHealth(NBTUtils.getInt(heldItem, "ItemWeaponHealth") + armorHealth);
            setArmor(NBTUtils.getInt(heldItem, "ItemWeaponArmor") + armorArmor);
            setStrength(NBTUtils.getInt(heldItem, "ItemWeaponStrength") + armorStrength);
            setMobility(NBTUtils.getInt(heldItem, "ItemWeaponMobility") + armorMobility);
        } else {
            setMagic(armorMagic);
            setHealth(armorHealth);
            setArmor(armorArmor);
            setStrength(armorStrength);
            setMobility(armorMobility);
        }
    }

    private void clearStats() {
        magic = 0;
        health = 0;
        armor = 0;
        strength = 0;
        mobility = 0;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getMagic() {
        return magic;
    }

    public int getMobility() {
        return mobility;
    }

    public int getStrength() {
        return strength;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
