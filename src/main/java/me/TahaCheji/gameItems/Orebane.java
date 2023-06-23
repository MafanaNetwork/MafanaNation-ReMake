package me.TahaCheji.gameItems;

import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Orebane extends GameItem {
    public Orebane() {
        super(ChatColor.DARK_PURPLE + "Orebane", Material.AMETHYST_SHARD, true, ItemType.ITEM, ItemRarity.LAPIS,
                ChatColor.LIGHT_PURPLE  + "try something new.");
    }
}
