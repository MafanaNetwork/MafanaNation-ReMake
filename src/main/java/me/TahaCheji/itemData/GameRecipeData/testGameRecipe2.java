package me.TahaCheji.itemData.GameRecipeData;

import me.TahaCheji.gameItems.testItems.Weapons.recipeItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class testGameRecipe2 extends GameRecipe {
    public testGameRecipe2() {
        super(new GameRecipeConstructor()
                .shape("AAA", "ABA", "AAA")
                .ingredient('A', new GameRecipeIngredient(new recipeItem().getItem(), 2))
                .ingredient('B', new GameRecipeIngredient(null, 1))
                .result(new ItemStack(Material.WOODEN_AXE, 8)).createRecipe());
    }

}
