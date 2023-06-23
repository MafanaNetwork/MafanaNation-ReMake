package me.TahaCheji.gameUtil;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import me.TahaCheji.Main;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobBoss;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class MobUtil implements Listener {


    public static GameMob getMob(String name) {
        GameMob gameMob = null;
        for (GameMob createMob : Main.getInstance().getGameMobs()) {
            GameMob mob = createMob.getMob(name);
            if (mob == null) {
                continue;
            }
            gameMob = mob;
        }
        return gameMob;
    }

    public static GameMobBoss getBoss(String name) {
        GameMobBoss gameMob = null;
        for (GameMobBoss createMob : Main.getInstance().getGameBosses()) {
            GameMobBoss mob = createMob.getBossMob(name);
            if (mob == null) {
                continue;
            }
            gameMob = mob;
        }
        return gameMob;
    }

    @EventHandler
    public void onDeath(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        LivingEntity entity = (LivingEntity) e.getEntity();
        Player player = (Player) e.getDamager();
        if(NBTUtils.getEntityString(entity, "MobName") != null) {
            GameMob gameMob = getMob(NBTUtils.getEntityString(entity, "MobName"));
            if(gameMob != null) {
                ItemStack itemStack = player.getItemInHand();
                if(itemStack == null || itemStack.getType() == Material.AIR) {
                    return;
                }
                GameWeapons originalGameWeapons = ItemUtil.getGameWeapon(itemStack);
                if(originalGameWeapons == null) {
                    return;
                }
                //player.setItemInHand(originalGameWeapons.getItemLevel().addXP(gameMob.getXp(), originalGameWeapons));
            }
        }
    }

    public static BossBar createBossBar(Plugin plugin, LivingEntity livingEntity, String title, BarColor color, BarStyle style, BarFlag... flags) {
        BossBar bossBar = plugin.getServer().createBossBar(title, color, style, flags);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!livingEntity.isDead()) {
                    bossBar.setProgress(livingEntity.getHealth() / livingEntity.getMaxHealth());
                } else {
                    List<Player> players = bossBar.getPlayers();
                    for (Player player : players) {
                        bossBar.removePlayer(player);
                    }
                    bossBar.setVisible(false);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);
        return bossBar;
    }

}
