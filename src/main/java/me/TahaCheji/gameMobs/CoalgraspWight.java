package me.TahaCheji.gameMobs;

import me.TahaCheji.Main;
import me.TahaCheji.gameItems.Orebane;
import me.TahaCheji.gameItems.testItems.Weapons.recipeItem;
import me.TahaCheji.gameSection.DeepstoneSection;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobSpawn;
import me.TahaCheji.mobData.LootItem;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class CoalgraspWight extends GameMob {

    public CoalgraspWight() {
        super(ChatColor.DARK_PURPLE + "Coalgrasp Wight", 100, EntityType.ZOMBIE, 75, 50,
                15, 5, 0, null, null, new LootItem(new Orebane().getItem(), 1, 1, 25));
        setXp(50);
        //GameMobSpawn gameMobSpawn = new GameMobSpawn(10, 7, new DeepstoneSection().getX(), new DeepstoneSection().getY(), this);
        //gameMobSpawn.spawnMasterMobs();
    }

    @Override
    public boolean onAbilityHit(Player player, Entity entity) {
        player.getWorld().spawnParticle(Particle.SMOKE_LARGE, player.getLocation(), 20, 0.5, 1, 0.5, 0);

        int duration = 10;
        int amplifier = 0;
        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, duration, amplifier));

        return true;
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
