package me.TahaCheji.gameSection;

import me.TahaCheji.sectionsData.GameSections;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MainLobbyTown extends GameSections {

    public MainLobbyTown() {
        super("Town-unnamed",
                new Location(Bukkit.getWorld("world"),2415,45,3019),
                new Location(Bukkit.getWorld("world"),2281,19,3148));
    }

    @Override
    public boolean whileThere(Player player) {
        return false;
    }
}
