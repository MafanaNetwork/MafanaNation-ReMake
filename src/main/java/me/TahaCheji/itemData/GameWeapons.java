package me.TahaCheji.itemData;

import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameWeapons extends GameItem{

    public int strength;
    public int health;
    public int armor;
    public int magic;
    public int mobility;
    public GameItemLevel itemLevel = new GameItemLevel(0, 0);
    public GameAbility gameAbility;
    public List<String> lore;

    public GameWeapons(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
    }

    public ItemStack getGameWeapon() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(getName());
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.addAll(getItemLevel().getLore(itemStack));
        meta.setLore(lore);
        itemStack.setItemMeta(meta);



        itemStack = itemLevel.getLevelItemStack(itemStack);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponStrength", strength);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponHealth", health);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponArmor", armor);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMagic", magic);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMobility", mobility);
        return itemStack;
    }

    public void setGameAbility(GameAbility gameAbility) {
        this.gameAbility = gameAbility;
    }

    public GameItemLevel getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(GameItemLevel itemLevel) {
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
