package me.TahaCheji.gameItems.testItems.Weapons;

import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class recipeItem extends GameItem {
    public recipeItem() {
        super(ChatColor.DARK_AQUA + "Matalic Diamond", Material.DIAMOND, ItemType.ITEM, ItemRarity.DIAMOND);
    }
}
