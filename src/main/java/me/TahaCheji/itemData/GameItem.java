package me.TahaCheji.itemData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
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
import java.util.UUID;

public class GameItem implements GameItemEvents {

    private final String name;
    private final Material material;
    private final ItemType itemType;
    private final ItemRarity itemRarity;
    private final String itemUUID;
    private boolean glow;
    public List<String> lore;


    public GameItem(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        this.name = name;
        this.material = material;
        this.itemType = itemType;
        this.itemRarity = itemRarity;
        this.itemUUID = getName();
    }

    public GameItem(String name, Material material, boolean glow, ItemType itemType, ItemRarity itemRarity, String... lore) {
        this.name = name;
        this.material = material;
        this.glow = glow;
        this.itemType = itemType;
        this.itemRarity = itemRarity;
        this.itemUUID = getName();
        this.lore = Arrays.asList(lore);
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        List<String> list = new ArrayList<>();
        meta.setDisplayName(ChatColor.DARK_GRAY + "[" + name + ChatColor.DARK_GRAY + "]" );
        list.add(getItemRarity().getColor() + getItemRarity().getLore() + ChatColor.DARK_GRAY + "| " + getItemType().getLore() + " | " + getMaterial().name());
        if(glow) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);
        list.add(ChatColor.DARK_GRAY + ItemUtil.itemLoreDash(list.get(0)));
        list.add("");
        if(lore != null) {
            list.addAll(lore);
        }
        list.add("");
        meta.setLore(list);
        item.setItemMeta(meta);
        item = NBTUtils.setString(item, "ItemKey", item.getItemMeta().getDisplayName());
        item = NBTUtils.setString(item, "ItemType", itemType.getLore());
        item = NBTUtils.setString(item, "ItemRarity", itemRarity.getLore());
        item = NBTUtils.setString(item, "GameItemUUID", getItemUUID());
        return item;
    }


    public void registerItem() {
        Main.getInstance().getGameItems().add(this);
        System.out.println("Registered " + name);
    }
    public String getItemUUID() {
        return itemUUID;
    }

    public boolean compare(ItemStack other) {
        String otherUUID = NBTUtils.getString(other, "GameItemUUID");
        return otherUUID == this.itemUUID;
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
