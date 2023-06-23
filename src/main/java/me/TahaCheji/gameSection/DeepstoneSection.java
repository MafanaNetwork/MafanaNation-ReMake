package me.TahaCheji.gameSection;

import me.TahaCheji.sectionsData.GameSections;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DeepstoneSection extends GameSections {

    public DeepstoneSection() {
        super("Deepstone",
                new Location(Bukkit.getWorld("world"), 2295, 5, 3109),
                new Location(Bukkit.getWorld("world"), 2361, -52, 3191));
    }

    @Override
    public boolean whileThere(Player player) {
        return false;
    }
}
