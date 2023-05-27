package me.TahaCheji.mobData;

import me.TahaCheji.Main;
import me.TahaCheji.gameUtil.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GameMobBoss extends GameMob{

    private List<LootItem> lootTable;
    private List<GameMob> minions = new ArrayList<>();
    private LivingEntity entity;
    private UUID mobBossName;

    public boolean stageOne = false;
    public boolean stageTwo = false;
    public boolean stageThree = false;

    public GameMobBoss(String name, double spawnChance, EntityType type, double maxHealth, int strength, int defense, int damage, int speed, ItemStack mainItem, ItemStack[] armor, LootItem... lootItems) {
        super(name, spawnChance, type, maxHealth, strength, defense, damage, speed, mainItem, armor, lootItems);
        this.mobBossName = UUID.randomUUID();
    }

    public GameMobBoss(String name, double spawnChance, double maxHealth, EntityType type, int strength, int defense, int damage, int speed, ItemStack mainItem, ItemStack[] armor) {
        super(name, spawnChance, maxHealth, type, strength, defense, damage, speed, mainItem, armor);
        this.mobBossName = UUID.randomUUID();
    }


    public LivingEntity spawnBoss(Location location, Player player) {
        LivingEntity entity = spawnMob(location, player);
        entity.setCustomNameVisible(true);
        entity.setCustomName(getName() + " " + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "♥" + getMaxHealth() +  ChatColor.DARK_GRAY + "/" + ChatColor.RED + getMaxHealth()  + "♥" + ChatColor.DARK_GRAY + "]");
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(getMaxHealth());
        entity.setMaxHealth(getMaxHealth());
        entity.setHealth(getMaxHealth());
        if (getDefense() != 0) {
            entity.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(getDefense());
        }
        if (getSpeed() != 0) {
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(getSpeed());
        }
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(getStrength() * getDamage() / 5);
        EntityEquipment inv = entity.getEquipment();
        if (getArmor() != null) inv.setArmorContents(getArmor());
        inv.setHelmetDropChance(0f);
        inv.setChestplateDropChance(0f);
        inv.setLeggingsDropChance(0f);
        inv.setBootsDropChance(0f);
        inv.setItemInMainHand(getMainItem());
        inv.setItemInMainHandDropChance(0f);
        NBTUtils.setEntityString(entity, "MobBossName", mobBossName.toString());
        this.entity = entity;
        Main.getInstance().getActiveBoss().add(this);
        Main.getInstance().getPlayerBossFight().put(player, this);
        onSpawn(player, entity);
        setEntity(entity);
        return entity;
    }

    public void killMob() {
        Main.getInstance().getActiveBoss().remove(this);
        if (!getMob().isDead()) {
            getMob().remove();
        }
    }

    public void tryDropLoot(Location location, Player player) {
        for (LootItem item : lootTable) {
            item.tryDropItem(location, player);
        }
    }

    public GameMobBoss getBossMob(String name) {
        if(Objects.equals(name, mobBossName.toString())) {
            return this;
        }
        return null;
    }

    public UUID getMobBossUUID() {
        return mobBossName;
    }
    public void addMinion(GameMob mob) {minions.add(mob);}
    public void setEntity(LivingEntity entity) {this.entity = entity;}
    public List<GameMob> getMinions() {return minions;}
    public void setLootTable(LootItem... lootTable) {this.lootTable = Arrays.asList(lootTable);}
    public Entity getMob() {return entity;}
    public LivingEntity getBossEntity() {return entity;}
    public boolean isStageOne() {return stageOne;}
    public boolean isStageTwo() {return stageTwo;}
    public boolean isStageThree() {return stageThree;}
    public void setStageOne(boolean stageOne) {this.stageOne = stageOne;}
    public void setStageTwo(boolean stageTwo) {this.stageTwo = stageTwo;}
    public void setStageThree(boolean stageThree) {this.stageThree = stageThree;}
    public List<LootItem> getLootTable() {return lootTable;}
}
