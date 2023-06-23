package me.TahaCheji.gameItems.testItems;

import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.Material;

public class test2 extends GameArmor {


    public test2() {
        super("Armor2", Material.DIAMOND_LEGGINGS, ItemType.LEGGGINGS, ItemRarity.ADMIN);
        setLore("test", "test");
        setStrength(4);
        setMagic(2);
        setMobility(2);
        setHealth(1);
        setArmor(1);
        setArmorSet(testSet.getInstance());
        testSet.getInstance().setLeggings(getGameArmor());
    }

}
