package me.TahaCheji.managers;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItemAbility;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobBoss;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class DamageManager {

    private final GamePlayer gamePlayer;
    private GameMob gameMob;
    private GameItemAbility ability;
    DecimalFormat format = new DecimalFormat("#.##");

    public DamageManager(GamePlayer gamePlayer, GameMob gameMob) {
        this.gamePlayer = gamePlayer;
        this.gameMob = gameMob;
    }

    public DamageManager(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }


    public void abilityDamage (GameItemAbility gameItemAbility) {
        this.ability = gameItemAbility;
        if(gameMob == null) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) gameMob.getMob();
        if(livingEntity instanceof ArmorStand) {
            return;
        }
        int damage = gamePlayer.getGamePlayerStats().getPlayerDamageMagic(gameMob.getDefense(), getAbility().getAbilityDamage());
        gameMob.setHealth(gameMob.getHealth() - damage);

        double health = gameMob.getHealth();
        Location loc = gameMob.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
        gamePlayer.getPlayer().getWorld().spawn(loc, ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(ChatColor.DARK_GRAY +"[" + ChatColor.BLUE + "[M]" + damage + ChatColor.DARK_GRAY +"]");
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                armorStand.remove();
            },20);
        });
        ((LivingEntity) gameMob.getMob()).damage(health);
        livingEntity.setCustomName(ChatColor.translateAlternateColorCodes('&',
                gameMob.getName() + " " + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "♥" + format.format((int) health) +
                        ChatColor.DARK_GRAY + "/" + ChatColor.RED + gameMob.getMaxHealth() + "♥" + ChatColor.DARK_GRAY + "]"));
        setBossDamage(livingEntity);
        if(damage >= health) {
            gameMob.onMobDeath(gamePlayer.getPlayer());
        }
    }


    public void meleeDamage () {
        if(gameMob == null) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) gameMob.getMob();
        if(livingEntity instanceof ArmorStand) {
            return;
        }
        int damage = gamePlayer.getGamePlayerStats().getPlayerDamageStrength(gameMob.getDefense());
        gameMob.setHealth(gameMob.getHealth() - damage);

        double health = gameMob.getHealth();
        livingEntity.setCustomName(ChatColor.translateAlternateColorCodes('&',
                gameMob.getName() + " " + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "♥" + format.format((int) health) +
                        ChatColor.DARK_GRAY + "/" + ChatColor.RED + gameMob.getMaxHealth() + "♥" + ChatColor.DARK_GRAY + "]"));

        Location loc = livingEntity.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
        gamePlayer.getPlayer().getWorld().spawn(loc, ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(ChatColor.DARK_GRAY +"[" + ChatColor.DARK_RED + "✧" + damage + ChatColor.DARK_GRAY +"]");
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), armorStand::remove, 20); // Time in ticks (20 ticks = 1 second)
        });
        setBossDamage(livingEntity);
        if(damage >= health) {
            gameMob.onMobDeath(gamePlayer.getPlayer());
        }
    }

    public void playerTakeDamage(int damageTook) {
        double remainingHealth = gamePlayer.getGamePlayerStats().playerGetDamage(damageTook);
        if (remainingHealth <= 0) {
            gamePlayer.getGamePlayerStats().setHealth(gamePlayer.getGamePlayerStats().getBaseHealth());
            gamePlayer.getPlayer().sendMessage(ChatColor.RED + "[MafanaNation Manager]: You Died!");
            gamePlayer.getPlayer().playSound(gamePlayer.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, 10, 10);
            gamePlayer.getPlayer().teleport(gamePlayer.getPlayer().getLastDeathLocation());

            if (gameMob != null) {
                Bukkit.broadcastMessage(gamePlayer.getPlayer().getDisplayName() + " just died from " + NBTUtils.getEntityString(gameMob.getMob(), "MobName"));
            }

            GameMobBoss masterBoss = Main.getInstance().getPlayerBossFight().get(gamePlayer.getPlayer());
            if (masterBoss != null) {
                masterBoss.killMob();
                gamePlayer.getPlayer().sendMessage(ChatColor.RED + "[MafanaNation Manager]: You Failed");
                Main.getInstance().getPlayerBossFight().remove(gamePlayer.getPlayer(), masterBoss);

                for (GameMob minions : masterBoss.getMinions()) {
                    minions.killMob();
                }
            }
        } else {
            gamePlayer.getGamePlayerStats().setHealth((int) remainingHealth);

            // Delayed health display update
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                gamePlayer.getPlayer().setHealth(gamePlayer.getGamePlayerStats().getHealth());
            }, 0); // Adjust the delay (in ticks) as needed
        }
    }





    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public GameMob getTarget() {
        return gameMob;
    }
    public GameItemAbility getAbility() {
        return ability;
    }
    public void setAbility(GameItemAbility ability) {
        this.ability = ability;
    }
    private double getRandomOffset() {
        double random = Math.random();
        if (Math.random() > 0.5) random *= -1;
        return random;
    }
    public void setBossDamage(LivingEntity livingEntity) {
        for (GameMobBoss mob : Main.getInstance().getActiveBoss()) {
            if(mob != gameMob) {
                continue;
            }
            if (livingEntity instanceof Player) {
                continue;
            }
            if (livingEntity.getCustomName() == null) {
                continue;
            }
            NBTCompound nbt = new NBTEntity(livingEntity).getPersistentDataContainer();
            if (!(nbt.hasKey("MobBossName"))) {
                continue;
            }
            if (!(NBTUtils.getEntityString(livingEntity, "MobBossName").equalsIgnoreCase(NBTUtils.getEntityString(livingEntity, "MobBossName")))) {
                continue;
            }
            if(!mob.stageOne) {
                mob.stageOne(gamePlayer.getPlayer(), livingEntity, (int) livingEntity.getHealth());
            }
            if(!mob.stageTwo && mob.stageOne) {
                mob.stageTwo(gamePlayer.getPlayer(), livingEntity, (int) livingEntity.getHealth());
            }
            if(!mob.stageThree && mob.stageTwo && mob.stageOne) {
                mob.stageThree(gamePlayer.getPlayer(), livingEntity, (int) livingEntity.getHealth());
            }
        }
    }
    public static boolean canDamage(Player player, Location loc, Entity target) {
        if (target.equals(player) || !(target instanceof LivingEntity) || target instanceof ArmorStand || target.isDead())
            return false;

        if (target.hasMetadata("NPC"))
            return false;

        if (target instanceof Player)
            return false;

        return loc == null;
    }


}
