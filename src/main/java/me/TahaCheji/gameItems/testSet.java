package me.TahaCheji.gameItems;

import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameArmorData.GameArmorSet;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class testSet extends GameArmorSet {


    public testSet() {
        super("Test Set Lore");
        setDescription("yes");
    }

    @Override
    protected void performCustomFullSetBonus(Player player) {
        player.sendMessage("Full set on");
    }

}
