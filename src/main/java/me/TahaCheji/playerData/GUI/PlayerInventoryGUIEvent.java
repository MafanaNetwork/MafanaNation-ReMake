package me.TahaCheji.playerData.GUI;

import me.TahaCheji.Main;
import me.TahaCheji.adminCommand.PlayerInventoryAdmin;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerInventoryGUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (event.getSlot() == 53) {
            event.setCancelled(true);
            Iterator<Player> playerIterator = PlayerInventoryAdmin.inventoryPlayer.iterator();
            while (playerIterator.hasNext()) {
                Player player = playerIterator.next();
                if (player == null) {
                    continue;
                }
                if (inventory.getHolder() == null && event.getView().getTitle().contains(player.getName())) {
                    List<ItemStack> guiInv = new ArrayList<>();
                    for (int i = 0; i < 35; i++) {
                        guiInv.add(inventory.getItem(i));
                    }
                    updatePlayerInventory(player, guiInv);
                    List<ItemStack> guiArmor = new ArrayList<>();
                    guiArmor.add(inventory.getItem(51));
                    guiArmor.add(inventory.getItem(50));
                    guiArmor.add(inventory.getItem(49));
                    guiArmor.add(inventory.getItem(48));
                    updatePlayerArmor(player, guiArmor);
                    inventory.close();
                    playerIterator.remove();
                    event.getWhoClicked().sendMessage(ChatColor.YELLOW + "[MafanaNation Manager]: Saved the players inventory");
                }
            }

            Iterator<OfflinePlayer> offlinePlayerIterator = PlayerInventoryAdmin.inventoryOfflinePlayer.iterator();
            while (offlinePlayerIterator.hasNext()) {
                OfflinePlayer player = offlinePlayerIterator.next();
                if (player == null) {
                    continue;
                }
                if (inventory != null && inventory.getHolder() == null && event.getView().getTitle().contains(player.getName())) {
                    List<ItemStack> guiInv = new ArrayList<>();
                    for (int i = 0; i < 35; i++) {
                        guiInv.add(inventory.getItem(i));
                    }
                    updatePlayerInventory(player, guiInv);
                    List<ItemStack> guiArmor = new ArrayList<>();
                    guiArmor.add(inventory.getItem(51));
                    guiArmor.add(inventory.getItem(50));
                    guiArmor.add(inventory.getItem(49));
                    guiArmor.add(inventory.getItem(48));
                    updatePlayerArmor(player, guiArmor);
                    inventory.close();
                    offlinePlayerIterator.remove();
                    event.getWhoClicked().sendMessage(ChatColor.YELLOW + "[MafanaNation Manager]: Saved the players inventory");
                    Main.getInstance().getGamePlayers().remove(Main.getInstance().getGamePlayer(player));
                }
            }
        } else if (event.getSlot() == 52) {
            event.setCancelled(true);
            inventory.close();

        }
    }
    private void updatePlayerInventory(Player player, List<ItemStack> items) {
        Main.getInstance().getGamePlayer(player).getInventory().setInventoryItems(items);
    }
    private void updatePlayerArmor(Player player, List<ItemStack> armor) {
        Main.getInstance().getGamePlayer(player).getInventory().setInventoryArmor(armor);
    }

    private void updatePlayerInventory(OfflinePlayer player, List<ItemStack> items) {
        Main.getInstance().getGamePlayer(player).getInventory().setInventoryItems(items, player);
    }
    private void updatePlayerArmor(OfflinePlayer player, List<ItemStack> armor) {
        Main.getInstance().getGamePlayer(player).getInventory().setInventoryArmor(armor, player);
    }
}
