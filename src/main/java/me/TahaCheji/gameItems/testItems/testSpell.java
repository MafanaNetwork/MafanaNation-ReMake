package me.TahaCheji.gameItems.testItems;

import me.TahaCheji.Main;
import me.TahaCheji.itemData.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class testSpell extends GameSpell {
    public testSpell() {
        super("Nut", Material.SALMON, ItemType.SPELL, ItemRarity.LAPIS);
        setGameAbility(new GameItemAbility("Nutsss", ItemAbilityType.LEFT_CLICK,5,  5 , 10, "IM ABOUT TO BUSSSSST"));
    }

    @Override
    public boolean rightClickAirAction(Player var1, ItemStack var2) {
        GameItemCoolDown gameItemCoolDown = new GameItemCoolDown(this, Main.getInstance().getGamePlayer(var1));
        if(gameItemCoolDown.ifCanUse(this)) {
            return false;
        }
        gameItemCoolDown.addPlayerToCoolDownSpell();
        var1.sendMessage("Nutted");
        destroy(var2, 1);
        return true;
    }
}
