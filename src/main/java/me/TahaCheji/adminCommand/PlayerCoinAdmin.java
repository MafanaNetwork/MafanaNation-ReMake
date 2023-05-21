package me.TahaCheji.adminCommand;

import me.TahaCheji.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerCoinAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("mf")){
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: Error");
                return true;
            }
            if(args[0].equalsIgnoreCase("coins")) {
                if(args.length != 3) {
                    player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: /mf coins [add/remove/balance] [playername]");
                }
                if(args[1].equalsIgnoreCase("add")) {
                    Player commandPlayer = Bukkit.getPlayer(args[2]);
                    if(commandPlayer == null) {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[2]);
                        Main.getInstance().getPlayerCoins().addCoins(offlinePlayer, Integer.parseInt(args[3]));
                        return false;
                    }
                    assert commandPlayer != null;
                    Main.getInstance().getPlayerCoins().addCoins(commandPlayer, Integer.parseInt(args[3]));
                }
                if(args[1].equalsIgnoreCase("remove")) {
                    Player commandPlayer = Bukkit.getPlayer(args[2]);
                    if(commandPlayer == null) {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[2]);
                        Main.getInstance().getPlayerCoins().removeCoins(offlinePlayer, Integer.parseInt(args[3]));
                        return false;
                    }
                    assert commandPlayer != null;
                    Main.getInstance().getPlayerCoins().removeCoins(commandPlayer, Integer.parseInt(args[3]));
                }
                if(args[1].equalsIgnoreCase("balance")) {

                }
            }
        }
        return false;
    }
}
