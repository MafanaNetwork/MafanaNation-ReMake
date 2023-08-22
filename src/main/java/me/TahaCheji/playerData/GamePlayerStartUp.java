package me.TahaCheji.playerData;

import me.TahaCheji.Inv;
import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.sectionsData.GameSections;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GamePlayerStartUp {

    private GamePlayer gamePlayer;

    public GamePlayerStartUp(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
        Player player = gamePlayer.getPlayer();
        if (Inv.getInstance().getInventoryDataHandler().isSyncComplete(player) && !gamePlayer.hasSynced) {
            try {
                Main.getInstance().getMainScoreboard().updateScoreboard();
                ItemUtil.registerGameWeapons(player);
                ItemUtil.registerGameBows(player);
                ItemUtil.registerArmorGameItems(player);
                ItemUtil.registerGameSpells(player);
                ItemUtil.registerGameStaffs(player);
                gamePlayer.hasSynced = true;
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        for (GameSections gameSection : Main.getInstance().getGameSections()) {
            Location sectionX = gameSection.getX();
            Location sectionY = gameSection.getY();
            Location playerLocation = player.getLocation();

            int playerX = (int) playerLocation.getX();
            int playerY = (int) playerLocation.getY();
            int playerZ = (int) playerLocation.getZ();

            int sectionXMin = (int) Math.min(sectionX.getX(), sectionY.getX());
            int sectionXMax = (int) Math.max(sectionX.getX(), sectionY.getX());
            int sectionYMin = (int) Math.min(sectionX.getY(), sectionY.getY());
            int sectionYMax = (int) Math.max(sectionX.getY(), sectionY.getY());
            int sectionZMin = (int) Math.min(sectionX.getZ(), sectionY.getZ());
            int sectionZMax = (int) Math.max(sectionX.getZ(), sectionY.getZ());

            if (playerX >= sectionXMin && playerX <= sectionXMax
                    && playerY >= sectionYMin && playerY <= sectionYMax
                    && playerZ >= sectionZMin && playerZ <= sectionZMax) {
                gamePlayer.setGameSections(gameSection);
                gameSection.whileThere(player);
            }
        }


        player.setHealthScaled(true);
        player.setHealthScale(40);
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        ItemStack[] armor = player.getInventory().getArmorContents();
        gamePlayer.gamePlayerStats.updateStats(heldItem, armor);
        gamePlayer.getPlayer().setSaturation(20);
        gamePlayer.gamePlayerStats.regenerateHealth();
        gamePlayer.gamePlayerStats.regenerateMana();

        double maxHealth = gamePlayer.gamePlayerStats.getMaxHealth();
        double health = gamePlayer.gamePlayerStats.getHealth();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        player.setHealth(health);

        gamePlayer.getGamePlayerStats().setMobilityStats();
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
