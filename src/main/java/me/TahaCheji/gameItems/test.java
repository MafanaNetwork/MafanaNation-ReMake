package me.TahaCheji.gameItems;

import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameArmorData.GameArmorAbility;
import me.TahaCheji.itemData.ItemAbilityType;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class test extends GameArmor {


    public test() {
        super("Armor", Material.DIAMOND_CHESTPLATE, ItemType.CHESTPLATE, ItemRarity.ADMIN);
        setLore("test", "test");
        setStrength(4);
        setMagic(2);
        setMobility(2);
        setHealth(1);
        setArmor(1);
        setArmorSet(testSet.getInstance());
        testSet.getInstance().setChestplate(getGameArmor());
        setGameAbility(new GameArmorAbility("Hump", ItemAbilityType.LEFT_CLICK, 0, "zzzzz"));
    }


    @Override
    public boolean hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4) {
        var1.sendMessage("hit");
        return true;
    }

    @Override
    public boolean onItemShiftAction(Player var1, ItemStack var2) {
        var1.sendMessage("Sneaking Chestplate");
        return true;
    }

    @Override
    public boolean onItemWhileWearingAction(Player var1, ItemStack var2) {
        //var1.sendMessage("Wearing armor");
        return true;
    }
}
