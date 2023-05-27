package me.TahaCheji.mobData;


import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmorStandUtil implements Listener {


    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        Entity entity = e.getRightClicked();
        if (entity instanceof ArmorStand) {
            e.setCancelled(true);
        }

    }


}
