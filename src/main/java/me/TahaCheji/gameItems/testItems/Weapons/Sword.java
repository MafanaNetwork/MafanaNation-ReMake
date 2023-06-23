package me.TahaCheji.gameItems.testItems.Weapons;

import me.TahaCheji.Main;
import me.TahaCheji.itemData.*;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sword extends GameWeapons {

    public Sword(){
        super("Test", Material.DIAMOND_SWORD, ItemType.SWORD, ItemRarity.ADMIN);
        setLore("test");
        setStrength(100);
        setMagic(200);
        setMobility(298);
        setHealth(123);
        setArmor(112);
        setGameAbility(new GameItemAbility("Hump", ItemAbilityType.LEFT_CLICK, 5,5 , 10, "zzzzz"));
    }

    @Override
    public boolean leftClickAirAction(Player var1, ItemStack var2) {
        GameItemCoolDown gameItemCoolDown = new GameItemCoolDown(this, Main.getInstance().getGamePlayer(var1));
        if(gameItemCoolDown.ifCanUse(this)) {
            return false;
        }
        gameItemCoolDown.addPlayerToCoolDownWeapon();
        var1.sendMessage("1");
        return true;
    }

    @Override
    public boolean onItemJumpAction(Player var1, ItemStack var2) {
        var1.sendMessage("Jump");
        return true;
    }

    @Override
    public boolean onItemHoldAction(Player var1, ItemStack var2) {
        var1.sendMessage("Holding");
        return true;
    }

    @Override
    public boolean onItemShiftAction(Player var1, ItemStack var2) {
        var1.sendMessage("Sneaking");
        return true;
    }


}
