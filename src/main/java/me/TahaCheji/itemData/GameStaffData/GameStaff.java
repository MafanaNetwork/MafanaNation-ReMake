package me.TahaCheji.itemData.GameStaffData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItem;
import me.TahaCheji.itemData.GameItemAbility;
import me.TahaCheji.itemData.GameWeaponData.GameWeaponLevel;
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

public class GameStaff extends GameItem {


    public int armor;
    public int magic;
    public int mobility;
    public List<String> lore;
    public GameItemAbility gameItemAbility;
    public GameStaffLevel itemLevel = null;
    private String staffUUID;

    public GameStaff(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
        this.staffUUID = UUID.randomUUID().toString();
    }


    public ItemStack getGameStaff() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "[" + getItemLevel().getLevel() + "] " + getItemRarity().getColor() + getName());
        List<String> lore = new ArrayList<>();
        lore.add(getItemRarity().getColor() + getItemRarity().getLore() + ChatColor.DARK_GRAY + "| " + getItemType().getLore() + " | " + getMaterial().name());
        lore.addAll(getItemLevel().getLore(itemStack));
        lore.add(ChatColor.DARK_GRAY + ItemUtil.itemLoreDash(lore.get(0)));
        lore.add("");
        if (armor != 0) {
            lore.add(ChatColor.YELLOW + "⛨Armor: " + ChatColor.DARK_GRAY + "+" + getArmor());
        }
        if (magic != 0) {
            lore.add(ChatColor.BLUE + "[M]Magic: " + ChatColor.DARK_GRAY + "+" + getMagic());
        }
        if (mobility != 0) {
            lore.add(ChatColor.DARK_GREEN + "〰Mobility: " + ChatColor.DARK_GRAY + "+" + getMobility());
        }
        lore.add("");
        if (gameItemAbility != null) {
            lore.addAll(getGameItemAbility().toLore());
        }
        lore.add("");
        if (getLore() != null) {
            lore.addAll(getLore());
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        this.staffUUID = UUID.randomUUID().toString();
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponArmor", armor);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMagic", magic);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMobility", mobility);
        itemStack = NBTUtils.setString(itemStack, "GameStaffUUID", getStaffUUID());
        itemStack = itemLevel.getLevelItemStack(itemStack);
        return itemStack.clone();
    }

    public GameStaff createNewInstance() throws InstantiationException, IllegalAccessException {
        GameStaff game = this.getClass().newInstance();
        Main.getInstance().getGameStaffs().add(game);
        game.setStaffUUID(getStaffUUID());
        return game;
    }

    public void setItemLevel(GameStaffLevel itemLevel) {
        this.itemLevel = itemLevel;
    }

    public GameStaffLevel getItemLevel() {
        return itemLevel;
    }


    public List<String> getLore() {
        return lore;
    }

    public GameItemAbility getGameItemAbility() {
        return gameItemAbility;
    }

    public void setStaffUUID(String staffUUID) {
        this.staffUUID = staffUUID;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }

    public void setGameAbility(GameItemAbility gameItemAbility) {
        this.gameItemAbility = gameItemAbility;
    }

    public String getStaffUUID() {
        return staffUUID;
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
