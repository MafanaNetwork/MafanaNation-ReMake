package me.TahaCheji.gameItems;

import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.Material;

public class test3 extends GameArmor {


    public test3() {
        super("Armor3", Material.DIAMOND_BOOTS, ItemType.BOOTS, ItemRarity.ADMIN);
        setLore("test", "test");
        setStrength(4);
        setMagic(2);
        setMobility(2);
        setHealth(1);
        setArmor(1);
        setArmorSet(testSet.getInstance());
        testSet.getInstance().setBoots(getGameArmor());
    }

}
