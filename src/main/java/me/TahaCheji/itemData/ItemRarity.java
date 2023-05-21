package me.TahaCheji.itemData;

import org.bukkit.ChatColor;

public enum ItemRarity {

    WOOD(ChatColor.DARK_GRAY, ChatColor.DARK_GRAY + "Wooden "),

    COAL(ChatColor.GRAY, ChatColor.GRAY + "Charcoal "),

    IRON(ChatColor.WHITE, ChatColor.WHITE + "Sturdy Iron "),

    LAPIS(ChatColor.AQUA, ChatColor.AQUA + "Azure "),

    GOLD(ChatColor.GOLD, ChatColor.GOLD + "Gilded "),

    REDSTONE(ChatColor.RED, ChatColor.RED + "Crimson Ember "),

    DIAMOND(ChatColor.BLUE, ChatColor.BLUE + "Radiant Diamond "),

    OBSIDAIN(ChatColor.BLACK, ChatColor.BLACK + "Shadowed Obsidian "),

    ADMIN(ChatColor.DARK_RED, ChatColor.RED + "Exalted ");

    private final ChatColor color;

    private final String lore;

    ItemRarity(ChatColor color, String lore) {
        this.color = color;
        this.lore = lore;
    }


    public ChatColor getColor() {
        return color;
    }

    public String getLore() {
        return lore;

    }
}
