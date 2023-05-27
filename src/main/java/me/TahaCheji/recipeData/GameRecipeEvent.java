package me.TahaCheji.recipeData;

import me.TahaCheji.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameRecipeEvent implements Listener {

    public HashMap<Player, BukkitTask> opened = new HashMap<>();

    @EventHandler
    public void playerRightClick(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        if (!(inv.getType() == InventoryType.WORKBENCH)) {
            return;
        }
        e.setCancelled(true);
        player.openInventory(GameRecipeCrafting.getCraftingGui());
        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 10, 10);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (!(e.getView().getTitle().contains("MafanaCraft"))) {
            return;
        }
        e.setCancelled(e.getSlot() != 1 && e.getSlot() != 2 && e.getSlot() != 3 &&
                e.getSlot() != 10 && e.getSlot() != 11 && e.getSlot() != 12 &&
                e.getSlot() != 19 && e.getSlot() != 20 && e.getSlot() != 21 && e.getSlot() != 14 && e.getClickedInventory() != player.getInventory());
        if(e.getSlot() == 14) {
            e.setCancelled(true);
            for(GameRecipe gameRecipe : Main.getInstance().getGameRecipes()) {
                gameRecipe.craftItem(player, e.getInventory());
            }
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        if (!(e.getView().getTitle().contains("MafanaCraft"))) {
            return;
        }
        BukkitTask br;
        br = new BukkitRunnable() {
            @Override
            public void run() {
                List<ItemStack> itemsInGui = new ArrayList<>();
                itemsInGui.add(e.getInventory().getItem(1));
                itemsInGui.add(e.getInventory().getItem(2));
                itemsInGui.add(e.getInventory().getItem(3));

                itemsInGui.add(e.getInventory().getItem(10));
                itemsInGui.add(e.getInventory().getItem(11));
                itemsInGui.add(e.getInventory().getItem(12));

                itemsInGui.add(e.getInventory().getItem(19));
                itemsInGui.add(e.getInventory().getItem(20));
                itemsInGui.add(e.getInventory().getItem(21));
                for(GameRecipe gameRecipe : Main.getInstance().getGameRecipes()) {
                    if(gameRecipe.isSame(itemsInGui)) {
                        e.getInventory().setItem(14, gameRecipe.getResult());
                    } else {
                        e.getInventory().setItem(14, null);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 0L);
        opened.put(player, br);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if (!(e.getView().getTitle().contains("MafanaCraft"))) {
            return;
        }
        if (!(e.getInventory().getItem(1) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(1));
        }
        if (!(e.getInventory().getItem(2) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(2));
        }
        if (!(e.getInventory().getItem(3) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(3));
        }

        if (!(e.getInventory().getItem(10) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(10));
        }
        if (!(e.getInventory().getItem(11) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(11));
        }
        if (!(e.getInventory().getItem(12) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(12));
        }

        if (!(e.getInventory().getItem(19) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(19));
        }
        if (!(e.getInventory().getItem(20) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(20));
        }
        if (!(e.getInventory().getItem(21) == null)) {
            player.getInventory().addItem(e.getInventory().getItem(21));
        }
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10f, 0f);
        if(opened.get(player) != null) {
            opened.get(player).cancel();
        }
    }



}
