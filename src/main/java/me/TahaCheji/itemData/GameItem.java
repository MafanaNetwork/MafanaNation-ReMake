package me.TahaCheji.itemData;

import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameItem {

    private final String name;
    private final Material material;
    private final ItemType itemType;
    private final ItemRarity itemRarity;
    private final int UUID;
    public List<String> lore;


    public GameItem(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        this.name = name;
        this.material = material;
        this.itemType = itemType;
        this.itemRarity = itemRarity;
        this.UUID = ItemUtil.stringToSeed(material.name() + name + itemRarity.toString());
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        List<String> list = new ArrayList<>();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);
        meta.setLore(list);
        item.setItemMeta(meta);
        item = NBTUtils.setString(item, "ItemKey", item.getItemMeta().getDisplayName());
        item = NBTUtils.setString(item, "ItemType", itemType.getLore());
        item = NBTUtils.setString(item, "ItemRarity", itemRarity.getLore());
        item = ItemUtil.storeIntInItem(item, this.UUID, "GameItemUUID");
        return item;
    }

    public void onItemUse(Player player, ItemStack item) {

    }

    public abstract void onItemStackCreate(ItemStack var1);

    public abstract void onItemHoldAction(Player var1, ItemStack var2);

    public abstract boolean leftClickAirAction(Player var1, ItemStack var2);

    public abstract boolean leftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);

    public abstract boolean rightClickAirAction(Player var1, ItemStack var2);

    public abstract boolean rightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);

    public abstract boolean shiftLeftClickAirAction(Player var1, ItemStack var2);

    public abstract boolean shiftLeftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);

    public abstract boolean shiftRightClickAirAction(Player var1, ItemStack var2);

    public abstract boolean shiftRightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);

    public abstract boolean middleClickAction(Player var1, ItemStack var2);

    public abstract boolean hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4);

    public abstract boolean breakBlockAction(Player var1, BlockBreakEvent var2, Block var3, ItemStack var4);

    public abstract boolean clickedInInventoryAction(Player var1, InventoryClickEvent var2, ItemStack var3, ItemStack var4);

    public boolean compare(ItemStack other) {
        int otherUUID = ItemUtil.getIntFromItem(other, "GameItemUUID");
        return otherUUID == this.UUID;
    }


    public int getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public ItemRarity getItemRarity() {
        return itemRarity;
    }
}
