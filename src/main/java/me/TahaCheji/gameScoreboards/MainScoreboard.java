package me.TahaCheji.gameScoreboards;

import fr.mrmicky.fastboard.FastBoard;
import me.TahaCheji.managers.ScoreboardManager;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.ChatColor;

public class MainScoreboard extends ScoreboardManager {

    public MainScoreboard() {
        super(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD +  "MafanaNation" + ChatColor.DARK_GRAY + "]");
    }

    @Override
    public void updateBoard(FastBoard board, GamePlayer gamePlayer) {
        if(gamePlayer.getGameSections() == null) {
            board.updateLines(
                    ChatColor.GOLD + "=-=-=-=-=-=-=-=-=-=-=-=-",
                    "",
                    ChatColor.DARK_GRAY + ">> " + "Coins: " + ChatColor.GOLD + gamePlayer.getCoins().getCoins(gamePlayer.getPlayer()),
                    "",
                    ChatColor.DARK_GRAY + ">> " + "Location: " + ChatColor.GOLD + "null",
                    "",
                    ChatColor.DARK_GRAY + ">> " + "Player loans: " + ChatColor.GOLD + "none",
                    "",
                    ChatColor.GOLD + "-=-=-=-Mafana.net-=-=-=-"
            );
        } else {
            board.updateLines(
                    ChatColor.GOLD + "=-=-=-=-=-=-=-=-=-=-=-=-",
                    "",
                    ChatColor.DARK_GRAY + ">> " + "Coins: " + ChatColor.GOLD + gamePlayer.getCoins().getCoins(gamePlayer.getPlayer()),
                    "",
                    ChatColor.DARK_GRAY + ">> " + "Location: " + ChatColor.GOLD + gamePlayer.getGameSections().getName(),
                    "",
                    ChatColor.DARK_GRAY + ">> " + "Player loans: " + ChatColor.GOLD + "none",
                    "",
                    ChatColor.GOLD + "-=-=-=-Mafana.net-=-=-=-"
            );
        }
    }
}
