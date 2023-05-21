package me.TahaCheji.gameEvent;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GamePlayerUseGameItem implements Listener {
    public GamePlayerUseGameItem() {
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerUse(PlayerInteractEvent event) {
        GamePlayer gamePlayer = Main.getInstance().getGamePlayer(event.getPlayer());
            if (ItemUtil.isGameItem(event.getPlayer().getInventory().getItemInMainHand())) {
                this.useGameItem(event, event.getPlayer().getInventory().getItemInMainHand());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getDamager();
            ItemStack mainhand = player.getInventory().getItemInMainHand();
            ItemStack offhand = player.getInventory().getItemInOffHand();
            GameItem uber;
            if (ItemUtil.isGameItem(mainhand)) {
                uber = ItemUtil.getGameItem(mainhand);
                if (uber != null) {
                    if (uber.hitEntityAction(player, event, event.getEntity(), mainhand)) {
                        uber.onItemUse(player, mainhand);
                    }
                }
            }

            if (ItemUtil.isGameItem(offhand)) {
                uber = ItemUtil.getGameItem(offhand);
                if (uber != null) {

                    if (uber.hitEntityAction(player, event, event.getEntity(), offhand)) {
                        uber.onItemUse(player, offhand);
                    }
                }
            }

        }
    }

    private void useGameItem(PlayerInteractEvent event, @NotNull ItemStack item) {
        Player player = event.getPlayer();
        GameItem uber = ItemUtil.getGameItem(item);
        if (uber != null) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (!player.isSneaking()) {
                    if (uber.leftClickAirAction(player, item)) {
                        uber.onItemUse(player, item);
                    }
                } else if (uber.shiftLeftClickAirAction(player, item)) {
                    uber.onItemUse(player, item);
                }
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

                if (!player.isSneaking()) {
                    if (uber.leftClickBlockAction(player, event, event.getClickedBlock(), item)) {
                        uber.onItemUse(player, item);
                    }
                } else if (uber.shiftLeftClickBlockAction(player, event, event.getClickedBlock(), item)) {
                    uber.onItemUse(player, item);
                }
            } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (!player.isSneaking()) {
                    if (uber.rightClickAirAction(player, item)) {
                        uber.onItemUse(player, item);
                    }
                } else if (uber.shiftRightClickAirAction(player, item)) {
                    uber.onItemUse(player, item);
                }
            } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if (!player.isSneaking()) {
                    if (uber.rightClickBlockAction(player, event, event.getClickedBlock(), item)) {
                        uber.onItemUse(player, item);
                    }
                } else if (uber.shiftRightClickBlockAction(player, event, event.getClickedBlock(), item)) {
                    uber.onItemUse(player, item);
                }
            }
        }
    }
}