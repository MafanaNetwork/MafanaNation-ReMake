package me.TahaCheji.mobData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.NBTUtils;
import me.TahaCheji.itemData.GameItemEvents;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GameMob implements GameMobEvents {

    private final String name;
    private final double spawnChance;
    private final double maxHealth;
    private double health;
    private final EntityType type;
    private final int strength;
    private final int defense;
    private final int damage;
    private final int speed;
    private int xp;
    private final ItemStack mainItem;
    private final ItemStack[] armor;
    private List<LootItem> lootTable;
    private String uuid;
    private GameMobSpawn gameMobSpawn;

    LivingEntity entity;

    public GameMob(String name, double spawnChance, EntityType type, double maxHealth, int strength, int defense, int damage, int speed,
                   ItemStack mainItem, ItemStack[] armor, LootItem... lootItems) {
        this.name = name;
        this.spawnChance = spawnChance;
        this.maxHealth = maxHealth;
        this.type = type;
        this.strength = strength;
        this.defense = defense;
        this.damage = damage;
        this.speed = speed;
        this.mainItem = mainItem;
        this.armor = armor;
        this.uuid = name + "_mob";
        lootTable = Arrays.asList(lootItems);
    }

    public GameMob(String name, double spawnChance, double maxHealth, EntityType type, int strength, int defense, int damage, int speed, ItemStack mainItem, ItemStack[] armor) {
        this.name = name;
        this.spawnChance = spawnChance;
        this.maxHealth = maxHealth;
        this.type = type;
        this.strength = strength;
        this.defense = defense;
        this.damage = damage;
        this.speed = speed;
        this.mainItem = mainItem;
        this.armor = armor;
        this.uuid = name + "_mob";
    }

    public LivingEntity spawnMob(Location location, Player player) {
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, type);
        entity.setCustomNameVisible(true);
        entity.setCustomName(getName() + " " + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "♥" + maxHealth + ChatColor.DARK_GRAY + "/" + ChatColor.RED + maxHealth + "♥" + ChatColor.DARK_GRAY + "]");
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        setHealth(maxHealth);
        entity.setHealth(maxHealth);
        if (defense != 0) {
            entity.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(defense);
        }
        if (speed != 0) {
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        }
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(strength * damage / 5);
        EntityEquipment inv = entity.getEquipment();
        if (armor != null) inv.setArmorContents(armor);
        inv.setHelmetDropChance(0f);
        inv.setChestplateDropChance(0f);
        inv.setLeggingsDropChance(0f);
        inv.setBootsDropChance(0f);
        inv.setItemInMainHand(mainItem);
        inv.setItemInMainHandDropChance(0f);
        NBTUtils.setEntityString(entity, "MobName", uuid);
        Main.getInstance().getActiveMobs().add(this);
        onSpawn(player, entity);
        setEntity(entity);
        BukkitRunnable abilityTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!entity.isDead()) {
                    passiveAbility(entity);
                }
            }
        };

        abilityTask.runTaskTimer(Main.getInstance(), 0L, 20L);
        return entity;
    }

    public void onMobDeath(Player player) {
        onDeath(player, entity);
        tryDropLoot(entity.getLocation(), player);
        System.out.println(1);
        entity.setHealth(0);
        if (this instanceof GameMobBoss) {
            Main.getInstance().getActiveBoss().remove(this);
        }
        Main.getInstance().getActiveMobs().remove(this);
    }

    public void killMob() {
        Main.getInstance().getActiveMobs().remove(this);
        if (!getMob().isDead()) {
            getMob().remove();
        }
    }

    public GameMob getMob(String name) {
        if (Objects.equals(name, uuid)) {
            return this;
        }
        return null;
    }

    public void tryDropLoot(Location location, Player player) {
        if (lootTable == null) {
            return;
        }
        for (LootItem item : lootTable) {
            item.tryDropItem(location, player);
        }
    }


    public int getXp() {
        return xp;
    }

    public String getUuid() {
        return uuid;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void setLootTable(List<LootItem> lootTable) {
        this.lootTable = lootTable;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setGameMobSpawn(GameMobSpawn gameMobSpawn) {
        this.gameMobSpawn = gameMobSpawn;
    }

    public GameMobSpawn getGameMobSpawn() {
        return gameMobSpawn;
    }

    public void setLootTable(LootItem... lootTable) {
        this.lootTable = Arrays.asList(lootTable);
    }

    public void registerMob() {
        Main.getInstance().getGameMobs().add(this);
    }

    public Location getLocation() {
        return getMob().getLocation();
    }

    public Entity getMob() {
        return entity;
    }

    public String getName() {
        return name;
    }

    public double getSpawnChance() {
        return spawnChance;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public EntityType getType() {
        return type;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public ItemStack getMainItem() {
        return mainItem;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public List<LootItem> getLootTable() {
        return lootTable;
    }
}
