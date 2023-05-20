package me.TahaCheji.playerData;

import me.TahaCheji.Inv;
import me.TahaCheji.InventoryDataHandler;
import me.TahaCheji.objects.DatabaseInventoryData;
import me.TahaCheji.playerData.GUI.PlayerInventoryGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamePlayerInventory {

    public List<ItemStack> inventoryItems;
    public List<ItemStack> inventoryArmor;
    public GamePlayer gamePlayer;
    public OfflinePlayer offlinePlayer;

    public GamePlayerInventory(GamePlayer gamePlayer) throws Exception {
        this.gamePlayer = gamePlayer;
        DatabaseInventoryData data = Inv.getInstance().getInvMysqlInterface().getData(gamePlayer.getPlayer());
        if(gamePlayer.getPlayer().isOnline()){
            List<ItemStack> pInv = new ArrayList<>();
            for (ItemStack itemStack : gamePlayer.getPlayer().getInventory()){
                pInv.add(itemStack);
            }
            inventoryItems = pInv;
            inventoryArmor = Arrays.asList(gamePlayer.getPlayer().getInventory().getArmorContents());
            return;
        }

        inventoryItems = Arrays.asList(new InventoryDataHandler(Inv.getInstance()).decodeItems(data.getRawInventory()));
        inventoryArmor = Arrays.asList(new InventoryDataHandler(Inv.getInstance()).decodeItems(data.getRawArmor()));
    }

    public GamePlayerInventory(OfflinePlayer offlinePlayer) throws Exception {
        this.offlinePlayer = offlinePlayer;
        DatabaseInventoryData data = Inv.getInstance().getInvMysqlInterface().getData(offlinePlayer);
        inventoryItems = Arrays.asList(new InventoryDataHandler(Inv.getInstance()).decodeItems(data.getRawInventory()));
        inventoryArmor = Arrays.asList(new InventoryDataHandler(Inv.getInstance()).decodeItems(data.getRawArmor()));
    }

    public List<ItemStack> getInventoryArmor() {
        return inventoryArmor;
    }

    public List<ItemStack> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryArmor(List<ItemStack> inventoryArmor) {
        this.inventoryArmor = inventoryArmor;
        Player player = gamePlayer.getPlayer();
        if(player.isOnline()){
            player.getInventory().setArmorContents(inventoryArmor.toArray(new ItemStack[0]));

            player.updateInventory(); // Update the player's inventory view
            player.sendMessage(ChatColor.YELLOW + "[MafanaNation Manager]: Your armor has been updated.");
            return;
        }
        saveOfflineInventory(inventoryArmor, getInventoryArmor(), player);
    }

    public void setInventoryItems(List<ItemStack> inventoryItems) {
        Player player = gamePlayer.getPlayer();
        this.inventoryItems = inventoryItems;
        if(player.isOnline()){
            player.getInventory().clear(); // Clear the player's current inventory

            for (int i = 0; i < inventoryItems.size(); i++) {
                ItemStack item = inventoryItems.get(i);
                player.getInventory().setItem(i, item);
            }

            player.updateInventory(); // Update the player's inventory view
            player.sendMessage(ChatColor.YELLOW + "[MafanaNation Manager]: Your inventory has been updated.");
            return;

        }
        saveOfflineInventory(inventoryItems, getInventoryArmor(), player);
    }

    public void setInventoryArmor(List<ItemStack> inventoryArmor, OfflinePlayer player) {
        this.inventoryArmor = inventoryArmor;
        saveOfflineInventory(getInventoryItems(), inventoryArmor, player);
    }

    public void setInventoryItems(List<ItemStack> inventoryItems, OfflinePlayer player) {
        this.inventoryItems = inventoryItems;
        saveOfflineInventory(inventoryItems, getInventoryArmor(), player);
    }

    public void saveOfflineInventory(List<ItemStack> inventoryItems, List<ItemStack> inventoryArmor, OfflinePlayer player) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(Inv.getInstance(), () -> {
            try {
                ItemStack[] inventory = inventoryItems.toArray(new ItemStack[0]);
                ItemStack[] armor = inventoryArmor.toArray(new ItemStack[0]);
                Inv.getInstance().getInvMysqlInterface().setData(player, Inv.getInstance().getInventoryDataHandler().encodeItems(inventory), Inv.getInstance().getInventoryDataHandler().encodeItems(armor), "True");
                //Inv.getInstance().getInventoryDataHandler().onDataSaveFunction(player, true, "true", inventory, armor);
            } catch (Exception e) {
                    e.printStackTrace(); // Log the exception
                }
        }, 2L);

    }

}
