package me.TahaCheji.itemData.GameArmorData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.ItemRarity;
import me.TahaCheji.itemData.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class GameArmor extends GameItem {

    public int strength;
    public int health;
    public int armor;
    public int magic;
    public int mobility;
    public List<String> lore;
    private String armorUUID;
    private GameArmorAbility gameAbility;
    private GameArmorSet armorSet;

    public GameArmor(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
        this.armorUUID = getName() + "ArmorUUID";
    }

    public ItemStack getGameArmor() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        itemStack.setItemMeta(meta);
        meta.setDisplayName(getItemRarity().getColor() + getName());
        List<String> lore = new ArrayList<>();
        lore.add(getItemRarity().getColor() + getItemRarity().getLore() + ChatColor.DARK_GRAY + "| " + getItemType().getLore() + " | " + getMaterial().name());
        lore.add(ChatColor.DARK_GRAY + ItemUtil.itemLoreDash(lore.get(0)));
        lore.add("");
        if(strength != 0) {
            lore.add(ChatColor.DARK_RED + "⚔Strength: " + ChatColor.DARK_GRAY + "+" + getStrength());
        }
        if(health != 0) {
            lore.add(ChatColor.RED + "❤Health: " + ChatColor.DARK_GRAY + "+" + getHealth());
        }
        if(armor != 0) {
            lore.add(ChatColor.YELLOW + "⛨Armor: " + ChatColor.DARK_GRAY + "+" + getArmor());
        }
        if(magic != 0) {
            lore.add(ChatColor.BLUE + "[M]Magic: " + ChatColor.DARK_GRAY + "+" + getMagic());
        }
        if(mobility != 0) {
            lore.add(ChatColor.DARK_GREEN + "〰Mobility: " + ChatColor.DARK_GRAY + "+" + getMobility());
        }
        lore.add("");
        if(gameAbility != null) {
            lore.addAll(getGameAbility().toLore());
        }
        lore.add("");
        if(getArmorSet() != null) {
            lore.addAll(getArmorSet().toLore());
        }
        lore.add("");
        lore.addAll(getLore());
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        itemStack = NBTUtils.setInt(itemStack, "ItemArmorStrength", strength);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorHealth", health);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorArmor", armor);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorMagic", magic);
        itemStack = NBTUtils.setInt(itemStack, "ItemArmorMobility", mobility);
        itemStack = NBTUtils.setString(itemStack, "GameArmorUUID", getArmorUUID());
        return itemStack;
    }

    public GameArmor createNewInstance() throws InstantiationException, IllegalAccessException {
        GameArmor game = this.getClass().newInstance();
        game.setArmorUUID(UUID.randomUUID().toString());
        Main.getInstance().getGameArmors().add(game);
        return game;
    }

    public void setArmorSet(GameArmorSet armorSet) {this.armorSet = armorSet;}
    public GameArmorSet getArmorSet() {return armorSet;}
    public List<String> getLore() {
        return lore;
    }
    public void setGameAbility(GameArmorAbility gameAbility) {
        this.gameAbility = gameAbility;
    }
    public GameArmorAbility getGameAbility() {
        return gameAbility;
    }
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    public void setArmorUUID(String armorUUID) {
        this.armorUUID = armorUUID;
    }
    public String getArmorUUID() {
        return armorUUID;
    }
    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public void setMagic(int magic) {
        this.magic = magic;
    }
    public void setMobility(int mobility) {
        this.mobility = mobility;
    }
    public int getStrength() {
        return strength;
    }
    public int getHealth() {
        return health;
    }
    public int getArmor() {
        return armor;
    }
    public int getMagic() {
        return magic;
    }
    public int getMobility() {
        return mobility;
    }
}
