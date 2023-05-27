package me.TahaCheji.recipeData;

import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GameRecipe {

    private List<ItemStack> ingredients;
    private ItemStack result;
    private GameRecipe instance;

    public GameRecipe(GameRecipe instance) {
        this.instance = instance;
    }

    public GameRecipe(List<ItemStack> ingredients, ItemStack result) {
        this.ingredients = ingredients;
        this.result = result;
    }

    public List<ItemStack> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }

    public void craftItem(Player player, Inventory gui) {
        ItemStack[] craftingItems = new ItemStack[9];

        craftingItems[0] = gui.getItem(1);
        craftingItems[1] = gui.getItem(2);
        craftingItems[2] = gui.getItem(3);
        craftingItems[3] = gui.getItem(10);
        craftingItems[4] = gui.getItem(11);
        craftingItems[5] = gui.getItem(12);
        craftingItems[6] = gui.getItem(19);
        craftingItems[7] = gui.getItem(20);
        craftingItems[8] = gui.getItem(21);

        if (!isSame(Arrays.asList(craftingItems))) {
            return; // Items in the GUI do not match the recipe
        }

        ItemStack resultItem = getResult().clone();
        ItemStack slot10 = craftingItems[3];
        if (slot10 != null) {
            int requiredAmount = getIngredients().get(3).getAmount();
            if (slot10.getAmount() > requiredAmount) {
                slot10.setAmount(slot10.getAmount() - requiredAmount);
                gui.setItem(10, slot10.clone());
            } else {
                gui.setItem(10, null);
            }
        }

        ItemStack slot11 = craftingItems[4];
        if (slot11 != null) {
            int requiredAmount = getIngredients().get(4).getAmount();
            if (slot11.getAmount() > requiredAmount) {
                slot11.setAmount(slot11.getAmount() - requiredAmount);
                gui.setItem(11, slot11.clone());
            } else {
                gui.setItem(11, null);
            }
        }

        ItemStack slot12 = craftingItems[5];
        if (slot12 != null) {
            int requiredAmount = getIngredients().get(5).getAmount();
            if (slot12.getAmount() > requiredAmount) {
                slot12.setAmount(slot12.getAmount() - requiredAmount);
                gui.setItem(12, slot12.clone());
            } else {
                gui.setItem(12, null);
            }
        }

// Update the next slots: 19, 20, 21
        ItemStack slot19 = craftingItems[6];
        if (slot19 != null) {
            int requiredAmount = getIngredients().get(6).getAmount();
            if (slot19.getAmount() > requiredAmount) {
                slot19.setAmount(slot19.getAmount() - requiredAmount);
                gui.setItem(19, slot19.clone());
            } else {
                gui.setItem(19, null);
            }
        }

        ItemStack slot20 = craftingItems[7];
        if (slot20 != null) {
            int requiredAmount = getIngredients().get(7).getAmount();
            if (slot20.getAmount() > requiredAmount) {
                slot20.setAmount(slot20.getAmount() - requiredAmount);
                gui.setItem(20, slot20.clone());
            } else {
                gui.setItem(20, null);
            }
        }

        ItemStack slot21 = craftingItems[8];
        if (slot21 != null) {
            int requiredAmount = getIngredients().get(8).getAmount();
            if (slot21.getAmount() > requiredAmount) {
                slot21.setAmount(slot21.getAmount() - requiredAmount);
                gui.setItem(21, slot21.clone());
            } else {
                gui.setItem(21, null);
            }
        }

        ItemStack slot1 = craftingItems[0];
        if (slot1 != null) {
            int requiredAmount = getIngredients().get(0).getAmount();
            if (slot1.getAmount() > requiredAmount) {
                slot1.setAmount(slot1.getAmount() - requiredAmount);
                gui.setItem(1, slot1.clone());
            } else {
                gui.setItem(1, null);
            }
        }

        ItemStack slot2 = craftingItems[1];
        if (slot2 != null) {
            int requiredAmount = getIngredients().get(1).getAmount();
            if (slot2.getAmount() > requiredAmount) {
                slot2.setAmount(slot2.getAmount() - requiredAmount);
                gui.setItem(2, slot2.clone());
            } else {
                gui.setItem(2, null);
            }
        }

        ItemStack slot3 = craftingItems[2];
        if (slot3 != null) {
            int requiredAmount = getIngredients().get(2).getAmount();
            if (slot3.getAmount() > requiredAmount) {
                slot3.setAmount(slot3.getAmount() - requiredAmount);
                gui.setItem(3, slot3.clone());
            } else {
                gui.setItem(3, null);
            }
        }

        // Check if the result item can fit in the player's inventory
        HashMap<Integer, ItemStack> remainingItems = player.getInventory().addItem(resultItem);

        if (!remainingItems.isEmpty()) {
            // Inventory is full, drop the item at the player's location
            player.getWorld().dropItem(player.getLocation(), resultItem);
        }
        gui.setItem(14, null);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10f, 0f);
    }



    public boolean isSame(List<ItemStack> items) {
        if (items.size() != ingredients.size()) {
            return false;
        }

        for (int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            ItemStack ingredient = ingredients.get(i);
            if (item == null || ingredient == null) {
                if (item == null && ingredient == null) {
                    continue;
                } else {
                    return false;
                }
            }
            if (item.getAmount() < ingredient.getAmount()) {
                return false;
            }
            if (!Objects.equals(NBTUtils.getString(item, "GameItemUUID"), NBTUtils.getString(ingredient, "GameItemUUID"))) {
                return false;
            }
        }

        return true;
    }

    public GameRecipe getInstance() {
        return instance;
    }
}

