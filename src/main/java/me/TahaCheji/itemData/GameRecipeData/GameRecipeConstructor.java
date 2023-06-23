package me.TahaCheji.itemData.GameRecipeData;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRecipeConstructor {
    private List<String> shape;
    private Map<Character, GameRecipeIngredient> ingredients;
    private ItemStack result;

    public GameRecipeConstructor() {
        shape = new ArrayList<>();
        ingredients = new HashMap<>();
    }

    public GameRecipeConstructor shape(String... shape) {
        if (shape.length > 3) {
            throw new IllegalArgumentException("Shape cannot have more than 3 rows");
        }

        for (String row : shape) {
            if (row.length() > 3) {
                throw new IllegalArgumentException("Shape row cannot have more than 3 columns");
            }

            this.shape.add(row);
        }

        return this;
    }

    public GameRecipeConstructor ingredient(char key, GameRecipeIngredient ingredient) {
        ingredients.put(key, ingredient);
        return this;
    }

    public GameRecipeConstructor result(ItemStack result) {
        this.result = result;
        return this;
    }

    public GameRecipe createRecipe() {
        if (shape.isEmpty()) {
            throw new IllegalStateException("Shape must be defined");
        }

        if (result == null) {
            throw new IllegalStateException("Result must be defined");
        }

        List<ItemStack> ingredientList = new ArrayList<>();

        for (String row : shape) {
            for (char c : row.toCharArray()) {
                GameRecipeIngredient ingredient = ingredients.get(c);

                if (ingredient == null) {
                    throw new IllegalStateException("Ingredient for character '" + c + "' is not defined");
                }

                ingredientList.add(ingredient.getItemStack());
            }
        }
        return new GameRecipe(ingredientList, result);
    }

}
