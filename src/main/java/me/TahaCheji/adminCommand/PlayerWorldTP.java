package me.TahaCheji.adminCommand;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerWorldTP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /mftp <world>");
            return true;
        }

        Player player = (Player) sender;
        String worldName = args[0];

        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            world = Bukkit.createWorld(new WorldCreator(worldName));
            if (world == null) {
                sender.sendMessage("Failed to load world: " + worldName);
                return true;
            }
        }
        player.teleport(world.getSpawnLocation());
        sender.sendMessage("Teleported to world: " + world.getName());

        return true;
    }

}
