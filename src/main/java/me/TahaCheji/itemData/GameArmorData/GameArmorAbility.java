package me.TahaCheji.itemData.GameArmorData;

import me.TahaCheji.itemData.ItemAbilityType;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameArmorAbility {
    private final String name;
    private final List<String> description;
    private final ItemAbilityType type;
    private final double coolDown;

    public GameArmorAbility(String name, ItemAbilityType type, double coolDown, String... description) {
        this.name = name;
        this.description = Arrays.asList(description);
        this.type = type;
        this.coolDown = coolDown;
    }

    public List<String> toLore() {
        List<String> lore = new ArrayList();
        lore.add(ChatColor.GOLD + "Armor Ability: " + this.name + " " + ChatColor.GOLD + ChatColor.DARK_GREEN + "[" +  this.type.getText() + "]");
        for(String string : description) {
            lore.add(ChatColor.DARK_GRAY + "'" + string + "'");
        }
        return lore;
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

}
