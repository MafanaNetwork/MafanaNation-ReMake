package me.TahaCheji.mobData;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface GameMobEvents {

    default boolean onSpawn(Player player, Entity entity){return false;}
    default boolean getHit(Player player, Entity entity){return false;}
    default boolean onAbilityHit(Player player, Entity entity){return false;}
    default boolean passiveAbility(Entity entity){return false;}
    default boolean stageOne(Player player, Entity entity, int health){return false;}
    default boolean stageTwo(Player player,Entity entity, int health){return false;}
    default boolean stageThree(Player player,Entity entity, int health){return false;}
    default boolean onDeath(Player player, Entity entity){return false;}

}
