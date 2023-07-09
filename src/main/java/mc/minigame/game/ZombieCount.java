package mc.minigame.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

public class ZombieCount {
    public static int getTotalZombieCount() {
        int totalZombies = 0;

        World overworld = Bukkit.getWorld("world");

        if (overworld != null && overworld.getEnvironment() == World.Environment.NORMAL) {
            totalZombies = (int) overworld.getEntities().stream()
                    .filter(entity -> entity.getType() == EntityType.ZOMBIE)
                    .count();
        }

        return totalZombies;
    }
}
