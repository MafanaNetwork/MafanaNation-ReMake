package me.TahaCheji.recipeData;

import me.TahaCheji.gameItems.Weapons.recipeItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class testGameRecipe extends GameRecipe {
    public testGameRecipe() {
        super(new GameRecipeConstructor()
                .shape("AAA", "ABA", "AAA")
                .ingredient('A', new GameRecipeIngredient(null, 1))
                .ingredient('B', new GameRecipeIngredient(new recipeItem().getItem(), 2))
                .result(new ItemStack(Material.STONE, 8)).createRecipe());
    }

}
