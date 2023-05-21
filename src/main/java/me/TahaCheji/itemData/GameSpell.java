package me.TahaCheji.itemData;

import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GameSpell extends GameItem{

    public boolean onTimeUse;
    public GameAbility gameAbility;


    public GameSpell(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
    }

    public ItemStack getGameSpell() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        itemStack.setItemMeta(meta);


        return itemStack;
    }

    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }

    public void setGameAbility(GameAbility gameAbility) {
        this.gameAbility = gameAbility;
    }

    @Override
    public void onItemStackCreate(ItemStack var1) {

    }

    @Override
    public void onItemHoldAction(Player var1, ItemStack var2) {

    }

    @Override
    public boolean leftClickAirAction(Player var1, ItemStack var2) {
        return false;
    }

    @Override
    public boolean leftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        return false;
    }

    @Override
    public boolean rightClickAirAction(Player var1, ItemStack var2) {
        return false;
    }

    @Override
    public boolean rightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        return false;
    }

    @Override
    public boolean shiftLeftClickAirAction(Player var1, ItemStack var2) {
        return false;
    }

    @Override
    public boolean shiftLeftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        return false;
    }

    @Override
    public boolean shiftRightClickAirAction(Player var1, ItemStack var2) {
        return false;
    }

    @Override
    public boolean shiftRightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        return false;
    }

    @Override
    public boolean middleClickAction(Player var1, ItemStack var2) {
        return false;
    }

    @Override
    public boolean hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4) {
        return false;
    }

    @Override
    public boolean breakBlockAction(Player var1, BlockBreakEvent var2, Block var3, ItemStack var4) {
        return false;
    }

    @Override
    public boolean clickedInInventoryAction(Player var1, InventoryClickEvent var2, ItemStack var3, ItemStack var4) {
        return false;
    }

    public static void destroy(ItemStack item, int quantity) {
        if (item.getAmount() <= quantity) {
            item.setAmount(0);
        } else {
            item.setAmount(item.getAmount() - quantity);
        }

    }

    public void onItemUse(Player player, ItemStack item) {
        onItemUse(player, item);
        if(onTimeUse) {
            destroy(item, 1);
        }
    }

    public void setOnTimeUse(boolean onTimeUse) {
        this.onTimeUse = onTimeUse;
    }


}
