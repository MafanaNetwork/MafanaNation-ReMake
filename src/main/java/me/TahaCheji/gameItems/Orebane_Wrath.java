package me.TahaCheji.gameItems;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.MobUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.*;
import me.TahaCheji.itemData.GameItemLevel;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import me.TahaCheji.managers.DamageManager;
import me.TahaCheji.mobData.GameMob;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Orebane_Wrath extends GameWeapons {

    public Orebane_Wrath(){
        super(ChatColor.DARK_GRAY + "OrebaneWrath", Material.IRON_HOE, ItemType.SWORD, ItemRarity.IRON);
        setLore("You still dont feel.");
        setStrength(200);
        setMagic(55);
        setMobility(25);
        setHealth(100);
        setArmor(75);
        setGameAbility(new GameItemAbility("RSlash", ItemAbilityType.LEFT_CLICK, 5,1 , 25, "Scythe but sounds cooler"));
        setGameItemLevel(new GameItemLevel(this, 0, 50));
    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack itemStack) {
        GameItemCoolDown gameItemCoolDown = new GameItemCoolDown(this, Main.getInstance().getGamePlayer(player));
        if (gameItemCoolDown.ifCanUse(this)) {
            return false;
        }
        gameItemCoolDown.addPlayerToCoolDownWeapon();

        // Spawn curved diagonal slash particle effect (resembling "C")
        Location playerLocation = player.getEyeLocation();
        World world = playerLocation.getWorld();
        int numParticles = 50; // Total number of particles to spawn

        final double[] radius = {1.0}; // Initial distance of particles from the player
        double angleIncrement = Math.PI * 1.25 / numParticles; // Angle increment per particle
        List<Location> loc = new ArrayList<>();

        BukkitRunnable runnable = new BukkitRunnable() {
            double elapsedTime = 0.0;
            double distanceIncrement = 5.0; // Distance increment per second
            @Override
            public void run() {
                if (elapsedTime >= 5.0) {
                    this.cancel();
                    return;
                }

                Vector particleDirection = player.getLocation().getDirection().normalize();
                for (int i = 0; i < numParticles; i++) {
                    double angle = angleIncrement * i;

                    double offsetX = Math.cos(angle) * radius[0];
                    double offsetY = Math.sin(angle) * radius[0];
                    double offsetZ = angle * radius[0];

                    Vector offsetVector = particleDirection.clone().multiply(offsetX).add(playerLocation.getDirection().normalize().multiply(offsetY)).add(playerLocation.getDirection().multiply(offsetZ));
                    Location particleLocation = playerLocation.clone().add(offsetVector);


                    world.spawnParticle(Particle.REDSTONE, particleLocation, 1, 0, 0, 0, 1, new Particle.DustOptions(Color.WHITE, 1));
                    loc.add(particleLocation.clone());
                    // Check for nearby entities at the stored location
                }
                radius[0] += distanceIncrement;
                elapsedTime += 1.0; // Increment elapsed time by 1 second
            }
        };

        // Run the task repeatedly with a 1-second delay between each run
        runnable.runTaskTimer(Main.getInstance(), 0L, 0L);
        for(Location storedLocation : loc) {
            List<Entity> nearbyEntities = (List<Entity>) world.getNearbyEntities(storedLocation, 0.5, 0.5, 0.5);
            for (Entity entity : nearbyEntities) {
                if (entity instanceof LivingEntity && entity != player) {
                    if (entity instanceof ArmorStand) {
                        continue;
                    }
                    if (MobUtil.getMob(NBTUtils.getEntityString(entity, "MobName")) == null) {
                        continue;
                    }
                    GameMob gameMob = MobUtil.getMob(NBTUtils.getEntityString(entity, "MobName"));
                    if (gameMob == null) {
                        continue;
                    }
                    new DamageManager(Main.getInstance().getGamePlayer(player), gameMob).abilityDamage(getGameAbility());
                }
            }
        }

        return true;
    }




    @Override
    public boolean hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4) {
        var2.setCancelled(true);
        return true;
    }

    @Override
    public boolean onItemJumpAction(Player var1, ItemStack var2) {
        return true;
    }

    @Override
    public boolean onItemHoldAction(Player var1, ItemStack var2) {
        return true;
    }

    @Override
    public boolean onItemShiftAction(Player var1, ItemStack var2) {
        return true;
    }


}
