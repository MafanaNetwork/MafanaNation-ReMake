package me.TahaCheji.itemData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class GameBow extends GameItem{

    public int strength;
    public int mobility;
    public int attackSpeed;
    public List<String> lore;
    private String bowUUID;

    public GameBow(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
        this.bowUUID = UUID.randomUUID().toString();
    }

    public ItemStack getGameBow() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        itemStack.setItemMeta(meta);




        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponStrength", strength);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMobility", mobility);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponAttackSpeed", attackSpeed);
        itemStack = NBTUtils.setString(itemStack, "GameBowUUID", getBowUUID());
        return itemStack;
    }

    public GameBow createNewInstance() throws InstantiationException, IllegalAccessException {
        GameBow game = this.getClass().newInstance();
        Main.getInstance().getGameBows().add(game);
        game.setBowUUID(getBowUUID());
        return game;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public void setBowUUID(String bowUUID) {
        this.bowUUID = bowUUID;
    }

    public String getBowUUID() {
        return bowUUID;
    }

    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public int getMobility() {
        return mobility;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }
}
