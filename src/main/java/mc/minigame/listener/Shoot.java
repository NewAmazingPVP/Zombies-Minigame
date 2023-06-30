package mc.minigame.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import mc.minigame.variables.Weapons;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Shoot implements Listener {

    private Weapons weapons = new Weapons();
    private Map<Player, Long> cooldowns = new HashMap<>();
    private double defaultDamageAmount = 5.0;
    private double defaultCooldown = 1.0; // Cooldown in seconds

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player right-clicked with a diamond hoe
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                event.hasItem() && weapons.weaponList.contains(event.getItem().getType()) && hasArrow(player)) {

            if (hasCooldown(player)) {
                // Player is on cooldown
                return;
            }

            decreaseArrow(player);

            player.playSound(player.getLocation(), "block.mud_bricks.break", 1.0f, 1.0f);

            // Calculate the crosshair target vector based on the player's location and direction
            Vector direction = player.getEyeLocation().getDirection();
            double range = 100;
            Location targetLocation = player.getEyeLocation().clone();

            for (int i = 0; i < range; i++) {
                targetLocation.add(direction);

                Entity target = getTargetEntityAtLocation(targetLocation);
                if (target != null) {
                    damageTarget(player, target);
                    applyCooldown(player);
                    break;
                }

                // Check if the target location is obstructed by a block
                if (targetLocation.getBlock().getType().isSolid()) {
                    break;
                }
            }
        }
    }

    private Entity getTargetEntityAtLocation(Location location) {
        for (Entity target : location.getWorld().getEntities()) {
            if ((target.getLocation().getBlock().getX() == location.getBlock().getX()) &&
                    (target.getLocation().getBlock().getZ() == location.getBlock().getZ()) &&
                    (target.getLocation().getBlock().getY() >= location.getBlock().getY() - target.getHeight()) &&
                    (target.getLocation().getBlock().getY() <= location.getBlock().getY() + target.getHeight()) &&
                    (target instanceof LivingEntity) && !(target instanceof Player)) {
                return target;
            }
        }
        return null;
    }

    private void damageTarget(Player player, Entity target) {
        double damageAmount = calculateDamageAmount(player.getItemInHand().getType());
        ((LivingEntity) target).damage(damageAmount, player);
    }

    private double calculateDamageAmount(Material weaponType) {
        // Add custom damage calculation based on the weapon type
        switch (weaponType) {
            case WOODEN_HOE:
                return 4.0;
            case STONE_HOE:
                return 5.0;
            case IRON_HOE:
                return 6.0;
            case GOLDEN_HOE:
                return 7.0;
            case DIAMOND_HOE:
                return 8.0;
            case NETHERITE_HOE:
                return 9.0;
            default:
                return defaultDamageAmount;
        }
    }

    private boolean hasCooldown(Player player) {
        long currentTime = System.currentTimeMillis();
        if (cooldowns.containsKey(player)) {
            long cooldownEndTime = cooldowns.get(player);
            return currentTime < cooldownEndTime;
        }
        return false;
    }

    private void applyCooldown(Player player) {
        double cooldownSeconds = getCooldownDuration(player.getItemInHand().getType());
        if (cooldownSeconds > 0) {
            long cooldownEndTime = (long) (System.currentTimeMillis() + (cooldownSeconds * 1000.0));
            cooldowns.put(player, cooldownEndTime);
        }
    }

    private double getCooldownDuration(Material weaponType) {
        // Add custom cooldown duration based on the weapon type
        switch (weaponType) {
            case WOODEN_HOE:
                return 0.5; // 0.5 seconds
            case STONE_HOE:
                return 0.6; // 0.6 seconds
            case IRON_HOE:
                return 0.7; // 0.7 seconds
            case GOLDEN_HOE:
                return 0.8; // 0.8 seconds
            case DIAMOND_HOE:
                return 0.9; // 0.9 seconds
            case NETHERITE_HOE:
                return 1.0; // 1.0 second
            default:
                return defaultCooldown;
        }
    }

    private boolean hasArrow(Player player){
        return player.getInventory().contains(Material.ARROW);
    }

    private void decreaseArrow(Player player){
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        player.getInventory().removeItem(arrow); ;
    }
}
