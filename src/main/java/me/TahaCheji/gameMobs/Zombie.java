package me.TahaCheji.gameMobs;

import me.TahaCheji.gameItems.testItems.Weapons.recipeItem;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.LootItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Zombie extends GameMob {

    public Zombie() {
        super(ChatColor.GREEN + "Zombie", 100, EntityType.ZOMBIE, 25, 50,
                5, 15, 0, null, null, new LootItem(new recipeItem().getItem(), 1, 3, 100));
        setXp(20);
        //setGameMobSpawn(new GameMobSpawn(new Location(Bukkit.getWorld("world"), 0, 64, 0), this));
    }

    @Override
    public boolean onAbilityHit(Player player, Entity entity) {
        return false;
    }

    @Override
    public boolean passiveAbility(Entity entity) {
        for(Player player : entity.getLocation().getNearbyPlayers(5)) {
            player.sendMessage("near zombie");
        }
        return true;
    }

    @Override
    public boolean onSpawn(Player player, Entity entity) {
        return false;
    }
}
