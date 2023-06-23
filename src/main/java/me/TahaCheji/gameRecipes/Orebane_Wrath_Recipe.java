package me.TahaCheji.gameRecipes;

import me.TahaCheji.gameItems.Orebane_Blade;
import me.TahaCheji.gameItems.Orebane_Wrath;
import me.TahaCheji.itemData.GameRecipeData.GameRecipe;
import me.TahaCheji.itemData.GameRecipeData.GameRecipeConstructor;
import me.TahaCheji.itemData.GameRecipeData.GameRecipeIngredient;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Orebane_Wrath_Recipe extends GameRecipe {

    public Orebane_Wrath_Recipe() {
        super(new GameRecipeConstructor()
                .shape("ABA", "ABA", "ACA")
                .ingredient('A', new GameRecipeIngredient(null, 1))
                .ingredient('B', new GameRecipeIngredient(new Orebane_Blade().getItem(), 1))
                .ingredient('C', new GameRecipeIngredient(new ItemStack(Material.STICK), 1))
                .result(new Orebane_Wrath().getGameWeapon()).createRecipe());
    }
}
