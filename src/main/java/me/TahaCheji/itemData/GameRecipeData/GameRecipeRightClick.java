package me.TahaCheji.itemData.GameRecipeData;

import me.TahaCheji.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRecipeRightClick implements Listener {

    private List<GameRecipe> gameRecipes;
    private Map<Player, List<GameRecipe>> playerRecipes;

    public GameRecipeRightClick() {
        this.gameRecipes = Main.getInstance().getGameRecipes();
        this.playerRecipes = new HashMap<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack clickedItem = event.getItem();

        // Check if the player right-clicked an item
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (clickedItem != null) {
                List<GameRecipe> matchingRecipes = findMatchingRecipes(clickedItem);
                if (!matchingRecipes.isEmpty()) {
                    playerRecipes.put(player, matchingRecipes);
                    openRecipeGui(player, 0);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if (inventory != null && ChatColor.stripColor(event.getView().getTitle()).equals("MafanaCraft Recipe")) {
            event.setCancelled(true);

            if (playerRecipes.containsKey(player)) {
                List<GameRecipe> recipes = playerRecipes.get(player);
                int currentPage = getCurrentPage(player);

                if (clickedItem != null && clickedItem.getType() == Material.PAPER) {
                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next Page")) {
                        openRecipeGui(player, currentPage + 1);
                    } else if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Previous Page")) {
                        openRecipeGui(player, currentPage - 1);
                    }
                } else if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                    int recipeIndex = currentPage * 5 + event.getSlot();
                    if (recipeIndex >= 0 && recipeIndex < recipes.size()) {
                        GameRecipe recipe = recipes.get(recipeIndex);
                        // Handle the selected recipe
                        // ...
                        player.closeInventory();
                    }
                }
            }
        }
    }

    private List<GameRecipe> findMatchingRecipes(ItemStack clickedItem) {
        List<GameRecipe> matchingRecipes = new ArrayList<>();
        for (GameRecipe recipe : gameRecipes) {
            List<ItemStack> ingredients = recipe.getIngredients();
            for(ItemStack itemStack : ingredients) {
                if(itemStack == null || itemStack.getType() == Material.AIR) {
                    continue;
                }
                if(clickedItem.getItemMeta() == null || clickedItem.getItemMeta().getDisplayName().equals("") || itemStack.getItemMeta().getDisplayName().equals("")) {
                    continue;
                }
                if(itemStack.getItemMeta().getDisplayName().equals(clickedItem.getItemMeta().getDisplayName())) {
                    matchingRecipes.add(recipe);
                }
            }
        }
        return matchingRecipes;
    }

    private void openRecipeGui(Player player, int page) {
        List<GameRecipe> recipes = playerRecipes.get(player);
        int totalPages = (int) Math.ceil((double) recipes.size() / 5);

        if (page < 0) {
            page = 0;
        } else if (page >= totalPages) {
            page = totalPages - 1;
        }

        playerRecipes.put(player, recipes);

        Inventory gui = Bukkit.createInventory(null, 36, ChatColor.GRAY + "" + ChatColor.BOLD + "MafanaCraft Recipe");

        ArrayList<String> lore = new ArrayList<>();

        ItemStack newItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta newMeta = newItem.getItemMeta();
        newMeta.setDisplayName(ChatColor.GRAY + "");
        newMeta.setLore(lore);
        newItem.setItemMeta(newMeta);

        for (int emptySlot = 0; emptySlot < gui.getSize(); emptySlot++) {
            if (gui.getItem(emptySlot) == null || gui.getItem(emptySlot).getType().equals(Material.AIR)) {
                gui.setItem(emptySlot, newItem);
            }
        }

        int startIndex = page * 5;
        int endIndex = Math.min(startIndex + 5, recipes.size());

        int ingredientSlot = 1;

        for (int i = startIndex; i < endIndex; i++) {
            GameRecipe recipe = recipes.get(i);
            ItemStack result = recipe.getResult();
            List<ItemStack> ingredients = recipe.getIngredients();

            for (ItemStack ingredient : ingredients) {
                gui.setItem(ingredientSlot, ingredient);
                ingredientSlot++;
                if (ingredientSlot == 4) {
                    ingredientSlot = 10;
                }
                if (ingredientSlot == 13) {
                    ingredientSlot = 19;
                }
                if(ingredientSlot == 22) {
                    ingredientSlot = 1;
                }
            }

            gui.setItem(14, result);
        }



        if (page > 0) {
            ItemStack previousPageItem = new ItemStack(Material.PAPER);
            ItemMeta previousPageMeta = previousPageItem.getItemMeta();
            previousPageMeta.setDisplayName(ChatColor.GREEN + "Previous Page");
            previousPageItem.setItemMeta(previousPageMeta);
            gui.setItem(27, previousPageItem);
        }

        if (page < totalPages - 1) {
            ItemStack nextPageItem = new ItemStack(Material.PAPER);
            ItemMeta nextPageMeta = nextPageItem.getItemMeta();
            nextPageMeta.setDisplayName(ChatColor.GREEN + "Next Page");
            nextPageItem.setItemMeta(nextPageMeta);
            gui.setItem(35, nextPageItem);
        }

        player.openInventory(gui);
    }


    private int getCurrentPage(Player player) {
        if (playerRecipes.containsKey(player)) {
            return (int) Math.floor(playerRecipes.get(player).size() / 5);
        }
        return 0;
    }
}
