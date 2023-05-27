package me.TahaCheji.gameMobs;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.AbilityUtil;
import me.TahaCheji.managers.DamageManager;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobBoss;
import me.TahaCheji.mobData.GameMobText;
import me.TahaCheji.mobData.LootItem;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;
import java.awt.Color;
import xyz.xenondevs.particle.data.color.ParticleColor;

import java.util.Random;

public class Gollador extends GameMobBoss {

    public Gollador() {
        super(ChatColor.RED + "Gollador", 100, EntityType.WITHER_SKELETON, 550, 50, 25, 5, 0, null, null);
        //setLootTable(new LootItem(new GolladorStand().getItem(), 100));
    }


    public boolean onSpawn(Player player, Entity entity) {
        return true;
    }

    @Override
    public boolean getHit(Player player, Entity entity) {
        return false;
    }

    @Override
    public boolean onAbilityHit(Player player, Entity entity) {
        player.removePotionEffect(PotionEffectType.WITHER);
        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (int) (2 * 20), (int) 2));
        return true;
    }

    @Override
    public boolean passiveAbility(Entity entity) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            for (Entity newEntity : entity.getLocation().getNearbyEntities(5, 5, 5)) {
                if (!(newEntity instanceof Player)) {
                    continue;
                }
                Player player = (Player) newEntity;
                GamePlayer gamePlayer = Main.getInstance().getGamePlayer(player);
                if (player.isDead() || entity.isDead()) {
                    continue;
                }
                Location loc = player.getLocation();

                double radius = 2.7;

                loc.add(0, -1, 0);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_HURT, 1, 0);
                for (double j = 0; j < Math.PI * 2; j += Math.PI / 36) {
                    Location loc1 = loc.clone().add(Math.cos(j) * radius, 1, Math.sin(j) * radius);
                    double y_max = .5 + new Random().nextDouble();
                    for (double y = 0; y < y_max; y += .1) {
                        Location loc2 = loc1.clone();
                        loc2.add(0, y, 0);
                        ParticleEffect.REDSTONE.display(loc2, Color.BLACK);
                    }
                }
                new DamageManager(gamePlayer).playerTakeDamage(50);
                player.removePotionEffect(PotionEffectType.WITHER);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (int) (2 * 20), (int) 2));
            }
        }, 250);
        new BukkitRunnable() {
            Vector dir = new AbilityUtil().getMobTargetDirection(entity, null).multiply(.3);
            Location loc = entity.getLocation().add(0, 2, 0);
            double r = 0.4;
            int ti = 0;

            public void run() {
                ti++;
                if (ti > 50)
                    cancel();

                for (double j = 0; j < 4; j++) {
                    loc.add(dir);
                    for (double i = 0; i < Math.PI * 2; i += Math.PI / 6) {
                        Vector vec = AbilityUtil.rotateFunc(new Vector(r * Math.cos(i), r * Math.sin(i), 0), loc);
                        loc.add(vec);
                        ParticleEffect.SPELL_WITCH.display(loc, 0, 0, 0, 0, 1, null, Bukkit.getOnlinePlayers());
                        loc.add(vec.multiply(-1));
                    }
                    for (Entity entity1 : loc.getNearbyEntities(2, 2, 2)) {
                        if (!(entity1 instanceof Player)) {
                            continue;
                        }
                        Player player1 = ((Player) entity1).getPlayer();
                        if (player1 == null) {
                            continue;
                        }
                        loc.getWorld().playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 2, .7f);
                        player1.damage(50);
                        player1.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (2 * 20), 0));
                        cancel();
                        return;
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
        return true;
    }

    @Override
    public boolean stageOne(Player player, Entity entity, int health) {
        if (!(health <= 350)) {
            return false;
        } else {
            new GameMobText(player, (LivingEntity) entity, ChatColor.RED + "Stage 1 MOTHER FAUAKKER").spawnText(10);
            setStageOne(true);
            getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + 5);
        }
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2, 2);
        //player.getWorld().strikeLightning(player.getLocation());
        new BukkitRunnable() {
            Vector dir = new AbilityUtil().getMobTargetDirection(entity, null).multiply(.3);
            Location loc = entity.getLocation().add(0, 2, 0);
            double r = 0.4;
            int ti = 0;

            public void run() {
                ti++;
                if (ti > 50)
                    cancel();

                for (double j = 0; j < 4; j++) {
                    loc.add(dir);
                    for (double i = 0; i < Math.PI * 2; i += Math.PI / 6) {
                        Vector vec = AbilityUtil.rotateFunc(new Vector(r * Math.cos(i), r * Math.sin(i), 0), loc);
                        loc.add(vec);
                        ParticleEffect.SPELL_WITCH.display(loc, 0, 0, 0, 0, 1, null, Bukkit.getOnlinePlayers());
                        loc.add(vec.multiply(-1));
                    }
                    for (Entity entity1 : loc.getNearbyEntities(2, 2, 2)) {
                        if (!(entity1 instanceof Player)) {
                            continue;
                        }
                        Player player1 = ((Player) entity1).getPlayer();
                        if (player1 == null) {
                            continue;
                        }
                        loc.getWorld().playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 2, .7f);
                        player1.damage(50);
                        player1.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (2 * 20), 0));
                        cancel();
                        return;
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
        return true;
    }

    @Override
    public boolean stageTwo(Player player, Entity entity, int health) {
        if (!(health <= 150)) {
            return false;
        } else {
            new GameMobText(player, (LivingEntity) entity, ChatColor.RED + "FINALE STAGEEE").spawnText(10);
            setStageTwo(true);
            ((LivingEntity) entity).setHealth(((LivingEntity) entity).getHealth() + 75);
            getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + 5);
        }
        GameMob minion1 = new GolladorMinion();
        GameMob minion2 = new GolladorMinion();
        GameMob minion3 = new GolladorMinion();
        addMinion(minion1);
        addMinion(minion2);
        addMinion(minion3);
        minion1.spawnMob(player.getLocation().add(2, 0, 0), player);
        minion2.spawnMob(player.getLocation().add(-2, 0, 0), player);
        minion3.spawnMob(player.getLocation().add(0, 0, 2), player);
        return true;
    }

    @Override
    public boolean stageThree(Player player, Entity entity, int health) {
        if(!(health <= 25)) {
            setStageThree(false);
        } else {
            setStageThree(true);
            getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + 20);
            new GameMobText(player, (LivingEntity) entity, ChatColor.GREEN + "Healed + Damage").spawnText(10);
            ((LivingEntity) entity).setHealth(((LivingEntity) entity).getHealth() + 100);
            player.getWorld().strikeLightning(player.getLocation());

        }
        return true;
    }

    @Override
    public boolean onDeath(Player player, Entity entity) {
        return false;
    }
}
