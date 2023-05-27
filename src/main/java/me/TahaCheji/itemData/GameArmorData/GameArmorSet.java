package me.TahaCheji.itemData.GameArmorData;

import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class GameArmorSet {

    private final String name;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private List<String> description;
    public static GameArmorSet gameArmorSet;

    public GameArmorSet(String name) {
        setGameArmorSet(this);
        this.name = name;
    }

    public boolean hasFullSet(Player player) {
        PlayerInventory inventory = player.getInventory();
        return isItemValid(inventory.getHelmet(), helmet) &&
                isItemValid(inventory.getChestplate(), chestplate) &&
                isItemValid(inventory.getLeggings(), leggings) &&
                isItemValid(inventory.getBoots(), boots);
    }

    public  void applyFullSetBonus(Player player) {
        if (hasFullSet(player)) {
            performCustomFullSetBonus(player);
        }
    }

    protected abstract void performCustomFullSetBonus(Player player);

    private boolean isItemValid(ItemStack itemStack, ItemStack expectedItem) {
        return itemStack != null &&
                itemStack.getType() != Material.AIR &&
                Objects.equals(NBTUtils.getString(itemStack, "GameArmorUUID"), NBTUtils.getString(expectedItem, "GameArmorUUID"));
    }

    public void setDescription(String... description) {
        this.description = Arrays.asList(description);
    }

    public List<String> toLore() {
        List<String> lore = new ArrayList();
        lore.add(ChatColor.GOLD + "Armor Full Set: " + this.name + " ");
        for(String string : description) {
            lore.add(ChatColor.DARK_GRAY + "'" + string + "'");
        }
        return lore;
    }


    public String getName() {
        return name;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public static GameArmorSet getInstance() {
        return gameArmorSet;
    }

    public static void setGameArmorSet(GameArmorSet gameArmorSet) {
        GameArmorSet.gameArmorSet = gameArmorSet;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getDescription() {
        return description;
    }
}
