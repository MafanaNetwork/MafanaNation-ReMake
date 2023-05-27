package me.TahaCheji.adminCommand;

import me.TahaCheji.Main;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobsGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerMobAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("mfmob")) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: Error");
                return true;
            }
            if (!player.isOp()) {
                player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: You Do Not Have The Permission To Do This Command");
                return true;
            }
            if(args[0].equalsIgnoreCase("spawn")) {
                if(args.length == 1) {
                    new GameMobsGUI().getAllMobsGui().open(player);
                    return true;
                }
                for(GameMob createMob : Main.getInstance().getGameMobs()) {
                    GameMob mob = createMob.getMob(ChatColor.stripColor(args[1]));
                    if(mob == null) {
                        return true;
                    } else {
                        mob.spawnMob(player.getLocation(), player);
                    }
                }
            }
            if(args[0].equalsIgnoreCase("killall")) {
                for (GameMob mob : Main.getInstance().getActiveMobs()) {
                    mob.killMob();
                }
                for (GameMob mob : Main.getInstance().getActiveBoss()) {
                    mob.killMob();
                }
            }
        }
        return false;
    }
}
