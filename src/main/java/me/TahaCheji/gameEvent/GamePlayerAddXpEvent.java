package me.TahaCheji.gameEvent;

import de.tr7zw.nbtapi.NBTItem;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.itemData.GameItemXpAddEvent;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class GamePlayerAddXpEvent implements Listener {


    @EventHandler
    public void xpAddEvent(GameItemXpAddEvent event) {
        ItemStack item = event.getGetRawItem();
        if(event.getPlayer().isOp()) {
            return;
        }
        if(new NBTItem(item).hasTag("RENTED")) {
            return;
        }
        GameWeapons gameWeapon = ItemUtil.getGameWeapon(item);
        assert gameWeapon != null;
        gameWeapon.setGameItemLevel(event.getGameItemLevel());
        event.getPlayer().setItemInHand(gameWeapon.getGameWeapon());
    }
}
