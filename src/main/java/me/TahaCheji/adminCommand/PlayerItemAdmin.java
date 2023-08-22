package me.TahaCheji.adminCommand;

import me.TahaCheji.gameUtil.ItemUtil;
import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameItemGUI;
import me.TahaCheji.itemData.GameRecipeData.GameRecipe;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import me.TahaCheji.itemData.GameRecipeData.GameRecipeCrafting;
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
            if(args[0].equalsIgnoreCase("test")) {
                GameRecipe.itemArmorStand(player.getItemInHand(), player);
            }
            if(args[0].equalsIgnoreCase("cr")) {
                player.openInventory(GameRecipeCrafting.getCraftingGui());
            }
            if (args[0].equalsIgnoreCase("level")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameWeapons gameWeapon = ItemUtil.getGameWeapon(itemStack);
                assert gameWeapon != null;
                gameWeapon.getGameItemLevel().addLevel(player, Integer.parseInt(args[1]), itemStack);
                player.setItemInHand(gameWeapon.getGameWeapon());
            }
            if (args[0].equalsIgnoreCase("levelmage")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameStaff gameStaff = ItemUtil.getGameStaff(itemStack);
                assert gameStaff != null;
                gameStaff.getGameItemLevel().addLevel(player, Integer.parseInt(args[1]), itemStack);
                player.setItemInHand(gameStaff.getGameStaff());
            }
            if(args[0].equalsIgnoreCase("check")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameArmor originalGameWeapons = ItemUtil.getGameArmor(itemStack);
                player.sendMessage(String.valueOf(originalGameWeapons != null));
            }
            if (args[0].equalsIgnoreCase("xp")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameWeapons gameWeapon = ItemUtil.getGameWeapon(itemStack);
                assert gameWeapon != null;
                gameWeapon.getGameItemLevel().addXP(player, Integer.parseInt(args[1]), itemStack);
                player.setItemInHand(gameWeapon.getGameWeapon());
            }
            if (args[0].equalsIgnoreCase("xpmage")) {
                ItemStack itemStack = ((Player) sender).getItemInHand();
                GameStaff gameStaff = ItemUtil.getGameStaff(itemStack);
                assert gameStaff != null;
                gameStaff.getGameItemLevel().addXP(player, Integer.parseInt(args[1]), itemStack);
                player.setItemInHand(gameStaff.getGameStaff());
            }
            if(args[0].equalsIgnoreCase("items")) {
                new GameItemGUI().getAllItemsGui().open((HumanEntity) sender);
                return true;
            }
        }
        return false;
    }
}
