package mc.minigame.listener;

import mc.minigame.Zombies;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import mc.minigame.variables.Weapons;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Shoot implements Listener {

    private Zombies mainPlugin;

    public Shoot(Zombies mainPlugin) {
        this.mainPlugin = mainPlugin;
    }
    private Weapons weapons = new Weapons();
    public Map<Player, Long> cooldowns = new HashMap<>();


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

            shootArrow(player);
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
                    break;
                }

                // Check if the target location is obstructed by a block
                if (targetLocation.getBlock().getType().isSolid()) {
                    break;
                }
            }

            applyCooldown(player);
            animation(player, player.getItemInHand().getType());
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
        double damageAmount = weapons.calculateDamageAmount(player.getItemInHand().getType());
        ((LivingEntity) target).damage(damageAmount, player);
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
        double cooldownSeconds = weapons.getCooldownDuration(player.getItemInHand().getType());
        if (cooldownSeconds > 0) {
            long cooldownEndTime = (long) (System.currentTimeMillis() + (cooldownSeconds * 1000.0));
            cooldowns.put(player, cooldownEndTime);
        }
    }

    private boolean hasArrow(Player player){
        return player.getInventory().contains(Material.ARROW);
    }

    private void decreaseArrow(Player player){
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        player.getInventory().removeItem(arrow); ;
    }

    public void animation(Player player, Material material) {
        double cooldownSeconds = weapons.getCooldownDuration(player.getItemInHand().getType()) * 20;
        player.setCooldown(material, (int) cooldownSeconds);
    }

    public void shootArrow(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setDamage(0.0);
        arrow.setVelocity(player.getEyeLocation().getDirection().multiply(2)); // Adjust the velocity as needed
        arrow.setShooter(player);
        arrow.playEffect(EntityEffect.ARROW_PARTICLES);
        Bukkit.getScheduler().runTaskLater(mainPlugin, arrow::remove, 5); // Remove the arrow entity after a short delay
    }
}
