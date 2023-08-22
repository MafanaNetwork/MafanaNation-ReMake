package me.TahaCheji.itemData.GameArmorData;

import me.TahaCheji.gameUtil.ItemUtil;
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
    private GameArmor helmet;
    private GameArmor chestplate;
    private GameArmor leggings;
    private GameArmor boots;
    private List<String> description;
    public static GameArmorSet gameArmorSet;

    public GameArmorSet(String name) {
        setGameArmorSet(this);
        this.name = name;
    }

    public boolean hasFullSet(Player player) {
        PlayerInventory inventory = player.getInventory();
        int i = 0;
        if(inventory.getHelmet() != null && helmet != null) {
            if (ItemUtil.getGameArmor(inventory.getHelmet()) != null) {
                if(isItemValid(ItemUtil.getGameArmor(inventory.getHelmet()).getName(), helmet.getName())) {
                    i += 1;
                }
            }
        } else if (inventory.getHelmet() == null && helmet == null) {
            i += 1;
        }
        if(inventory.getChestplate() != null && chestplate != null) {
            if (ItemUtil.getGameArmor(inventory.getChestplate()) != null) {
                if(isItemValid(ItemUtil.getGameArmor(inventory.getChestplate()).getName(), chestplate.getName())) {
                    i += 1;
                }
            }
        } else if (inventory.getChestplate() == null && chestplate == null) {
            i += 1;
        }
        if(inventory.getLeggings() != null && leggings != null) {
            if (ItemUtil.getGameArmor(inventory.getLeggings()) != null) {
                if(isItemValid(ItemUtil.getGameArmor(inventory.getLeggings()).getName(), leggings.getName())) {
                    i += 1;
                }
            }
        } else if (inventory.getLeggings() == null && leggings == null) {
            i += 1;
        }
        if(inventory.getBoots() != null && boots != null) {
            if (ItemUtil.getGameArmor(inventory.getBoots()) != null) {
                if(isItemValid(ItemUtil.getGameArmor(inventory.getBoots()).getName(), boots.getName())) {
                    i += 1;
                }
            }
        } else if (inventory.getBoots() == null && boots == null) {
            i += 1;
        }
        return i == 4;
    }

    public  void applyFullSetBonus(Player player) {
        if (hasFullSet(player)) {
            performCustomFullSetBonus(player);
        }
    }

    protected abstract void performCustomFullSetBonus(Player player);

    private boolean isItemValid(String i, String s) {
        return i != null &&
                Objects.equals(i, s);
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

    public void setHelmet(GameArmor helmet) {
        this.helmet = helmet;
    }

    public void setChestplate(GameArmor chestplate) {
        this.chestplate = chestplate;
    }

    public void setLeggings(GameArmor leggings) {
        this.leggings = leggings;
    }

    public void setBoots(GameArmor boots) {
        this.boots = boots;
    }

    public GameArmor getHelmet() {
        return helmet;
    }

    public GameArmor getChestplate() {
        return chestplate;
    }

    public GameArmor getLeggings() {
        return leggings;
    }

    public GameArmor getBoots() {
        return boots;
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
