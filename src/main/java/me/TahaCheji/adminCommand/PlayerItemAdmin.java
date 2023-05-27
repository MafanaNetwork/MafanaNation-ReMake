package me.TahaCheji.adminCommand;

import me.TahaCheji.gameItems.Weapons.recipeItem;
import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameItemGUI;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import me.TahaCheji.recipeData.GameRecipeCrafting;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerItemAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("mfitem")) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: Error");
                return true;
            }
            if (!player.isOp()) {
                player.sendMessage(ChatColor.RED + "[MafanaNation Manager]: You Do Not Have The Permission To Do This Command");
                return true;
            }
            if(args[0].equalsIgnoreCase("cr")) {
                player.openInventory(GameRecipeCrafting.getCraftingGui());
            }
            if (args[0].equalsIgnoreCase("level")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameWeapons originalGameWeapons = ItemUtil.getGameWeapon(itemStack);
                player.setItemInHand(originalGameWeapons.getItemLevel().addLevel(1, originalGameWeapons));
            }
            if (args[0].equalsIgnoreCase("levelm")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameStaff originalGameWeapons = ItemUtil.getGameStaff(itemStack);
                player.setItemInHand(originalGameWeapons.getItemLevel().addLevel(1, originalGameWeapons));
            }
            if(args[0].equalsIgnoreCase("check")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameArmor originalGameWeapons = ItemUtil.getGameArmor(itemStack);
                player.sendMessage(String.valueOf(originalGameWeapons != null));
            }
            if (args[0].equalsIgnoreCase("xp")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameWeapons originalGameWeapons = ItemUtil.getGameWeapon(itemStack);
                player.setItemInHand(originalGameWeapons.getItemLevel().addXP(Integer.parseInt(args[1]), originalGameWeapons));
            }
            if(args[0].equalsIgnoreCase("items")) {
                player.getInventory().addItem(new recipeItem().getItem(), new recipeItem().getItem(), new recipeItem().getItem(), new recipeItem().getItem(), new recipeItem().getItem(), new recipeItem().getItem(), new recipeItem().getItem(), new recipeItem().getItem(), new recipeItem().getItem());
                new GameItemGUI().getAllItemsGui().open((HumanEntity) sender);
                return true;
            }
        }
        return false;
    }
}
