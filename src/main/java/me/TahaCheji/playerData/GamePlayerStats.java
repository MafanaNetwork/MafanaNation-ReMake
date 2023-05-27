package me.TahaCheji.playerData;

import me.TahaCheji.gameUtil.NBTUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GamePlayerStats {

    private final GamePlayer gamePlayer;

    private int manaRegenAmount = 2; // Amount of mana regenerated per interval

    private int healthRegenAmount = 2; // Amount of health regenerated per interval

    private final int baseMagic = 50;
    private int magic = baseMagic;
    private int maxMagic;

    private final int baseHealth = 50;
    private int health = baseHealth;
    private int maxHealth;

    private final int baseArmor = 25;
    private int armor = baseArmor;

    private final int baseStrength = 25;
    private int strength = baseStrength;

    private final int baseMobility = 25;
    private int mobility = baseMobility;



    public GamePlayerStats(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void updateStats(ItemStack heldItem, ItemStack[] armor) {
        int armorMagic = 0;
        int armorHealth = 0;
        int armorArmor = 0;
        int armorStrength = 0;
        int armorMobility = 0;

        // Calculate the stats provided by the equipped armor
        for (ItemStack armorPiece : armor) {
            if (armorPiece != null && !armorPiece.getType().isAir()) {
                armorMagic += NBTUtils.getInt(armorPiece, "ItemArmorMagic");
                armorHealth += NBTUtils.getInt(armorPiece, "ItemArmorHealth");
                armorArmor += NBTUtils.getInt(armorPiece, "ItemArmorArmor");
                armorStrength += NBTUtils.getInt(armorPiece, "ItemArmorStrength");
                armorMobility += NBTUtils.getInt(armorPiece, "ItemArmorMobility");
            }
        }

        if (heldItem != null && !heldItem.getType().isAir()) {
            setArmor(NBTUtils.getInt(heldItem, "ItemWeaponArmor") + armorArmor + baseArmor);
            setStrength(NBTUtils.getInt(heldItem, "ItemWeaponStrength") + armorStrength + baseStrength);
            setMobility(NBTUtils.getInt(heldItem, "ItemWeaponMobility") + armorMobility + baseMobility);

            setMaxHealth(NBTUtils.getInt(heldItem, "ItemWeaponHealth") + armorHealth + baseHealth);
            setMaxMagic(NBTUtils.getInt(heldItem, "ItemWeaponMagic") + armorMagic  + baseMagic);
        } else {
            setArmor(armorArmor + baseArmor);
            setStrength(armorStrength + baseStrength);
            setMobility(armorMobility + baseMobility);

            setMaxHealth(armorHealth + baseHealth);
            setMaxMagic(armorMagic + baseMagic);
        }
    }

    private void clearStats() {
        magic = 0;
        health = 0;
        armor = 0;
        strength = 0;
        mobility = 0;
    }

    public void actionBar(GamePlayer player) {
        String s = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "⚔" + gamePlayer.getGamePlayerStats().getStrength() + ChatColor.DARK_GRAY + " - " +
                ChatColor.RED + "❤" + gamePlayer.getGamePlayerStats().getHealth() + "/" + gamePlayer.getGamePlayerStats().getMaxHealth() + ChatColor.DARK_GRAY + " - " +
                ChatColor.YELLOW + "⛨" + gamePlayer.getGamePlayerStats().getArmor() + ChatColor.DARK_GRAY + " - " +
                ChatColor.BLUE + "[M]" + gamePlayer.getGamePlayerStats().getMagic() + "/" + gamePlayer.getGamePlayerStats().getMaxMagic() + ChatColor.DARK_GRAY + " - " +
                ChatColor.DARK_GREEN + "〰" + gamePlayer.getGamePlayerStats().getMobility() + ChatColor.DARK_GRAY + "]" ;
        player.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
    }

    public void regenerateHealth() {
        if (health < maxHealth) {
            health = Math.min(health + healthRegenAmount, maxHealth);
        } else if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void regenerateMana() {
        if (magic < maxMagic) {
            magic = Math.min(magic + manaRegenAmount, maxMagic);
        } else if (magic > maxMagic) {
            magic = maxMagic;
        }
    }

    public int getPlayerDamageStrength(int armor) {
        double strength = getStrength();
        double scalingFactor = 0.5; // 50% reduction

        double scaledStrength = strength * scalingFactor;

        double damageReduction = armor / 5.0;
        double finalDamage = Math.max(0, scaledStrength - damageReduction);
        int damage = (int) finalDamage;

        return damage;
    }

    public int getPlayerDamageMagic(int armor, int abilityDamage) {
        double strength = getMaxMagic();
        double scalingFactor = 0.5;
        double scaledStrength = strength * scalingFactor;
        double scaledAbilityDamage = abilityDamage * 0.5;
        double combinedStrength = scaledStrength + scaledAbilityDamage;
        double damageReduction = armor / 5.0;
        double finalDamage = Math.max(0, combinedStrength - damageReduction);
        int damage = (int) finalDamage;

        return damage;
    }

    public int playerGetDamage(int incomingDamage) {
        double strength = incomingDamage;
        double scalingFactor = 0.5; // Scaling factor for strength
        double defenseScalingFactor = 0.35; // Scaling factor for defense

        double scaledStrength = strength * scalingFactor;

        double damageReduction = getArmor() / 5.0 * defenseScalingFactor;
        double finalDamage = Math.max(0, scaledStrength - damageReduction);
        int damage = (int) Math.ceil(finalDamage); // Use Math.ceil to round up the damage

        // Calculate remaining health
        int remainingHealth = (int) (gamePlayer.getPlayer().getHealth() - damage);
        remainingHealth = Math.max(remainingHealth, 0); // Ensure remaining health is not negative

        return remainingHealth;
    }

    public void setMobilityStats() {
        Player player = gamePlayer.getPlayer();
        double mobility = getMobility();

        if (mobility > 25) {
            double walkSpeed = 0.3 + (mobility * 0.001);
            int jumpBoostLevel = (int) (mobility / 75.0);

            player.setWalkSpeed((float) walkSpeed);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, jumpBoostLevel, false, false));
        } else {
            player.setWalkSpeed((float) 0.3);
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }










    public int getBaseArmor() {
        return baseArmor;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseMagic() {
        return baseMagic;
    }

    public int getBaseMobility() {
        return baseMobility;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public int getHealthRegenAmount() {
        return healthRegenAmount;
    }

    public int getManaRegenAmount() {
        return manaRegenAmount;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxMagic(int maxMagic) {
        this.maxMagic = maxMagic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getMagic() {
        return magic;
    }

    public int getMobility() {
        return mobility;
    }

    public int getStrength() {
        return strength;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
