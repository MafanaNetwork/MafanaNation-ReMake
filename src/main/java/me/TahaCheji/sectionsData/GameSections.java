package me.TahaCheji.sectionsData;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class GameSections {

    private final String name;
    private final Location x;
    private final Location y;

    public GameSections(String name, Location x, Location y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public abstract boolean whileThere(Player player);

    public String getName() {
        return name;
    }

    public Location getX() {
        return x;
    }

    public Location getY() {
        return y;
    }


}
