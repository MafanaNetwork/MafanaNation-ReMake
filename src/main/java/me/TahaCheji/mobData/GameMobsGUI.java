package me.TahaCheji.mobData;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.MobUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GameMobsGUI implements Listener {
    public PaginatedGui getAllMobsGui() {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text(ChatColor.GOLD + "All Game Mobs"))
                .rows(6)
                .pageSize(54)
                .disableAllInteractions()
                .create();

        List<String> lore = new ArrayList<>();
        ItemStack greystainedglass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta newmeta = greystainedglass.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + " ");
        newmeta.setLore(lore);
        greystainedglass.setItemMeta(newmeta);

        gui.setItem(0, new GuiItem(greystainedglass));
        gui.setItem(1,new GuiItem(greystainedglass));
        gui.setItem(2,new GuiItem(greystainedglass));
        gui.setItem(3,new GuiItem(greystainedglass));
        gui.setItem(4,new GuiItem(greystainedglass));
        gui.setItem(5,new GuiItem(greystainedglass));
        gui.setItem(6,new GuiItem(greystainedglass));
        gui.setItem(7,new GuiItem(greystainedglass));
        gui.setItem(8,new GuiItem(greystainedglass));
        gui.setItem(17,new GuiItem(greystainedglass));
        gui.setItem(26,new GuiItem(greystainedglass));
        gui.setItem(35,new GuiItem(greystainedglass));
        gui.setItem(45,new GuiItem(greystainedglass));
        gui.setItem(53,new GuiItem(greystainedglass));
        gui.setItem(52,new GuiItem(greystainedglass));
        gui.setItem(51,new GuiItem(greystainedglass));
        gui.setItem(50,new GuiItem(greystainedglass));
        gui.setItem(48,new GuiItem(greystainedglass));
        gui.setItem(47,new GuiItem(greystainedglass));
        gui.setItem(46,new GuiItem(greystainedglass));
        gui.setItem(44,new GuiItem(greystainedglass));
        gui.setItem(36,new GuiItem(greystainedglass));
        gui.setItem(27,new GuiItem(greystainedglass));
        gui.setItem(18,new GuiItem(greystainedglass));
        gui.setItem(9,new GuiItem(greystainedglass));
        gui.setItem(49, new GuiItem(greystainedglass));
        gui.setItem(6, 3, ItemBuilder.from(Material.PAPER).setName(ChatColor.DARK_GRAY + "Previous").asGuiItem(event -> gui.previous()));
        gui.setItem(6, 7, ItemBuilder.from(Material.PAPER).setName(ChatColor.DARK_GRAY + "Next").asGuiItem(event -> gui.next()));

        for(GameMob createMob : Main.getInstance().getGameMobs()) {
            if(createMob instanceof GameMobBoss) {
                continue;
            }
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD + createMob.getName());
            List<String> itemLore = new ArrayList<>();
            itemLore.add(ChatColor.GOLD + "Name: " + createMob.getName());
            itemLore.add(ChatColor.GOLD + "Health: " + createMob.getMaxHealth());
            itemLore.add(ChatColor.GOLD + "Damage: " + createMob.getDamage());
            itemLore.add(ChatColor.GOLD + "Defence: " + createMob.getDefense());
            itemLore.add(ChatColor.GOLD + "Speed: " + createMob.getSpeed());
            itemLore.add(ChatColor.GOLD + "Strength: " + createMob.getStrength());
            itemLore.add(ChatColor.GOLD + "MobType: " + createMob.getType().toString());
            itemLore.add("");
            itemLore.add(ChatColor.DARK_PURPLE + "LootTable:");
            if(createMob.getLootTable() != null) {
                for (LootItem lootItem : createMob.getLootTable()) {
                    itemLore.add(ChatColor.DARK_GRAY + "-" + lootItem.getItem().getItemMeta().getDisplayName());
                }
            }
            itemMeta.setLore(itemLore);
            itemStack.setItemMeta(itemMeta);
            itemStack = NBTUtils.setString(itemStack, "MobName", createMob.getUuid());
            gui.addItem(new GuiItem(itemStack));
        }
        for(GameMobBoss createMob : Main.getInstance().getGameBosses()) {
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD + createMob.getName());
            List<String> itemLore = new ArrayList<>();
            itemLore.add(ChatColor.GOLD + "Name: " + createMob.getName());
            itemLore.add(ChatColor.GOLD + "Health: " + createMob.getMaxHealth());
            itemLore.add(ChatColor.GOLD + "Damage: " + createMob.getDamage());
            itemLore.add(ChatColor.GOLD + "Defence: " + createMob.getDefense());
            itemLore.add(ChatColor.GOLD + "Speed: " + createMob.getSpeed());
            itemLore.add(ChatColor.GOLD + "Strength: " + createMob.getStrength());
            itemLore.add(ChatColor.GOLD + "MobType: " + createMob.getType().toString());
            itemLore.add("");
            itemLore.add(ChatColor.DARK_PURPLE + "LootTable:");
            if(createMob.getLootTable() != null) {
                for (LootItem lootItem : createMob.getLootTable()) {
                    itemLore.add(ChatColor.DARK_GRAY + "-" + lootItem.getItem().getItemMeta().getDisplayName());
                }
            }
            itemMeta.setLore(itemLore);
            itemStack.setItemMeta(itemMeta);
            itemStack = NBTUtils.setString(itemStack, "MobBossName", createMob.getMobBossUUID().toString());
            gui.addItem(new GuiItem(itemStack));
        }
        return gui;
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().contains("All Game Mobs")) {
            return;
        }
        event.setCancelled(true);
        if(event.getCurrentItem() == null) {
            return;
        }
        if(event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if(event.getCurrentItem().getType() == Material.ORANGE_STAINED_GLASS_PANE) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        GameMob createMob = MobUtil.getMob(NBTUtils.getString(event.getCurrentItem(), "MobName"));
        if(createMob == null) {
            GameMobBoss boss = MobUtil.getBoss(NBTUtils.getString(event.getCurrentItem(), "MobBossName"));
            if(boss == null) {
                return;
            }
            boss.spawnBoss(player.getLocation(), player);
            player.sendMessage(ChatColor.GOLD + "Spawned Boss!");
            return;
        }
        createMob.spawnMob(player.getLocation(), player);
        player.sendMessage(ChatColor.GOLD + "Spawned Mob!");
    }

}
