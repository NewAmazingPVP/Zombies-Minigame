package mc.minigame;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Zombies extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onIntialize(PlayerJoinEvent event) {
        event.getPlayer().sendTitle(ChatColor.DARK_PURPLE + "Welcome to the zombie game ;)", "");
        event.getPlayer().getWorld().strikeLightningEffect(event.getPlayer().getLocation());
        PotionEffect newEffect = new PotionEffect(PotionEffectType.BLINDNESS, 30*20, 1, false, false);
        event.getPlayer().addPotionEffect(newEffect);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player right-clicked with a diamond hoe
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                event.hasItem() && event.getItem().getType() == Material.DIAMOND_HOE) {

            // Calculate the crosshair target vector based on the player's location and direction
            Vector direction = player.getEyeLocation().getDirection();
            double range = 100;
            Location targetLocation = player.getEyeLocation().clone();

            for (int i = 0; i < range; i++) {
                targetLocation.add(direction);

                for (Entity target : player.getWorld().getEntities()) {
                    if (target.getLocation().getBlock().equals(targetLocation.getBlock())) {
                        if (target instanceof LivingEntity) {
                            LivingEntity livingEntity = (LivingEntity) target;
                            double damageAmount = 5.0;
                            livingEntity.damage(damageAmount);
                        }

                        break;
                    }
                }

                // Check if the target location is obstructed by a block
                if (targetLocation.getBlock().getType().isSolid()) {
                    break;
                }
            }
        }
    }
}
