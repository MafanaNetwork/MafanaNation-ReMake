package me.TahaCheji.itemData.GameWeaponData;

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

public abstract class GameWeapons extends GameItem {

    public int strength;
    public int health;
    public int armor;
    public int magic;
    public int mobility;

    public int originalStrength;
    public int originalHealth;
    public int originalArmor;
    public int originalMagic;
    public int originalMobility;

    public GameItemLevel gameItemLevel;
    public GameItemAbility gameItemAbility;
    public List<String> lore;
    private String weaponUUID;

    public GameWeapons(String name, Material material, ItemType itemType, ItemRarity itemRarity) {
        super(name, material, itemType, itemRarity);
        this.weaponUUID = UUID.randomUUID().toString();
    }

    public ItemStack getGameWeapon() {
        ItemStack itemStack = getItem().clone();
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(getItemRarity().getColor() + getItemRarity().getLore() + ChatColor.DARK_GRAY + "| " + getItemType().getLore() + " | " + getMaterial().name());
        if(getGameItemLevel() != null) {
            lore.addAll(getGameItemLevel().getLore(itemStack));
        }
        lore.add(ChatColor.DARK_GRAY + ItemUtil.itemLoreDash(lore.get(0)));
        lore.add("");
        if (strength != 0) {
            lore.add(ChatColor.DARK_RED + "⚔Strength: " + ChatColor.DARK_GRAY + "+" + getStrength());
        }
        if (health != 0) {
            lore.add(ChatColor.RED + "❤Health: " + ChatColor.DARK_GRAY + "+" + getHealth());
        }
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
            lore.addAll(getGameAbility().toLore());
        }
        lore.add("");
        lore.addAll(getLore());
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponStrength", strength);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponHealth", health);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponArmor", armor);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMagic", magic);
        itemStack = NBTUtils.setInt(itemStack, "ItemWeaponMobility", mobility);
        itemStack = NBTUtils.setString(itemStack, "GameWeaponUUID", getWeaponUUID());
        if(getGameItemLevel() != null) {
            itemStack = getGameItemLevel().setItemToLevel(itemStack);
        }
        return itemStack.clone();
    }


    public GameWeapons createNewInstance() throws InstantiationException, IllegalAccessException {
        GameWeapons gameWeapons = this.getClass().newInstance();
        gameWeapons.setWeaponUUID(UUID.randomUUID().toString());
        gameWeapons.setGameItemLevel(new GameItemLevel(this, 0, 0));
        Main.getInstance().getGameWeapons().add(gameWeapons);
        return gameWeapons;
    }

    public ItemStack setItemClone(boolean x, ItemStack itemStack) {
        return NBTUtils.setBoolean(itemStack, "ItemClone", x);
    }


    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public void setWeaponUUID(String weaponUUID) {
        this.weaponUUID = weaponUUID;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getWeaponUUID() {
        return weaponUUID;
    }

    public void setGameAbility(GameItemAbility gameItemAbility) {
        this.gameItemAbility = gameItemAbility;
    }


    public GameItemAbility getGameAbility() {
        return gameItemAbility;
    }

    public void setGameItemLevel(GameItemLevel gameItemLevel) {
        this.gameItemLevel = gameItemLevel;
    }

    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }

    public void setStrength(int strength) {
        if(originalStrength == 0) {
            originalStrength = strength;
        }
        this.strength = strength;
    }

    public void setHealth(int health) {
        if(originalHealth == 0) {
            originalHealth = health;
        }
        this.health = health;
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


    public GameItemLevel getGameItemLevel() {
        return gameItemLevel;
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

    public int getOriginalStrength() {
        return originalStrength;
    }

    public int getOriginalHealth() {
        return originalHealth;
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
}
