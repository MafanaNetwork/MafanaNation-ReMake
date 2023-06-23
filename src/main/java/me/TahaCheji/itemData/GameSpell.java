package me.TahaCheji.itemData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class GameSpell extends GameItem{

    public boolean onTimeUse;
    public GameItemAbility gameItemAbility;
    private String spellUUID;
    public List<String> lore;

    public GameSpell(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
        this.spellUUID = UUID.randomUUID().toString();
    }

    public ItemStack getGameSpell() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        itemStack.setItemMeta(meta);
        meta.setDisplayName(getItemRarity().getColor() + getName());
        List<String> lore = new ArrayList<>();
        lore.add(getItemRarity().getColor() + getItemRarity().getLore() + ChatColor.DARK_GRAY + "| " + getItemType().getLore() + " | " + getMaterial().name());
        lore.add(ChatColor.DARK_GRAY + ItemUtil.itemLoreDash(lore.get(0)));
        lore.add("");
        if(getGameItemAbility() != null) {
            lore.addAll(getGameItemAbility().toLore());
        }
        lore.add("");
        if(getLore() != null) {
            lore.addAll(getLore());
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        itemStack = NBTUtils.setString(itemStack, "GameSpellUUID", getSpellUUID());
        return itemStack;
    }

    public GameSpell createNewInstance() throws InstantiationException, IllegalAccessException {
        GameSpell game = this.getClass().newInstance();
        game.setSpellUUID(UUID.randomUUID().toString());
        Main.getInstance().getGameSpells().add(game);
        return game;
    }

    public void setSpellUUID(String spellUUID) {
        this.spellUUID = spellUUID;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getSpellUUID() {
        return spellUUID;
    }

    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }

    public void setGameAbility(GameItemAbility gameItemAbility) {
        this.gameItemAbility = gameItemAbility;
    }

    public boolean isOnTimeUse() {
        return onTimeUse;
    }

    public GameItemAbility getGameItemAbility() {
        return gameItemAbility;
    }

    public List<String> getLore() {
        return lore;
    }

    public static void destroy(ItemStack item, int quantity) {
        if (item.getAmount() <= quantity) {
            item.setAmount(0);
        } else {
            item.setAmount(item.getAmount() - quantity);
        }

    }

    public void setOnTimeUse(boolean onTimeUse) {
        this.onTimeUse = onTimeUse;
    }


}
