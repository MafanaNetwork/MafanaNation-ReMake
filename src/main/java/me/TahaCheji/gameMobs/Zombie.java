package me.TahaCheji.gameMobs;

import me.TahaCheji.gameItems.Weapons.recipeItem;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobSpawn;
import me.TahaCheji.mobData.LootItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
        return false;
    }

    @Override
    public boolean onSpawn(Player player, Entity entity) {
        return false;
    }
}
