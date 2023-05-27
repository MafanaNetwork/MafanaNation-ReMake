package me.TahaCheji.mobData;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Arrays;
import java.util.Objects;

public class DropLoot implements Listener {


    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Player player = e.getEntity().getKiller();
        e.getDrops().clear();
        e.setDroppedExp(0);
        if (player == null) {
            return;
        }
        for (GameMob mob : Main.getInstance().getGameMobs()) {
            if (entity instanceof Player) {
                continue;
            }
            if (entity.getCustomName() == null) {
                continue;
            }
            NBTCompound nbt = new NBTEntity(entity).getPersistentDataContainer();
            if (!(nbt.hasKey("MobName"))) {
                continue;
            }
            if (!(NBTUtils.getEntityString(entity, "MobName").equalsIgnoreCase(mob.getName()))) {
                continue;
            }
            if (mob.getLootTable() == null) {
                continue;
            }
            mob.tryDropLoot(entity.getLocation(), player);
            Main.getInstance().getActiveMobs().remove(mob);
        }

        for (GameMobBoss mob : Main.getInstance().getGameBosses()) {
            if (entity instanceof Player) {
                continue;
            }
            if (entity.getCustomName() == null) {
                continue;
            }
            NBTCompound nbt = new NBTEntity(entity).getPersistentDataContainer();
            if (!(nbt.hasKey("MobBossName"))) {
                continue;
            }
            if (!(NBTUtils.getEntityString(entity, "MobBossName").equalsIgnoreCase(mob.getName()))) {
                continue;
            }
            if (mob.getLootTable() == null) {
                continue;
            }
            for (GameMob minions : mob.getMinions()) {
                minions.killMob();
            }
            mob.onDeath(player, entity);
            mob.tryDropLoot(entity.getLocation(), player);
            Main.getInstance().getActiveBoss().remove(mob);
        }
    }
}
