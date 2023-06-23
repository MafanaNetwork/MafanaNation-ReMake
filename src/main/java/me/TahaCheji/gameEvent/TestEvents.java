package me.TahaCheji.gameEvent;

import me.TahaCheji.gameUtil.MobUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class TestEvents implements Listener {

    @EventHandler
    public void rightClick(PlayerInteractAtEntityEvent e) {
        Entity entity = e.getRightClicked();
        if(MobUtil.getMob(NBTUtils.getEntityString(entity, "MobName")) != null) {
            System.out.println(MobUtil.getMob(NBTUtils.getEntityString(entity, "MobName")));
            System.out.println(1);
        }
        if(e.getPlayer().isSneaking()) {
            entity.remove();
        }
    }
}
