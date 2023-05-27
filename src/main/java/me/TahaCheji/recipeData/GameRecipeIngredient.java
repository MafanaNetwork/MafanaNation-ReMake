package me.TahaCheji.recipeData;

import org.bukkit.inventory.ItemStack;

public class GameRecipeIngredient {
    private ItemStack itemStack = null;

    public GameRecipeIngredient(ItemStack itemStack, int amount) {
        if(itemStack != null) {
            ItemStack newItem = itemStack;
            newItem.setAmount(amount);
            this.itemStack = newItem;
        }
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
