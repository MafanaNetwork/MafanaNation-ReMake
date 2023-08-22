package me.TahaCheji.itemData;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GameItemLevelUpEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final GameItemLevel gameItemLevel;
    private final ItemStack getRawItem;

    private final int amountLevelAdded;

    public GameItemLevelUpEvent(Player player, GameItemLevel gameItemLevel, ItemStack getRawItem, int i) {
        this.player = player;
        this.amountLevelAdded = i;
        this.gameItemLevel = gameItemLevel;
        this.getRawItem = getRawItem;
    }

    public GameItemLevel getGameItemLevel() {
        return gameItemLevel;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public @NotNull String getEventName() {
        return super.getEventName();
    }

    public ItemStack getGetRawItem() {
        return getRawItem;
    }

    public int getAmountLevelAdded() {
        return amountLevelAdded;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
