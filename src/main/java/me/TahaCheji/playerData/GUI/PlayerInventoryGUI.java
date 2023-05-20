package me.TahaCheji.playerData.GUI;

import me.TahaCheji.Inv;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerInventoryGUI implements Listener {

    public Inventory inventory;

    public void createGUI(List<ItemStack> items, List<ItemStack> armor, Player player, Player sender) {
        Inventory gui = Bukkit.createInventory(null, 54, player.getName() + " InventoryEdit");
        for (int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            gui.setItem(i, item);
        }
        ItemStack boots = armor.get(0);
        ItemStack leggings = armor.get(1);
        ItemStack chestplate = armor.get(2);
        ItemStack helmet = armor.get(3);
        ItemStack mainItem = items.get(0);

        ArrayList<String> lore = new ArrayList<String>();
        ItemStack greystainedglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta newmeta = greystainedglass.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + " ");
        newmeta.setLore(lore);
        greystainedglass.setItemMeta(newmeta);

        ItemStack saveItem = new ItemStack(Material.MAP);
        ItemMeta saveItemMeta = saveItem.getItemMeta();
        saveItemMeta.setDisplayName(ChatColor.GREEN + "Close and Save");
        saveItemMeta.setLore(lore);
        saveItem.setItemMeta(saveItemMeta);

        ItemStack closeItem = new ItemStack(Material.MAP);
        ItemMeta closeItemMeta = closeItem.getItemMeta();
        closeItemMeta.setDisplayName(ChatColor.RED + "Close and Not Save");
        closeItemMeta.setLore(lore);
        closeItem.setItemMeta(closeItemMeta);

        gui.setItem(36, greystainedglass);
        gui.setItem(37, greystainedglass);
        gui.setItem(38, greystainedglass);
        gui.setItem(39, greystainedglass);
        gui.setItem(40, greystainedglass);
        gui.setItem(41, greystainedglass);
        gui.setItem(42, greystainedglass);
        gui.setItem(43, greystainedglass);
        gui.setItem(44, greystainedglass);
        gui.setItem(45, greystainedglass);
        gui.setItem(46, greystainedglass);
        gui.setItem(52, closeItem);
        gui.setItem(53, saveItem);

        gui.setItem(47, mainItem);
        gui.setItem(48, helmet);
        gui.setItem(49, chestplate);
        gui.setItem(50, leggings);
        gui.setItem(51, boots);
        // Open the GUI for the player
        sender.openInventory(gui);
        inventory = gui;
    }

    public void createGUI(List<ItemStack> items, List<ItemStack> armor, OfflinePlayer player, Player sender) {
        Inventory gui = Bukkit.createInventory(null, 54, player.getName() + " InventoryEdit");
        for (int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            gui.setItem(i, item);
        }
        ItemStack boots = armor.get(0);
        ItemStack leggings = armor.get(1);
        ItemStack chestplate = armor.get(2);
        ItemStack helmet = armor.get(3);
        ItemStack mainItem = items.get(0);

        ArrayList<String> lore = new ArrayList<String>();
        ItemStack greystainedglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta newmeta = greystainedglass.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + " ");
        newmeta.setLore(lore);
        greystainedglass.setItemMeta(newmeta);

        ItemStack saveItem = new ItemStack(Material.MAP);
        ItemMeta saveItemMeta = saveItem.getItemMeta();
        saveItemMeta.setDisplayName(ChatColor.GREEN + "Close and Save");
        saveItemMeta.setLore(lore);
        saveItem.setItemMeta(saveItemMeta);

        ItemStack closeItem = new ItemStack(Material.MAP);
        ItemMeta closeItemMeta = closeItem.getItemMeta();
        closeItemMeta.setDisplayName(ChatColor.RED + "Close and Not Save");
        closeItemMeta.setLore(lore);
        closeItem.setItemMeta(closeItemMeta);

        gui.setItem(36, greystainedglass);
        gui.setItem(37, greystainedglass);
        gui.setItem(38, greystainedglass);
        gui.setItem(39, greystainedglass);
        gui.setItem(40, greystainedglass);
        gui.setItem(41, greystainedglass);
        gui.setItem(42, greystainedglass);
        gui.setItem(43, greystainedglass);
        gui.setItem(44, greystainedglass);
        gui.setItem(45, greystainedglass);
        gui.setItem(46, greystainedglass);
        gui.setItem(52, closeItem);
        gui.setItem(53, saveItem);

        gui.setItem(47, mainItem);
        gui.setItem(48, helmet);
        gui.setItem(49, chestplate);
        gui.setItem(50, leggings);
        gui.setItem(51, boots);
        // Open the GUI for the player
        sender.openInventory(gui);
        inventory = gui;
    }


}
