package me.TahaCheji.gameRecipes;

import me.TahaCheji.gameItems.Orebane;
import me.TahaCheji.gameItems.Orebane_Blade;
import me.TahaCheji.itemData.GameRecipeData.GameRecipe;
import me.TahaCheji.itemData.GameRecipeData.GameRecipeConstructor;
import me.TahaCheji.itemData.GameRecipeData.GameRecipeIngredient;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Orebane_Blade_Recipe extends GameRecipe {

    public Orebane_Blade_Recipe() {
        super(new GameRecipeConstructor()
                .shape("ABA", "ABA", "AAA")
                .ingredient('A', new GameRecipeIngredient(null, 1))
                .ingredient('B', new GameRecipeIngredient(new Orebane().getItem(), 16))
                .result(new Orebane_Blade().getItem()).createRecipe());
    }
}
