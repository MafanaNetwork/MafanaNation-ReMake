package me.TahaCheji.gameEvent;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.sun.tools.classfile.ConstantPool;
import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameArmorData.GameArmorSet;
import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GamePlayerUseGameItem implements Listener {
    public GamePlayerUseGameItem() {
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerUse(PlayerInteractEvent event) {
        GamePlayer gamePlayer = Main.getInstance().getGamePlayer(event.getPlayer());
        if(event.getPlayer().getInventory().getItemInMainHand() == null || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
            return;
        }
        if (ItemUtil.isGameItem(event.getPlayer().getInventory().getItemInMainHand())) {
            this.useGameItem(event, event.getPlayer().getInventory().getItemInMainHand());
        }
    }

    @EventHandler
    private void onPlayerShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainhand = player.getInventory().getItemInMainHand();
        GameItem item;
        if (mainhand != null && mainhand.getType() != Material.AIR) {
            if (ItemUtil.isGameItem(mainhand)) {
                item = ItemUtil.getGameItem(mainhand);
                if(!(item instanceof GameArmor)) {
                    if (item != null) {
                        if (player.isSneaking()) {
                            return;
                        }
                        if (item.onItemShiftAction(player, mainhand)) {
                            item.onItemUse(player, mainhand);
                        }
                    }
                }
            }
        }
        for(ItemStack itemStack : player.getInventory().getArmorContents()) {
            if(itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            if(ItemUtil.isGameItem(itemStack)) {
                item = ItemUtil.getGameItem(itemStack);
                if(item != null) {
                    if(player.isSneaking()) {
                        return;
                    }
                    if(item.onItemShiftAction(player, mainhand)) {
                        item.onItemUse(player, mainhand);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        ItemStack mainhand = player.getInventory().getItemInMainHand();
        GameItem item;
        if (mainhand != null && mainhand.getType() != Material.AIR) {
            if (ItemUtil.isGameItem(mainhand)) {
                item = ItemUtil.getGameItem(mainhand);
                if(!(item instanceof GameArmor)) {
                    if (item != null) {
                        if (item.onItemJumpAction(player, mainhand)) {
                            item.onItemUse(player, mainhand);
                        }
                    }
                }
            }
        }
        for(ItemStack itemStack : player.getInventory().getArmorContents()) {
            if(itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            if(ItemUtil.isGameItem(itemStack)) {
                item = ItemUtil.getGameItem(itemStack);
                if(item != null) {
                    if(item.onItemJumpAction(player, mainhand)) {
                        item.onItemUse(player, mainhand);
                    }
                }
            }
        }
    }

    public static void whilePlayerHolding(Player player) {
        ItemStack mainhand = player.getInventory().getItemInMainHand();
        GameItem item;
        if (mainhand != null && mainhand.getType() != Material.AIR) {
            if (ItemUtil.isGameItem(mainhand)) {
                item = ItemUtil.getGameItem(mainhand);
                if(!(item instanceof GameArmor)) {
                    if (item != null) {
                        if (item.onItemHoldAction(player, mainhand)) {
                            item.onItemUse(player, mainhand);
                        }
                    }
                }
            }
        }
        for(ItemStack itemStack : player.getInventory().getArmorContents()) {
            if(itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            if(ItemUtil.isGameItem(itemStack)) {
                item = ItemUtil.getGameItem(itemStack);
                if(item != null) {
                    if(item.onItemWhileWearingAction(player, itemStack)) {
                        item.onItemUse(player, itemStack);
                    }
                }
            }
        }
        for(GameArmorSet armorSet : Main.getInstance().getGameArmorSet()) {
            if(armorSet.hasFullSet(player)) {
                armorSet.applyFullSetBonus(player);
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getDamager();
            ItemStack mainhand = player.getInventory().getItemInMainHand();
            GameItem uber;
            if (mainhand != null && mainhand.getType() != Material.AIR) {
                if (ItemUtil.isGameItem(mainhand)) {
                    uber = ItemUtil.getGameItem(mainhand);
                    if (uber != null) {
                        if (uber.hitEntityAction(player, event, event.getEntity(), mainhand)) {
                            uber.onItemUse(player, mainhand);
                        }
                    }
                }
            }
            for(ItemStack itemStack : player.getInventory().getArmorContents()) {
                if(itemStack == null || itemStack.getType() == Material.AIR) {
                    continue;
                }
                if(ItemUtil.isGameItem(itemStack)) {
                    uber = ItemUtil.getGameItem(itemStack);
                    if(uber != null) {
                        if(uber.hitEntityAction(player, event, event.getEntity(), mainhand)) {
                            uber.onItemUse(player, itemStack);
                        }
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