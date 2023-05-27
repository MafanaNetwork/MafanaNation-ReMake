package me.TahaCheji.itemData;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface GameItemEvents {

    default boolean onItemUse(Player var1, ItemStack var2) {return false;}
    default boolean onItemJumpAction(Player var1, ItemStack var2) {return false;}
    default boolean onItemWhileWearingAction(Player var1, ItemStack var2) {return false;}
    default boolean onItemShiftAction(Player var1, ItemStack var2) {return false;}
    default boolean onItemHoldAction(Player var1, ItemStack var2) {
        return false;
    }
    default boolean leftClickAirAction(Player var1, ItemStack var2) {
        return false;
    }
    default boolean leftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {return false;}
    default boolean rightClickAirAction(Player var1, ItemStack var2) {
        return false;
    }
    default boolean rightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {return false;}
    default boolean shiftLeftClickAirAction(Player var1, ItemStack var2) {
        return false;
    }
    default boolean shiftLeftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {return false;}
    default boolean shiftRightClickAirAction(Player var1, ItemStack var2) {
        return false;
    }
    default boolean shiftRightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {return false;}
    default boolean hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4) {return false;}
    default boolean breakBlockAction(Player var1, BlockBreakEvent var2, Block var3, ItemStack var4) {
        return false;
    }
    default boolean clickedInInventoryAction(Player var1, InventoryClickEvent var2, ItemStack var3, ItemStack var4) {return false;}

}
