package me.TahaCheji.adminCommand;

import me.TahaCheji.GamePlayerInventory;
import me.TahaCheji.Inv;
import me.TahaCheji.InventoryDataHandler;
import me.TahaCheji.Main;
import me.TahaCheji.objects.DatabaseInventoryData;
import me.TahaCheji.playerData.GUI.PlayerInventoryGUI;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerInventoryAdmin implements CommandExecutor {

    public static List<Player> inventoryPlayer = new ArrayList<>();
    public static List<OfflinePlayer> inventoryOfflinePlayer = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("mfinv")) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: Error");
                return true;
            }
            if(!player.isOp()){
                player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: You Do Not Have The Permission To Do This Command");
                return true;
            }
            if(args[0].equalsIgnoreCase("clear")){
                inventoryPlayer.clear();
                inventoryOfflinePlayer.clear();
                player.sendMessage(ChatColor.YELLOW + "[MafanaNation Manager]: You have cleared the cache");
                return true;
            }
            if (args[0].equalsIgnoreCase("inventory")) {
                Player commandPlayer = Bukkit.getPlayer(args[1]);
                if (commandPlayer == null) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    if (!offlinePlayer.hasPlayedBefore()) {
                        player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: That player does not exist");
                        return true;
                    }
                    DatabaseInventoryData data = Inv.getInstance().getInvMysqlInterface().getData(offlinePlayer);
                    PlayerInventoryGUI inventoryGUI = new PlayerInventoryGUI();
                    try {
                        Main.getInstance().getGamePlayers().add(new GamePlayer(offlinePlayer));
                        inventoryGUI.createGUI(Arrays.asList(new InventoryDataHandler(Inv.getInstance()).decodeItems(data.getRawInventory())), Arrays.asList(new InventoryDataHandler(Inv.getInstance()).decodeItems(data.getRawArmor())), offlinePlayer, player);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    inventoryOfflinePlayer.add(offlinePlayer);
                    return true;
                }
                GamePlayer gamePlayer = Main.getInstance().getGamePlayer(commandPlayer);
                try {
                    GamePlayerInventory inventory = new GamePlayerInventory(Inv.getInstance(), gamePlayer.getPlayer());
                    PlayerInventoryGUI inventoryGUI = new PlayerInventoryGUI();
                    inventoryGUI.createGUI(inventory.getInventoryItems(), inventory.getInventoryArmor(), gamePlayer.getPlayer(), player);
                    inventoryPlayer.add(commandPlayer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }
}
