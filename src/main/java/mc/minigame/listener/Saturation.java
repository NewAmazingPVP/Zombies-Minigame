package mc.minigame.listener;

import mc.minigame.Zombies;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Saturation implements Listener {
    private Zombies zombies;
    public void Saturation(Zombies zombies){
        this.zombies = zombies;
    }


    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
                event.setCancelled(true);
                player.setSaturation(20f);
                player.setFoodLevel(20);
            }
        }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED) {
                event.setCancelled(true);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.getHealth() < player.getMaxHealth()) {
                            player.setHealth(Math.min(player.getHealth() + 0.01, player.getMaxHealth()));
                        }
                    }
                }.runTaskLater(zombies, 1);
            }
        }
    }
}
