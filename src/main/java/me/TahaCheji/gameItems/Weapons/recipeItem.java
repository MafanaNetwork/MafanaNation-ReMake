package me.TahaCheji.gameItems.Weapons;

import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.Material;

public class recipeItem extends GameItem {
    public recipeItem() {
        super("Item", Material.DIAMOND, ItemType.ITEM, ItemRarity.ADMIN);
    }
}
