package me.TahaCheji.itemData;

import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public abstract class GameArmor extends GameItem{

    public int strength;
    public int health;
    public int armor;
    public int magic;
    public int mobility;
    public List<String> lore;

    public GameArmor(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
    }

    public ItemStack getGameArmor() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        itemStack.setItemMeta(meta);




        itemStack = NBTUtils.setInt(itemStack, "ItemArmorStrength", strength);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorHealth", health);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorArmor", armor);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorMagic", magic);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorMobility", mobility);
        return itemStack;
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
