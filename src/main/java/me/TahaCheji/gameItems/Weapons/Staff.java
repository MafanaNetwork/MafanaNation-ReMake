package me.TahaCheji.gameItems.Weapons;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.MobUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.*;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameStaffData.GameStaffLevel;
import me.TahaCheji.itemData.GameWeaponData.GameWeaponLevel;
import me.TahaCheji.managers.DamageManager;
import me.TahaCheji.mobData.GameMob;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Staff extends GameStaff {
    public Staff() {
        super("Staff of Kathor", Material.BLAZE_ROD, ItemType.STAFF, ItemRarity.LAPIS);
        setLore("katchow");
        setMagic(200);
        setMobility(298);
        setArmor(112);
        setGameAbility(new GameItemAbility("Abolish", ItemAbilityType.RIGHT_CLICK, 5,5 , 100, "In a 5 block radius strikes all mobs with lightning"));
        setItemLevel(new GameStaffLevel(0, 20));
        getItemLevel().setItem(getGameStaff());
    }

    @Override
    public boolean rightClickAirAction(Player var1, ItemStack var2) {
        CoolDown coolDown = new CoolDown(this, Main.getInstance().getGamePlayer(var1));
        if(coolDown.ifCanUse(this)) {
            return false;
        }
        coolDown.addPlayerToCoolDownStaff();
        playExpandingParticleCircle(var1);

        return true;
    }
    double radius = 0.5; // Initial radius of the particle circle
    double maxRadius = 5.0; // Maximum radius of the particle circle
    private void playExpandingParticleCircle(Player player) {
        double expansionRate = (maxRadius - radius) / 5.0; // Rate at which the circle expands (duration: 5 seconds)

        World world = player.getWorld();
        Location center = player.getLocation().add(0, 1.5, 0);

        // Play particles in a circle around the player
        for (double currentRadius = radius; currentRadius <= maxRadius; currentRadius += expansionRate) {
            for (double angle = 0; angle < 360; angle += 10) {
                double radians = Math.toRadians(angle);
                double x = center.getX() + currentRadius * Math.cos(radians);
                double y = center.getY();
                double z = center.getZ() + currentRadius * Math.sin(radians);
                Location particleLocation = new Location(world, x, y, z);

                // Display particle effect
                world.spawnParticle(Particle.REDSTONE, particleLocation, 1, new Particle.DustOptions(Color.WHITE, 1.0f));

                // Apply damage to nearby entities within the expanding particle circle
                for (Entity entity : world.getNearbyEntities(particleLocation, 1.0, 1.0, 1.0)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        if(MobUtil.getMob(NBTUtils.getEntityString(entity, "MobName")) == null) {
                            continue;
                        }
                        GameMob gameMob = MobUtil.getMob(NBTUtils.getEntityString(entity, "MobName"));
                        if(gameMob == null) {
                            continue;
                        }
                        new DamageManager(Main.getInstance().getGamePlayer(player), gameMob).abilityDamage(getGameItemAbility());
                        world.strikeLightning(entity.getLocation());
                        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.MASTER, 1.0f, 1.0f);
                    }
                }
            }
        }
    }

}
