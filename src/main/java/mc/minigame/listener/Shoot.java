package mc.minigame.listener;

import mc.minigame.game.PlayerMoney;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;
import mc.minigame.variables.Weapons;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Shoot implements Listener {

    public Map<Player, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player right-clicked with a diamond hoe
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                event.hasItem() && Weapons.weaponList.contains(event.getItem().getType())) {

            if (hasCooldown(player)) {
                return;
            }

            if(!hasArrow(player))
            {
                player.sendTitle(ChatColor.DARK_RED + "Out of Ammo!", "");
                return;
            }

            decreaseArrow(player);

            if (countArrowsInInventory(player) <= 20){
                TextComponent textComponent = new TextComponent(ChatColor.YELLOW + "You only have " + ChatColor.RED + countArrowsInInventory(player) + ChatColor.YELLOW + " ammos");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
            }

            player.playSound(player.getLocation(), "block.mud_bricks.break", 1.0f, 1.0f);

            // Calculate the crosshair target vector based on the player's location and direction
            Vector direction = player.getEyeLocation().getDirection();
            double range = 100;
            Location targetLocation = player.getEyeLocation().clone();

            for (int i = 0; i < range; i++) {
                targetLocation.add(direction);

                Entity target = getTargetEntityAtLocation(targetLocation);
                if (target != null) {
                    if(target instanceof Zombie) {
                        if (event.getItem().getType() == Material.GOLDEN_HOE || event.getItem().getType() == Material.GOLDEN_SHOVEL) {
                            ((LivingEntity) target).damage(Weapons.calculateDamageAmount(event.getItem().getType()), player);
                            Location explosionLocation = target.getLocation();
                            createExplosion(explosionLocation, Weapons.explosionRadius(event.getItem().getType()), player, event.getItem().getType());
                        } else {
                            PlayerMoney.addCoins(player, 10);
                            String message = ChatColor.GOLD + "+" + ChatColor.BOLD + "10 Hit Coins";
                            TextComponent textComponent = new TextComponent(message);
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
                            damageTarget(player, target);
                        }

                    }

                    break;
                }

                // Target location is obstructed by a block
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

    private void createExplosion(Location location, double radius, Player player, Material material) {
        Vector center = location.toVector();
        for (Entity entity : location.getWorld().getEntities()) {
            if (entity instanceof Zombie && entity.getLocation().distanceSquared(location) <= radius * radius) {
                Vector velocity = entity.getLocation().toVector().subtract(center);
                double distance = velocity.length();
                if (distance != 0) {
                    double force = (1 - distance / radius) * 2.0;
                    if (Double.isFinite(force)) {
                        entity.setVelocity(velocity.normalize().multiply(force));
                        ((LivingEntity) entity).damage(Weapons.calculateDamageAmount(material), player);
                    }
                }
            }
        }
    }


    private void damageTarget(Player player, Entity target) {
        double damageAmount = Weapons.calculateDamageAmount(player.getItemInHand().getType());
        ((LivingEntity) target).damage(damageAmount, player);
    }

    private boolean hasCooldown(Player player) {
        long currentTime = System.currentTimeMillis();
        if (cooldowns.containsKey(player)) {
            long cooldownEndTime = cooldowns.get(player);
            if (currentTime < cooldownEndTime) {
                return true;
            } else {
                cooldowns.remove(player);
            }
        }
        return false;
    }
    private void applyCooldown(Player player) {
        double cooldownSeconds = Weapons.getCooldownDuration(player.getItemInHand().getType());
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
        double cooldownSeconds = Weapons.getCooldownDuration(player.getItemInHand().getType()) * 20;
        player.setCooldown(material, (int) cooldownSeconds);
    }

    public static int countArrowsInInventory(Player player) {
        int arrowCount = 0;
        PlayerInventory inventory = player.getInventory();

        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == Material.ARROW) {
                arrowCount += item.getAmount();
            }
        }

        return arrowCount;
    }

    /*public void shootArrow(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setDamage(0.0);
        arrow.setVelocity(player.getEyeLocation().getDirection().multiply(2));
        arrow.setShooter(player);
        arrow.playEffect(EntityEffect.ARROW_PARTICLES);
        Bukkit.getScheduler().runTaskLater(mainPlugin, arrow::remove, 5);
    }*/
}
