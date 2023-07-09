package mc.minigame.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Spawn extends JavaPlugin {

    public static void zombies(int number, Location loc1, Location loc2, Double movement, Double dmg, Double health, Double reinforce, Double attackSpeed, Double resistance) {
        World world = loc1.getWorld();

        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());
        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        int count = 0;
        while (count < number) {
            double x = minX + (Math.random() * (maxX - minX));
            double y = minY + (Math.random() * (maxY - minY));
            double z = minZ + (Math.random() * (maxZ - minZ));

            Location spawnLocation = new Location(world, x, y, z);
            if (hasEnoughSpace(spawnLocation) && hasSolidFloor(spawnLocation)) {
                Zombie zombie = (Zombie) world.spawnEntity(spawnLocation, EntityType.ZOMBIE);
                zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(movement);
                zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dmg);
                zombie.getAttribute(Attribute.GENERIC_FOLLOW_RANGE ).setBaseValue(1000);
                //zombie.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(attackSpeed);
                zombie.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(reinforce);
                zombie.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(resistance);
                zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
                zombie.setHealth(zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()); // Set the current health to the maximum
                zombie.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1200, 1));
                zombie.setPersistent(true);


                /*Player nearestPlayer = null;
                double nearestDistance = Double.MAX_VALUE;
                for (Player player : zombie.getWorld().getPlayers()) {
                    double distance = zombie.getLocation().distance(player.getLocation());
                    if (distance < nearestDistance) {
                        nearestPlayer = player;
                        nearestDistance = distance;
                    }
                }

                if (nearestPlayer != null) {
                    zombie.setTarget(nearestPlayer);
                }*/
                count++;
            }
        }
    }



    private static boolean hasEnoughSpace(Location location) {
        int radius = 1;

        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y <= radius * 2; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = location.clone().add(x, y, z).getBlock();
                    if (!block.getType().isAir()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean hasSolidFloor(Location location) {
        int floorCheckRange = 1;

        for (int y = -floorCheckRange; y <= -1; y++) {
            Block block = location.clone().add(0, y, 0).getBlock();
            if (block.getType() == Material.AIR || !block.getType().isSolid()) {
                return false;
            }
        }

        return true;
    }
}
