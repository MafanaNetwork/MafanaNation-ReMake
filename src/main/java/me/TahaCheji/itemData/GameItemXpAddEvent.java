package me.TahaCheji.itemData;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GameItemXpAddEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final GameItemLevel gameItemLevel;
    private final ItemStack getRawItem;

    private final int amountXpAdded;

    public GameItemXpAddEvent(Player player, GameItemLevel gameItemLevel, ItemStack getRawItem, int i) {
        this.player = player;
        this.amountXpAdded = i;
        this.gameItemLevel = gameItemLevel;
        this.getRawItem = getRawItem;
    }

    public Player getPlayer() {
        return player;
    }

    public int getAmountXpAdded() {
        return amountXpAdded;
    }

    @Override
    public @NotNull String getEventName() {
        return super.getEventName();
    }

    public GameItemLevel getGameItemLevel() {
        return gameItemLevel;
    }

    public ItemStack getGetRawItem() {
        return getRawItem;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
