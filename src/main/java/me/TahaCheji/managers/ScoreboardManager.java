package me.TahaCheji.managers;

import fr.mrmicky.fastboard.FastBoard;
import me.TahaCheji.Main;
import me.TahaCheji.playerData.GamePlayer;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ScoreboardManager {

    public String name;
    public FastBoard board;
    private final HashMap<UUID, FastBoard> boards = new HashMap<>();

    public ScoreboardManager(String name) {
        this.name = name;
    }

    public void updateScoreboard() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (FastBoard board : boards.values()) {
                    updateBoard(board, Main.getInstance().getGamePlayer(board.getPlayer()));
                }
            }
        }, 0, 25);
    }

    public void onJoin(GamePlayer gamePlayer) {
        board = new FastBoard(gamePlayer.getPlayer());
        board.updateTitle(name);
        this.boards.put(gamePlayer.getPlayer().getUniqueId(), board);
    }

    public void onLeave(GamePlayer gamePlayer) {
        FastBoard board = this.boards.remove(gamePlayer.getPlayer().getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    public abstract void updateBoard(FastBoard board, GamePlayer gamePlayer);

    public FastBoard getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public Map<UUID, FastBoard> getBoards() {
        return boards;
    }
}
