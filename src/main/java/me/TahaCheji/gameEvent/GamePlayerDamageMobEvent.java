package me.TahaCheji.gameEvent;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.MobUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.managers.DamageManager;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobBoss;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GamePlayerDamageMobEvent implements Listener {

    @EventHandler
    public void playerGetHitEvent(EntityDamageByEntityEvent event) {
        Entity damagedEntity = event.getEntity();
        if (damagedEntity instanceof Player) {
            Player player = (Player) damagedEntity;
            new DamageManager(Main.getInstance().getGamePlayer(player)).playerTakeDamage((int) event.getDamage());
        }
    }

    @EventHandler
    public void playerHitEvent(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player player = (Player) damager;
            Entity damagedEntity = event.getEntity();
            GameMob gameMob = MobUtil.getMob(NBTUtils.getEntityString(damagedEntity, "MobName"));
            if(gameMob != null) {
                new DamageManager(Main.getInstance().getGamePlayer(player), gameMob).meleeDamage();
            } else {
                GameMobBoss boss = MobUtil.getBoss(NBTUtils.getEntityString(event.getEntity(), "MobBossName"));
                new DamageManager(Main.getInstance().getGamePlayer(player), boss).meleeDamage();
            }
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
    }

}
