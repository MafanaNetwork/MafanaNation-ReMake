package me.TahaCheji;

import me.TahaCheji.adminCommand.*;
import me.TahaCheji.gameScoreboards.MainScoreboard;
import me.TahaCheji.itemData.*;
import me.TahaCheji.itemData.GameArmorData.GameArmor;
import me.TahaCheji.itemData.GameArmorData.GameArmorSet;
import me.TahaCheji.itemData.GameStaffData.GameStaff;
import me.TahaCheji.itemData.GameWeaponData.GameWeapons;
import me.TahaCheji.mobData.GameMob;
import me.TahaCheji.mobData.GameMobBoss;
import me.TahaCheji.playerData.GamePlayer;
import me.TahaCheji.itemData.GameRecipeData.GameRecipe;
import me.TahaCheji.sectionsData.GameSections;
import me.tahacheji.mafananetwork.MafanaBank;
import me.tahacheji.mafananetwork.data.GamePlayerCoins;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Main extends JavaPlugin {

    private MainScoreboard mainScoreboard = new MainScoreboard();

    private List<GamePlayer> gamePlayers = new ArrayList<>();
    private GamePlayerCoins playerGamePlayerCoins = null;
    public static Main instance;

    private List<GameItem> gameItems = new ArrayList<>();
    private List<GameWeapons> originalGameWeapons = new ArrayList<>();
    private List<GameArmor> originalGameArmor = new ArrayList<>();
    private List<GameBow> originalGameBow = new ArrayList<>();
    private List<GameSpell> originalGameSpells = new ArrayList<>();
    private List<GameStaff> originalGameStaffs = new ArrayList<>();

    private List<GameWeapons> gameWeapons = new ArrayList<>();

    private List<GameArmor> gameArmors = new ArrayList<>();
    private List<GameArmorSet> gameArmorSet = new ArrayList<>();

    private List<GameBow> gameBows = new ArrayList<>();
    private List<GameSpell> gameSpells = new ArrayList<>();
    private List<GameStaff> gameStaffs = new ArrayList<>();

    private List<GameMob> gameMobs = new ArrayList<>();
    private List<GameMob> activeMobs = new ArrayList<>();
    private List<GameMobBoss> gameBosses = new ArrayList<>();
    private List<GameMobBoss> activeBoss = new ArrayList<>();

    private List<ArmorStand> armorStands = new ArrayList<>();

    private List<GameSections> gameSections = new ArrayList<>();

    private List<GameRecipe> gameGameRecipes = new ArrayList<>();

    private HashMap<Player, GameMobBoss> playerBossFight = new HashMap<>();
    private HashMap<GameItem, GamePlayer> coolDownHashMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        playerGamePlayerCoins = MafanaBank.getInstance().getGamePlayerCoins();
        List<World> worlds = Bukkit.getWorlds();
        for (World world : worlds) {
            List<LivingEntity> livingEntities = world.getLivingEntities();
            for (LivingEntity entity : livingEntities) {
                if (entity instanceof Player) {
                    continue;
                }
                entity.setHealth(0);
            }
        }
        String packageName = getClass().getPackage().getName();
        for (ArmorStand armorStand : armorStands) {
            armorStand.remove();
            armorStands.remove(armorStand);
        }
        for (LivingEntity livingEntity : Bukkit.getWorld("world").getLivingEntities()) {
            livingEntity.remove();
        }
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException ignored) {

            }
        }
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(GameSections.class)) {
            try {
                GameSections listener = (GameSections) clazz.getDeclaredConstructor().newInstance();
                getGameSections().add(listener);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException ignored) {

            }
        }
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(GameRecipe.class)) {
            try {
                GameRecipe listener = (GameRecipe) clazz.getDeclaredConstructor().newInstance();
                getGameRecipes().add(listener.getInstance());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException ignored) {
            }
        }
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(GameMob.class)) {
            try {
                GameMob getClass = (GameMob) clazz.getDeclaredConstructor().newInstance();
                getClass.registerMob();
                if (getClass instanceof GameMobBoss) {
                    gameBosses.add((GameMobBoss) getClass);
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException ignored) {
            }
        }
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(GameArmorSet.class)) {
            try {
                GameArmorSet getClass = (GameArmorSet) clazz.getDeclaredConstructor().newInstance();
                getGameArmorSet().add(getClass);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException ignored) {
            }
        }
        for (Class<?> clazz : new Reflections(packageName).getSubTypesOf(GameItem.class)) {
            try {
                GameItem gameItem = (GameItem) clazz.getDeclaredConstructor().newInstance();
                gameItem.registerItem();
                if (gameItem instanceof GameWeapons) {
                    originalGameWeapons.add((GameWeapons) gameItem);
                }
                if (gameItem instanceof GameArmor) {
                    originalGameArmor.add((GameArmor) gameItem);
                }
                if (gameItem instanceof GameBow) {
                    originalGameBow.add((GameBow) gameItem);
                }
                if (gameItem instanceof GameStaff) {
                    originalGameStaffs.add((GameStaff) gameItem);
                }
                if (gameItem instanceof GameSpell) {
                    originalGameSpells.add((GameSpell) gameItem);
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException
                     | NoSuchMethodException ignored) {
            }
        }

        getCommand("mfinv").setExecutor(new PlayerInventoryAdmin());
        getCommand("mfcoin").setExecutor(new PlayerCoinAdmin());
        getCommand("mfitem").setExecutor(new PlayerItemAdmin());
        getCommand("mfmob").setExecutor(new PlayerMobAdmin());
        getCommand("mftp").setExecutor(new PlayerWorldTP());
    }

    @Override
    public void onDisable() {
        for (GameMob gameMob : getActiveMobs()) {
            gameMob.killMob();
        }
    }

    public GamePlayer getGamePlayer(Player player) {
        for (GamePlayer gamePlayer : getGamePlayers()) {
            if (gamePlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                return gamePlayer;
            }
        }
        return null;
    }

    public GamePlayer getGamePlayer(OfflinePlayer player) {
        for (GamePlayer gamePlayer : getGamePlayers()) {
            if (gamePlayer.getName().equals(player.getName())) {
                return gamePlayer;
            }
        }
        return null;
    }


    public List<GameRecipe> getGameRecipes() {
        return gameGameRecipes;
    }

    public List<GameSections> getGameSections() {
        return gameSections;
    }

    public MainScoreboard getMainScoreboard() {
        return mainScoreboard;
    }

    public HashMap<GameItem, GamePlayer> getCoolDownHashMap() {
        return coolDownHashMap;
    }

    public List<GameArmorSet> getGameArmorSet() {
        return gameArmorSet;
    }

    public List<ArmorStand> getArmorStands() {
        return armorStands;
    }

    public List<GameMob> getGameMobs() {
        return gameMobs;
    }

    public List<GameMob> getActiveMobs() {
        return activeMobs;
    }

    public List<GameMobBoss> getGameBosses() {
        return gameBosses;
    }

    public List<GameMobBoss> getActiveBoss() {
        return activeBoss;
    }

    public HashMap<Player, GameMobBoss> getPlayerBossFight() {
        return playerBossFight;
    }

    public List<GameWeapons> getGameWeapons() {
        return gameWeapons;
    }

    public List<GameStaff> getGameStaffs() {
        return gameStaffs;
    }

    public List<GameSpell> getGameSpells() {
        return gameSpells;
    }

    public List<GameArmor> getGameArmors() {
        return gameArmors;
    }

    public List<GameBow> getGameBows() {
        return gameBows;
    }

    public List<GameBow> getOriginalGameBow() {
        return originalGameBow;
    }

    public List<GameSpell> getOriginalGameSpells() {
        return originalGameSpells;
    }

    public List<GameStaff> getOriginalGameStaffs() {
        return originalGameStaffs;
    }

    public GamePlayerCoins getPlayerGamePlayerCoins() {
        return playerGamePlayerCoins;
    }

    public List<GameArmor> getOriginalGameArmor() {
        return originalGameArmor;
    }

    public List<GameItem> getGameItems() {
        return gameItems;
    }

    public List<GameWeapons> getOriginalGameWeapons() {
        return originalGameWeapons;
    }

    public GamePlayerCoins getPlayerCoins() {
        return playerGamePlayerCoins;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public static Main getInstance() {
        return instance;
    }
}
