package me.TahaCheji.itemData.GameStaffData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.*;
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

    public int originalArmor;
    public int originalMagic;
    public int originalMobility;

    public List<String> lore;
    public GameItemAbility gameItemAbility;
    public GameItemLevel gameItemLevel;
    private String staffUUID;

    public GameStaff(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
        this.staffUUID = UUID.randomUUID().toString();
    }


    public ItemStack getGameStaff() {
        ItemStack itemStack = getItem();
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(getItemRarity().getColor() + getItemRarity().getLore() + ChatColor.DARK_GRAY + "| " + getItemType().getLore() + " | " + getMaterial().name());
        if(getGameItemLevel() != null) {
            lore.addAll(getGameItemLevel().getLore(itemStack));
        }
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

        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponArmor", armor);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMagic", magic);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMobility", mobility);
        itemStack = NBTUtils.setString(itemStack, "GameStaffUUID", getStaffUUID());
        if(getGameItemLevel() != null) {
            itemStack = getGameItemLevel().setItemToLevel(itemStack);
        }
        return itemStack.clone();
    }

    public GameStaff createNewInstance() throws InstantiationException, IllegalAccessException {
        GameStaff game = this.getClass().newInstance();
        game.setStaffUUID(UUID.randomUUID().toString());
        game.setGameItemLevel(new GameItemLevel(this, 0, 0));
        Main.getInstance().getGameStaffs().add(game);
        return game;
    }

    public void setGameItemLevel(GameItemLevel gameItemLevel) {
        this.gameItemLevel = gameItemLevel;
    }

    public GameItemLevel getGameItemLevel() {
        return gameItemLevel;
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

    public int getArmor() {
        return armor;
    }

    public int getMagic() {
        return magic;
    }

    public int getMobility() {
        return mobility;
    }

    public int getOriginalArmor() {
        return originalArmor;
    }

    public int getOriginalMagic() {
        return originalMagic;
    }

    public int getOriginalMobility() {
        return originalMobility;
    }

    public void setArmor(int armor) {
        if(originalArmor == 0) {
            originalArmor = armor;
        }
        this.armor = armor;
    }

    public void setMagic(int magic) {
        if(originalMagic == 0) {
            originalMagic = magic;
        }
        this.magic = magic;
    }

    public void setMobility(int mobility) {
        if(originalMobility == 0) {
            originalMobility = mobility;
        }
        this.mobility = mobility;
    }
}
