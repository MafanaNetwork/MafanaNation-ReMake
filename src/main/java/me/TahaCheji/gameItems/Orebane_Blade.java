package me.TahaCheji.gameItems;

import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Orebane_Blade extends GameItem {
    public Orebane_Blade() {
        super(ChatColor.GRAY + "Orebane Blade", Material.AMETHYST_SHARD, true, ItemType.ITEM, ItemRarity.OBSIDAIN,
                ChatColor.DARK_PURPLE  + "I guess you did.");
    }
}
