package mc.minigame;

import org.bukkit.*;
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
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendTitle(ChatColor.DARK_PURPLE + "Welcome to the zombie game ;)", "");
        player.getWorld().strikeLightningEffect(event.getPlayer().getLocation());
        PotionEffect newEffect = new PotionEffect(PotionEffectType.BLINDNESS, 5*20, 1, false, false);
        player.addPotionEffect(newEffect);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player right-clicked with a diamond hoe
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                event.hasItem() && event.getItem().getType() == Material.DIAMOND_HOE) {

            player.playSound(player.getLocation(), "block.mud_bricks.break", 1.0f, 1.0f);

            // Calculate the crosshair target vector based on the player's location and direction
            Vector direction = player.getEyeLocation().getDirection();
            double range = 100;
            Location targetLocation = player.getEyeLocation().clone();

            for (int i = 0; i < range; i++) {
                targetLocation.add(direction);

                for (Entity target : player.getWorld().getEntities()) {
                    if ((target.getLocation().getBlock().getX() == targetLocation.getBlock().getX()) && (target.getLocation().getBlock().getZ() == targetLocation.getBlock().getZ())
                            && (target.getLocation().getBlock().getY() >= targetLocation.getBlock().getY() - (target.getHeight()) &&
                            target.getLocation().getBlock().getY() <= targetLocation.getBlock().getY() + (target.getHeight()))) {
                        if (target instanceof LivingEntity && !(target instanceof Player)) {
                            LivingEntity livingEntity = (LivingEntity) target;
                            double damageAmount = 5.0;
                            livingEntity.damage(damageAmount, player);
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

    public void playSoundToNearbyPlayers(Location soundLocation, double radius, Sound sound, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location playerLocation = player.getLocation();
            double distance = soundLocation.distance(playerLocation);

            if (distance <= radius) {
                player.playSound(soundLocation, sound, volume, pitch);
            }
        }
    }

}
