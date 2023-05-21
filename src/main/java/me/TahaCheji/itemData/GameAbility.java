package me.TahaCheji.itemData;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameAbility {
    private final String name;
    private final List<String> description;
    private final ItemAbilityType type;
    private final double coolDown;
    private int abilityDamage;

    public GameAbility(String name, ItemAbilityType type, double coolDown, int damage, String... description) {
        this.name = name;
        this.description = Arrays.asList(description);
        this.type = type;
        this.coolDown = coolDown;
        this.abilityDamage = damage;
    }

    public List<String> toLore() {
        List<String> lore = new ArrayList();
        lore.add(ChatColor.GOLD + "Item Ability: " + this.name + " " + ChatColor.GOLD + ChatColor.DARK_GREEN + "[" +  this.type.getText() + "]");
        if(!(abilityDamage == 0)) {
            lore.add(ChatColor.RED + "Ability Damage: " + abilityDamage + ChatColor.DARK_GRAY + " | " + ChatColor.AQUA + "CoolDown: " + coolDown);
        }
        lore.add("");
        for(String string : description) {
            lore.add(ChatColor.DARK_GRAY + string);
        }
        return lore;
    }

    public void setAbilityDamage(int abilityDamage) {
        this.abilityDamage = abilityDamage;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    public ItemAbilityType getType() {
        return type;
    }

    public double getCoolDown() {
        return coolDown;
    }

    public int getAbilityDamage() {
        return abilityDamage;
    }
}
